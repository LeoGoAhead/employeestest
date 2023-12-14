package com.example.webapi.service;

import com.example.webapi.pojo.Employees;
import com.example.webapi.pojo.PageBean;

import java.util.List;

public interface EmployeesService {

    PageBean<Employees> list(int pageNum, int pageSize, String name, Integer deptName, Integer dmpDegreeName);

    void add(Employees employees);

    void update(Employees employees);

    void delete(int id);

    List<String[]> getExcelContents(PageBean<Employees> list);
}
