package com.example.webapi.controller;

import com.example.webapi.pojo.Login;
import com.example.webapi.pojo.Result;
import com.example.webapi.pojo.UserRole;
import com.example.webapi.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utils.JwtUtils;
import utils.ThreadLocalUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/account")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public Result login(String username, String password) {
        Login l = loginService.validateUser(username, password);
        if (null == l) {
            return Result.error("用户名或密码错误");
        } else {
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", l.getId());
            claims.put("name", l.getUsername());
            return Result.success(JwtUtils.genToken(claims));
        }
    }

    @GetMapping("/userinfo")
    public Result getUserInfo(String token) {
        if (StringUtils.hasText(token)) {
            Map<String, Object> tokenMap = JwtUtils.parseToken(token);
            Map<String, Object> map = ThreadLocalUtil.get();
            int tokenMapId = Integer.parseInt(tokenMap.get("id").toString());
            if (tokenMapId == Integer.parseInt(map.get("id").toString())) {
                Map<String, Object> m = new HashMap<>();
                List<UserRole> userRoles = loginService.listUserRoleByLoginId(tokenMapId);
                if (null != userRoles && userRoles.size() > 0) {
                    UserRole admin = userRoles.stream().filter(x -> x.getName().equals("admin")).findFirst().get();
                    m.put("roles", new String[]{admin.getName()});
                    m.put("introduction", "");
                    m.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
                    m.put("name", map.get("name").toString());
                    return Result.success(m);
                }
            }
        }
        return null;
    }

    @PostMapping("/logout")
    public Result logout() {
        return Result.success();
    }
}
