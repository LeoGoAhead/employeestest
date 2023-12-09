package com.example.webapi.controller;

import com.example.webapi.pojo.Employees;
import com.example.webapi.pojo.PageBean;
import com.example.webapi.pojo.Result;
import com.example.webapi.service.EmployeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import utils.JwtUtils;
import utils.ThreadLocalUtil;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/employees")
public class EmployeesController {

    @Autowired
    private EmployeesService employeesService;

    @PostMapping("/login")
    public Result login(String username, String password) {
        Employees e = employeesService.validateUser(username, password);
        if (null == e) {
            return Result.error("用户名或密码错误");
        } else {
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", e.getId());
            claims.put("name", e.getUsername());
            return Result.success(JwtUtils.genToken(claims));
        }
    }

    @GetMapping("/userinfo")
    public Result getUserInfo(String token) {
        if (StringUtils.hasText(token)) {
            Map<String, Object> tokenMap = JwtUtils.parseToken(token);
            Map<String, Object> map = ThreadLocalUtil.get();
            if (Integer.parseInt(tokenMap.get("id").toString()) == Integer.parseInt(map.get("id").toString())) {
                Map<String, Object> m = new HashMap<>();
                m.put("roles", new String[]{"admin"});
                m.put("introduction", "");
                m.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
                m.put("name", map.get("name").toString());
                return Result.success(m);
            }
        }
        return null;
    }

    @PostMapping("/logout")
    public Result logout() {
        return Result.success();
    }


    @PostMapping("/listemployees")
    public Result<PageBean<Employees>> list(int pageNum, int pageSize, String name, String deptName, String empDegreeName) {
        PageBean<Employees> list = employeesService.list(pageNum, pageSize, name, deptName, empDegreeName);
        return Result.success(list);
    }
}
