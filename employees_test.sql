create database employees_test;

use employees_test;

create table employees (
                      id int unsigned primary key auto_increment comment 'ID',
                      username varchar(20) not null unique comment '用户名',
                      password varchar(32)  comment '密码',
                      emp_name varchar(50)  default '' comment '员工姓名',
                      sex int comment '性别',
                      age int comment '年龄',
                      dept_name varchar(50) comment '部门名称',
                      emp_degree_name varchar(50) comment '学历'
) comment '员工表';
