package com.example.webapi.mapper;

import com.example.webapi.pojo.Login;
import com.example.webapi.pojo.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface LoginMapper {

    @Select("select * from Login where username = #{username} and password = #{password}")
    Login validateUser(@Param("username") String username, @Param("password") String password);

    @Select("select ur.Id, ur.name from Login_Role lr left join UserRole ur on lr.RoleId = ur.Id where lr.LoginId = #{loginId} ")
    List<UserRole> listUserRoleByLoginId(int loginId);
}
