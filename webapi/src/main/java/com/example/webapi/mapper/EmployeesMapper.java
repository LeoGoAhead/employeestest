package com.example.webapi.mapper;

import com.example.webapi.pojo.Employees;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EmployeesMapper {

    List<Employees> list(@Param("empName") String empName, @Param("deptName") int deptName, @Param("empDegreeName") int empDegreeName);

    @Insert("insert into Employees(emp_name, sex, age, dept_name, emp_degree_name) " +
            "values(#{empName}, #{sex}, #{age}, #{deptName}, #{empDegreeName})")
    void add(Employees employees);

    @Update("update Employees set emp_name = #{empName}, sex = #{sex}, age = #{age}, dept_name = #{deptName}, emp_degree_name = #{empDegreeName} where id = #{id}")
    void update(Employees employees);

    @Delete("delete from Employees where id = #{id}")
    void delete(int id);
}
