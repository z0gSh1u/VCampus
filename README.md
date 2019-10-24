# 虚拟校园系统VCampus

![logo](https://s2.ax1x.com/2019/08/27/m40U2j.png)

VCampus是一款基于C/S架构的软件，主要模拟了东南大学校内与学生、教师、行政人员相关的各类功能情境。

本项目是东南大学大三短学期的软件实践课程项目，开发者由东南大学计算机科学与工程学院六名大三学生组成。欢迎访问他们的Github页面：

[z0gSh1u](https://github.com/z0gSh1u)，[LongChen2018](https://github.com/LongChen2018)，[LY000001](https://github.com/LY000001)，[ZCLK2019](https://github.com/ZCLK2019)，[shilofel](https://github.com/shilofel)，[Twileon](https://github.com/Twileon)

## 开始

如果您不使用Release版本，而计划自行编译运行，则在运行前，请先完成下列配置文件的配置：

- 数据库配置 - `/src/resources/database.properties`（在开发周期内，该文件不会被上传）
- 服务器端配置 - `/src/resources/server.properties`

然后借助启动类先后启动服务器端和客户端即可：

- 服务器端启动类 - `tech.zxuuu.server.startup.Bootstrap`
- 客户端启动类 - `tech.zxuuu.client.startup.Bootstrap`

## 系统架构图

![系统架构图](https://s2.ax1x.com/2019/08/23/mD6lHs.jpg)

- 在用户登陆后，根据用户类型路由到与之对应的主界面，主界面上是各合法的功能模块的入口。
- 为了实现服务器和客户端的通信，我们构建了一套简单的请求-响应机制。一对请求-响应拥有相同的哈希值来标识。对象数据在Socket连接上传送的形式是一行JSON字符串。不使用二进制流的原因是，JSON是当今网络世界最流行的数据交换格式，拥有很好的可移植性。
- 请求到达服务器时，我们利用Java的反射机制，调用相关函数并传入参数。
- 我们借助流行的MyBatis框架构建了访问数据库的DAO层，避免了直接在服务器端业务代码中组织SQL语句。

## 技术细节

- 请求-响应机制及消息队列 
- 利用反射调用后端处理函数
- JSON序列化与反序列化
- 利用MyBatis建立DAO层

## 改善！

目前项目仍处于持续更新的开发阶段。在开发阶段完成后，欢迎你帮助我们改善该项目。你可以提交Pull Request或者Issue，我们会尽快处理。
