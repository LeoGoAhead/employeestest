package com.example.webapi.service;

import com.example.webapi.pojo.Employees;
import com.example.webapi.pojo.PageBean;

public interface EmployeesService {
    Employees validateUser(String username, String password);

    PageBean<Employees> list(int pageNum, int pageSize, String name, String deptName, String empDegreeName);
}
