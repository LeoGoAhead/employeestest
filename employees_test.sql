create database employees_test;

use employees_test;

-- 用户表
create table Login (
	id int unsigned primary key auto_increment comment 'ID',
	username varchar(20) not null unique comment '用户名',
    password varchar(32) comment '密码',
	full_name varchar(100) comment '全名'
) comment '用户表';

-- 角色表
create table UserRole (
	id int unsigned comment 'ID',
	name varchar(50) comment '角色名称'
) comment '角色表';

Insert into UserRole (id, name)
select 999, '系统管理员'
where not exists(Select * from UserRole where id = 999 and name = '系统管理员')

-- 用户角色表
create table Login_Role (
	id int unsigned comment 'ID',
	LoginId int comment '用户id',
	RoleId int comment '角色id'
) comment '用户角色表';

-- 角色权限表
create table Role_Permission (
	id int unsigned comment 'ID',
	RoleId int comment '角色id',
	PermissionId int comment '权限id'
) comment '角色权限表';

Declare @id int
Insert into Login (username, password, full_name)
select 'admin', '0192023a7bbd73250516f069df18b500', '系统管理员'
where not exists(Select * from Login where username = 'admin' and password = '0192023a7bbd73250516f069df18b500' and full_name = '系统管理员')
Set @id = Scope_Identity()
Insert into Login_Role(LoginId, RoleId) values(@id, 999)

--create table employees (
--                      id int unsigned primary key auto_increment comment 'ID',
--                      emp_name varchar(50)  default '' comment '员工姓名',
--                      sex varchar(10) comment '性别',
--                      age int comment '年龄',
--                      dept_name varchar(50) comment '部门名称',
--                      emp_degree_name varchar(50) comment '学历'
--) comment '员工表';

--Insert into employees(username, password, emp_name, sex, age, dept_name, emp_degree_name)
--select 'admin', '0192023a7bbd73250516f069df18b500', '管理员', '男', 30, '人事部', '研究生'
--where not exists(Select * from employees where username = 'admin' and password = '0192023a7bbd73250516f069df18b500' and emp_name = '管理员' and sex = '男' and age = 30 and dept_name = '人事部' and emp_degree_name = '研究生')