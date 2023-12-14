package com.example.webapi.service.impl;

import com.example.webapi.Enum.DegreeEnum;
import com.example.webapi.Enum.DepartmentEnum;
import com.example.webapi.Enum.SexEnum;
import com.example.webapi.mapper.EmployeesMapper;
import com.example.webapi.pojo.Employees;
import com.example.webapi.pojo.PageBean;
import com.example.webapi.service.EmployeesService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utils.Md5Util;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeesServiceImpl implements EmployeesService {

    @Autowired
    private EmployeesMapper employeesMapper;

    @Override
    public PageBean<Employees> list(int pageNum, int pageSize, String empName, Integer deptName, Integer dmpDegreeName) {
        PageBean<Employees> pb = new PageBean<>();
        PageHelper.startPage(pageNum, pageSize);
        if (null == deptName) {
            deptName = 0;
        }
        if (null == dmpDegreeName) {
            dmpDegreeName = 0;
        }
        List<Employees> as = employeesMapper.list(empName, deptName, dmpDegreeName);
        Page<Employees> p = (Page<Employees>) as;
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());
        return pb;
    }

    @Override
    public void add(Employees employees) {
        employeesMapper.add(employees);
    }

    @Override
    public void update(Employees employees) {
        employeesMapper.update(employees);
    }

    @Override
    public void delete(int id) {
        employeesMapper.delete(id);
    }

    @Override
    public List<String[]> getExcelContents(PageBean<Employees> list) {
        List<String[]> contents = new ArrayList<String[]>();
        List<Employees> items = list.getItems();
        for (int i = 0; i < items.size(); i++) {
            Employees employees = items.get(i);
            String[] content = new String[6];
            content[0] = String.valueOf(i+1);
            content[1] = employees.getEmpName();
            content[2] = SexEnum.getSexEnum(employees.getSex()).getValue();
            content[3] = employees.getAge().toString();
            content[4] = DepartmentEnum.getDepartmentEnum(employees.getDeptName()).getValue();
            content[5] = DegreeEnum.getDegreeEnum(employees.getEmpDegreeName()).getValue();
            contents.add(content);
        }
        return contents;
    }
}
