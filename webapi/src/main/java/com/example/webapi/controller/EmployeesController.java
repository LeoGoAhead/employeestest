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


    @PostMapping("/listemployees")
    public Result<PageBean<Employees>> list(int pageNum, int pageSize, String name, String deptName, String empDegreeName) {
        PageBean<Employees> list = employeesService.list(pageNum, pageSize, name, deptName, empDegreeName);
        return Result.success(list);
    }

    @PostMapping("/addemployees")
    public Result addemployees(Employees employees) {
        try {
            employeesService.add(employees);
            return Result.success();
        } catch (Exception e) {
            return Result.error("新增失败");
        }
    }

    @PostMapping("/updateemployees")
    public Result updatEemployees(Employees employees) {
        try {
            employeesService.update(employees);
            return Result.success();
        } catch (Exception e) {
            return Result.error("更新失败");
        }
    }

    @PostMapping("/deleteemployees")
    public Result deleteEmployees(int id) {
        employeesService.delete(id);
        return Result.success();
    }
}
