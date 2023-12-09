package com.example.webapi.mapper;

import com.example.webapi.pojo.Employees;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EmployeesMapper {

    @Select("select * from employees where username = #{username} and password = #{password}")
    Employees validateUser(@Param("username") String username, @Param("password") String password);

    List<Employees> list(@Param("empName") String empName, @Param("deptName") String deptName, @Param("empDegreeName") String empDegreeName);
}
