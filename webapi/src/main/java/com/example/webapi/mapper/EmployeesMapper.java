package com.example.webapi.mapper;

import com.example.webapi.pojo.Employees;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface EmployeesMapper {

    @Select("select * from employees where username = #{username} and password = #{password}")
    Employees validateUser(@Param("username") String username, @Param("password") String password);


}
