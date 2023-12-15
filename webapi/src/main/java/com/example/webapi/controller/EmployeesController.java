package com.example.webapi.controller;

import com.example.webapi.Enum.DegreeEnum;
import com.example.webapi.Enum.DepartmentEnum;
import com.example.webapi.Enum.SexEnum;
import com.example.webapi.pojo.Employees;
import com.example.webapi.pojo.PageBean;
import com.example.webapi.pojo.Result;
import com.example.webapi.service.EmployeesService;
import com.example.webapi.service.impl.ExcelPoiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import utils.JwtUtils;
import utils.ThreadLocalUtil;
import utils.Util;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/employees")
public class EmployeesController {

    @Autowired
    private EmployeesService employeesService;
    @Autowired
    private ExcelPoiService excelPoiService;

    @PostMapping("/listsex")
    public Result listSex() {
        List<Map<String, Object>> maps = SexEnum.listSexEnum();
        return Result.success(maps);
    }

    @PostMapping("/listdepartment")
    public Result listDepartment() {
        List<Map<String, Object>> maps = DepartmentEnum.listDepartmentEnum();
        return Result.success(maps);
    }

    @PostMapping("/listdegree")
    public Result listDegree() {
        List<Map<String, Object>> maps = DegreeEnum.listDegreeEnum();
        return Result.success(maps);
    }

    @PostMapping("/listemployees")
    public Result<PageBean<Employees>> list(int pageNum, int pageSize, String name, Integer deptNameId, Integer dmpDegreeNameId) {
        PageBean<Employees> list = employeesService.list(pageNum, pageSize, name, deptNameId, dmpDegreeNameId);
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

    @PostMapping("/exportemployees")
    public Result exportEmployees(String name, Integer deptName, Integer dmpDegreeName) {
        int pageNo = 1;
        int pageSize = Integer.MAX_VALUE;
        PageBean<Employees> list = employeesService.list(pageNo, pageSize, name, deptName, dmpDegreeName);
        String fileName = "职工列表_" + UUID.randomUUID().toString() + ".xlsx";
        String filePath = Util.combinePath("D:/", "temp", fileName);
        File file = new File(filePath);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        String[] titles = excelPoiService.getExportExcelTitles(ExcelPoiService.exportEmployees);
        List<String[]> contents = employeesService.getExcelContents(list);
        excelPoiService.writeExcel(filePath, "职工列表", titles, contents, null);
        return Result.success();
    }
}
