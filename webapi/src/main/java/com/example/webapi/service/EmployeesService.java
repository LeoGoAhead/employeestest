package com.example.webapi.service;

import com.example.webapi.pojo.Employees;
import com.example.webapi.pojo.PageBean;

public interface EmployeesService {

    PageBean<Employees> list(int pageNum, int pageSize, String name, String deptName, String empDegreeName);

    void add(Employees employees);

    void update(Employees employees);

    void delete(int id);
}
