package com.example.webapi.service.impl;

import com.example.webapi.mapper.EmployeesMapper;
import com.example.webapi.pojo.Employees;
import com.example.webapi.pojo.PageBean;
import com.example.webapi.service.EmployeesService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utils.Md5Util;

import java.util.List;

@Service
public class EmployeesServiceImpl implements EmployeesService {

    @Autowired
    private EmployeesMapper employeesMapper;

    @Override
    public Employees validateUser(String username, String password) {
        return employeesMapper.validateUser(username, Md5Util.getMD5String(password));
    }

    @Override
    public PageBean<Employees> list(int pageNum, int pageSize, String empName, String deptName, String dmpDegreeName) {
        PageBean<Employees> pb = new PageBean<>();
        PageHelper.startPage(pageNum, pageSize);
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
}
