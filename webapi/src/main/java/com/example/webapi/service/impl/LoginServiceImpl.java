package com.example.webapi.service.impl;

import com.example.webapi.mapper.LoginMapper;
import com.example.webapi.pojo.Employees;
import com.example.webapi.pojo.Login;
import com.example.webapi.pojo.UserRole;
import com.example.webapi.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utils.Md5Util;

import java.util.List;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginMapper loginMapper;

    @Override
    public Login validateUser(String username, String password) {
        return loginMapper.validateUser(username, Md5Util.getMD5String(password));
    }

    @Override
    public List<UserRole> listUserRoleByLoginId(int loginId) {
        return loginMapper.listUserRoleByLoginId(loginId);
    }
}
