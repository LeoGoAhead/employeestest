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


If not exists(Select top 1 1 From employees)
Begin
	Insert into employees(username, password, emp_name, sex, age, dept_name, emp_degree_name)
	values('admin', '0192023a7bbd73250516f069df18b500', '管理员', 1, 30, '经理部', '博士')
End
Go

Insert into employees(username, password, emp_name, sex, age, dept_name, emp_degree_name)
select 'admin', '0192023a7bbd73250516f069df18b500', '管理员', 1, 30, '经理部', '博士'
where not exists(Select * from employees where username = 'admin' and password = '0192023a7bbd73250516f069df18b500' and emp_name = '管理员' and sex = 1 and age = 30 and dept_name = '经理部' and emp_degree_name = '博士')