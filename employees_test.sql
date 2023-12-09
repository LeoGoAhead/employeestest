create database employees_test;

use employees_test;

create table employees (
                      id int unsigned primary key auto_increment comment 'ID',
                      username varchar(20) not null unique comment '用户名',
                      password varchar(32)  comment '密码',
                      emp_name varchar(50)  default '' comment '员工姓名',
                      sex varchar(10) comment '性别',
                      age int comment '年龄',
                      dept_name varchar(50) comment '部门名称',
                      emp_degree_name varchar(50) comment '学历'
) comment '员工表';

Insert into employees(username, password, emp_name, sex, age, dept_name, emp_degree_name)
select 'admin', '0192023a7bbd73250516f069df18b500', '管理员', '男', 30, '人事部', '研究生'
where not exists(Select * from employees where username = 'admin' and password = '0192023a7bbd73250516f069df18b500' and emp_name = '管理员' and sex = '男' and age = 30 and dept_name = '人事部' and emp_degree_name = '研究生')