package com.example.webapi.service;

import com.example.webapi.pojo.Login;
import com.example.webapi.pojo.UserRole;

import java.util.List;

public interface LoginService {
    Login validateUser(String username, String password);

    List<UserRole> listUserRoleByLoginId(int loginId);
}
