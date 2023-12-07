package com.example.webapi.service.impl;

import com.example.webapi.mapper.EmployeesMapper;
import com.example.webapi.pojo.Employees;
import com.example.webapi.service.EmployeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utils.Md5Util;

@Service
public class EmployeesServiceImpl implements EmployeesService {

    @Autowired
    private EmployeesMapper employeesMapper;

    @Override
    public Employees validateUser(String username, String password) {
        return employeesMapper.validateUser(username, Md5Util.getMD5String(password));
    }
}
