package com.example.webapi.service;

import com.example.webapi.pojo.Employees;

public interface EmployeesService {
    Employees validateUser(String username, String password);

}
