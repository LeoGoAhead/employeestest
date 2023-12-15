目录及文件说明：
1. 根目录中 vue-element-admin 文件夹为前端代码
2. 根目录中 webapi 文件夹为后端代码
3. 根目录中 employees_test.sql 文件为项目初始化数据库脚本文件，将内容在MySQL客户端执行即可获得对应的表结构和部分数据
	登录账号：admin
	登录密码：admin123
4. application.yml文件中请修改MySQL的连接ip及用户名密码
5. 虚拟路径为 /devapi 默认端口为8586
6. 员工列表导出功能的导出路径为 D:/temp 文件夹下
7. 根目录中 monitor.sh 为Linux系统监控cpu、内存及硬盘运行状态的shell脚本
8. 根目录中 SearchTxt.sh 为Linux系统搜索关键词的shell脚本，关键词写死为breathing，搜索的文件路径为/usr/local/Hello.txt 将搜索内容复制到新文件路径/usr/local/Hello1.txt
9. 根目录中 Hello.txt 该文件配合SearchTxt.sh脚本实现搜索功能