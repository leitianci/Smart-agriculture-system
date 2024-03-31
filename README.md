# Smart-agriculture-system
智慧农业系统管理软件，虚拟串口，串口调试助手，GUI界面，Web端

在软件设计方面使用传感器来模拟数据来源，使用Java串口技术，虚拟串口实现串口通信，接收到数据后存储到MySQL数据库，通过GUI图形用户界面及SpringBoot搭建的网页端进行展示。
![QQ截图20240331094627](https://github.com/leitianci/Smart-agriculture-system/assets/102131124/9ef30500-1cff-4c5b-af8a-19797f3d8d2c)
![QQ截图20240331103400](https://github.com/leitianci/Smart-agriculture-system/assets/102131124/b4fb1726-2363-4d37-8c92-e69f701a42a6)

运行这个项目时，需要安装虚拟串口、串口调试助手并打开，用串口调试助手打开COM2、4、6、8，分别模拟空气温度、空气湿度、土壤湿度、光照强度传感器。
![QQ截图20240331004504](https://github.com/leitianci/Smart-agriculture-system/assets/102131124/4c580f2b-30b6-4204-b2cb-07be8234cb52)
![QQ截图20240331225646](https://github.com/leitianci/Smart-agriculture-system/assets/102131124/22188f8c-31f0-4a14-b397-30f37bae601b)


传感器数据编码格式设置：每一条有效的传感器数据有8位，起始位2位，固定为00，终止位2位，固定为00，中间4位为有效数据位，其中前两位代表整数部分，后两位代表小数部分。

数据库有2个表，user表、farmland表
2张表设计
![QQ截图20240331104010](https://github.com/leitianci/Smart-agriculture-system/assets/102131124/b2ba0546-b18f-4560-be6e-229b08b394c5)
![QQ截图20240331104045](https://github.com/leitianci/Smart-agriculture-system/assets/102131124/b8164a41-fe7c-4ac5-98bf-746da046c9aa)

2张表数据
INSERT INTO `user` VALUES (1, 'admin', '123456', '管理员');
INSERT INTO `user` VALUES (2, '李永贵', '123456', '农民');

INSERT INTO `farmland` VALUES ('水稻田', '广东省蕉岭县种植基地', 1, '2023-6-22 00:21:54', 23.34, 45.34, 9, 68);
INSERT INTO `farmland` VALUES ('水稻田', '广东省蕉岭县种植基地', 2, '2023-6-22 00:21:58', 23.37, 46.34, 8.7, 65);


