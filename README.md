# Smart-agriculture-system
智慧农业系统管理软件，虚拟串口，串口调试助手，GUI界面，Web端

在软件设计方面使用传感器来模拟数据来源，使用Java串口技术，虚拟串口实现串口通信，接收到数据后存储到MySQL数据库，通过GUI图形用户界面及SpringBoot搭建的网页端进行展示。
![QQ截图20230621231842](https://github.com/leitianci/Smart-agriculture-system/assets/102131124/5cb975b1-2d4f-4039-8258-d000cb955b0b)
![QQ截图20230621225557](https://github.com/leitianci/Smart-agriculture-system/assets/102131124/c2e17597-6743-4741-baf3-ad176cc18080)

运行这个项目时，需要安装虚拟串口、串口调试助手并打开，用串口调试助手打开COM2、4、6、8，分别模拟空气温度、空气湿度、土壤湿度、光照强度传感器。
![20230707175244](https://github.com/leitianci/Smart-agriculture-system/assets/102131124/9834d1de-69fb-4b78-bc93-6560ec11a697)
![QQ截图20230621225646](https://github.com/leitianci/Smart-agriculture-system/assets/102131124/ff4a0d4b-c3d5-4f24-a81a-9e0e5bfa6c21)

传感器数据编码格式设置：每一条有效的传感器数据有8位，起始位2位，固定为00，终止位2位，固定为00，中间4位为有效数据位，其中前两位代表整数部分，后两位代表小数部分。

数据库有2个表，user表、farmland表
2张表设计
![QQ截图20230707175412](https://github.com/leitianci/Smart-agriculture-system/assets/102131124/485d7d3b-abed-42e9-92db-ecac615a6208)
![QQ截图20230707175435](https://github.com/leitianci/Smart-agriculture-system/assets/102131124/31beac14-724a-46c6-b75f-6c74ee680c2f)
2张表数据
INSERT INTO `user` VALUES (1, 'admin', '123456', '管理员');
INSERT INTO `user` VALUES (2, '李永贵', '123456', '农民');

INSERT INTO `farmland` VALUES ('水稻田', '广东省蕉岭县种植基地', 1, '2023-6-22 00:21:54', 23.34, 45.34, 9, 68);
INSERT INTO `farmland` VALUES ('水稻田', '广东省蕉岭县种植基地', 2, '2023-6-22 00:21:58', 23.37, 46.34, 8.7, 65);



