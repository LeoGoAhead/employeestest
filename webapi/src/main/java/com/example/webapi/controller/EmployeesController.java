package com.example.webapi.controller;

import com.example.webapi.pojo.Employees;
import com.example.webapi.pojo.Result;
import com.example.webapi.service.EmployeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utils.JwtUtils;

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


}
