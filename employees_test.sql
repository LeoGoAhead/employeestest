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
select 999, 'admin'
where not exists(Select * from UserRole where id = 999 and name = 'admin');

-- 用户角色表
create table Login_Role (
	id int unsigned primary key auto_increment comment 'ID',
	LoginId int comment '用户id',
	RoleId int comment '角色id'
) comment '用户角色表';

-- 角色权限表
create table Role_Permission (
	id int unsigned comment 'ID',
	RoleId int comment '角色id',
	PermissionId int comment '权限id'
) comment '角色权限表';

Insert into Login (username, password, full_name)
select 'admin', '0192023a7bbd73250516f069df18b500', '系统管理员'
where not exists(Select * from Login where username = 'admin' and password = '0192023a7bbd73250516f069df18b500' and full_name = '系统管理员');
Set @id = LAST_INSERT_ID();
Insert into Login_Role(LoginId, RoleId) values(@id, 999);

create table Employees (
    id int unsigned primary key auto_increment comment 'ID',
    emp_name varchar(50)  default '' comment '员工姓名',
    sex int comment '性别',
    age int comment '年龄',
    dept_name int comment '部门名称',
    emp_degree_name int comment '学历'
) comment '员工表';
