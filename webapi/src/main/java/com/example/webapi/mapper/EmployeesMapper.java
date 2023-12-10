package com.example.webapi.mapper;

import com.example.webapi.pojo.Employees;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EmployeesMapper {

    @Select("select * from employees where username = #{username} and password = #{password}")
    Employees validateUser(@Param("username") String username, @Param("password") String password);

    List<Employees> list(@Param("empName") String empName, @Param("deptName") String deptName, @Param("empDegreeName") String empDegreeName);

    @Insert("insert into employees(username, password, emp_name, sex, age, dept_name, emp_degree_name) " +
            "values(#{username}, #{password}, #{empName}, #{sex}, #{age}, #{deptName}, #{empDegreeName})")
    void add(Employees employees);

    @Update("update employees set username = #{username}, password = #{password}, emp_name = #{empName}, sex = #{sex}, age = #{age}, dept_name = #{deptName}, emp_degree_name = #{empDegreeName} where id = #{id}")
    void update(Employees employees);

    @Delete("delete from employees where id = #{id}")
    void delete(int id);
}
