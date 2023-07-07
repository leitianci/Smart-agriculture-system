package com.msb.tool;

import com.mysql.cj.jdbc.Driver;
import gnu.io.SerialPort;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public class Client {

    public static void run() throws Exception {

        //GUI设计
        JFrame jf=new JFrame("各类农业传感器实时参数");// 创建一个标题为"JTextArea"的窗口
        jf.setBounds(500, 250, 600, 200);// 设置窗口的坐标和大小

        JPanel jPanel = new JPanel();

        JLabel jLabel1 = new JLabel("空气温度");
        jPanel.add(jLabel1);
        JTextArea jTextArea1 = new JTextArea("",4,20);
        jTextArea1.setLineWrap(true);
        JScrollPane jScrollPane1 = new JScrollPane(jTextArea1);
        jPanel.add(jScrollPane1);

        JLabel jLabel2 = new JLabel("空气湿度");
        jPanel.add(jLabel2);
        JTextArea jTextArea2 = new JTextArea("",4,20);
        jTextArea2.setLineWrap(true);
        JScrollPane jScrollPane2 = new JScrollPane(jTextArea2);
        jPanel.add(jScrollPane2);

        JLabel jLabel3 = new JLabel("光照强度");
        jPanel.add(jLabel3);
        JTextArea jTextArea3 = new JTextArea("",4,20);
        jTextArea3.setLineWrap(true);
        JScrollPane jScrollPane3 = new JScrollPane(jTextArea3);
        jPanel.add(jScrollPane3);

        JLabel jLabel4 = new JLabel("土壤湿度");
        jPanel.add(jLabel4);
        JTextArea jTextArea4 = new JTextArea("",4,20);
        jTextArea4.setLineWrap(true);
        JScrollPane jScrollPane4 = new JScrollPane(jTextArea4);
        jPanel.add(jScrollPane4);
        
        jf.add(jPanel);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);// 设置窗口关闭即退出程序
        jf.setVisible(true);// 设置窗口可见


        //JDBC连接数据库
        /**
         * 向Dept表添加一个数据
         */
        //加载驱动
        Driver driver = new Driver();
        //注册驱动
        DriverManager.registerDriver(driver);
        //获得链接
        /**
         *  url：统一资源定位符
         *  1协议      jdbc:mysql:
         *  2IP       127.0.0.1
         *  3端口号    3306
         *  4数据库名  mydb
         *  5参数
         *
         */
        String url="jdbc:mysql://127.0.0.1:3306/agriculture?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai";
        String user="root";
        String password="root";
        Connection connection = DriverManager.getConnection(url,user,password);




        ArrayList<String> findPort = SerialTool.findPort();
        System.out.println("可用串口");
        for(String f:findPort) {
            System.out.println(f);
        }
        System.out.println();


        //串口1
        //设定发送字符串
        byte[] bs1 = hex2Bytes("BB");
        System.out.println(Arrays.toString(bs1) +"------");//打印字符串
        System.out.println(findPort.get(0));//找到COM1串口
        SerialPort Port1 = SerialTool.openPort("COM1", 9600);//打开串口
        System.out.println(Port1.getName());//获取串口名
        SerialTool.addListener(Port1, new SerialTool.DataAvailableListener() {
            @SuppressWarnings("unused")
            @Override
            public void dataAvailable() {
                byte[] data = null;
                try {
                    if (Port1 == null) {
                        System.out.println("串口对象为空，监听失败！");
                    } else {
                        // 读取串口数据
                        data = SerialTool.readFromPort(Port1);
                        /*System.out.println("打印"+data);*/
                        String resultHEX = byteArrayToHexString(data);
                        jTextArea1.append(resultHEX + "\n");


                        //
                        //获得语句对象statement
                        Statement statement = connection.createStatement();
                        float f1 = Float.parseFloat(resultHEX);
                        System.out.println(f1/10000);
                        //执行sql语句
                        String sql="insert into farmland (time,airtemperature) values (NOW(),'"+f1+"'/10000)";
                        int rows = statement.executeUpdate(sql);
                        System.out.println("影响数据行数"+rows);
                        //释放资源
                        //注意顺序，后获得的先关闭，先获得的后关闭
                        statement.close();

                        System.out.println(resultHEX);
                    }
                } catch (Exception e) {
                    System.out.println("错误");
                }
            }
        });
        //SerialTool.sendToPort(Port1, bs1);//写入,写入应该在监听器打开之后而不是之前

        //串口3
        byte[] bs3 = hex2Bytes("BB");
        SerialPort Port3 = SerialTool.openPort("COM3", 9600);//打开串口
        System.out.println(Port3.getName());//获取串口名
        SerialTool.addListener(Port3, new SerialTool.DataAvailableListener() {
            @SuppressWarnings("unused")
            @Override
            public void dataAvailable() {
                byte[] data = null;
                try {
                    if (Port3 == null) {
                        System.out.println("串口对象为空，监听失败！");
                    } else {
                        // 读取串口数据
                        data = SerialTool.readFromPort(Port3);
                        /*System.out.println("打印"+data);*/
                        String resultHEX = byteArrayToHexString(data);


                        //
                        //获得语句对象statement
                        Statement statement = connection.createStatement();
                        float f2 = Float.parseFloat(resultHEX);
                        System.out.println(f2/100);
                        //执行sql语句
                        String sql="insert into farmland (time,airhumidity) values (NOW(),'"+f2+"'/10000)";
                        int rows = statement.executeUpdate(sql);
                        System.out.println("影响数据行数"+rows);
                        //释放资源
                        //注意顺序，后获得的先关闭，先获得的后关闭
                        statement.close();

                        jTextArea2.append(resultHEX + "\n");
                        System.out.println(resultHEX);
                    }
                } catch (Exception e) {
                    System.out.println("错误");
                }
            }
        });
        //SerialTool.sendToPort(Port3, bs3);//写入,写入应该在监听器打开之后而不是之前



        //串口5
        byte[] bs5 = hex2Bytes("BB");
        SerialPort Port5 = SerialTool.openPort("COM5", 9600);//打开串口
        System.out.println(Port5.getName());//获取串口名
        SerialTool.addListener(Port5, new SerialTool.DataAvailableListener() {
            @SuppressWarnings("unused")
            @Override
            public void dataAvailable() {
                byte[] data = null;
                try {
                    if (Port5 == null) {
                        System.out.println("串口对象为空，监听失败！");
                    } else {
                        // 读取串口数据
                        data = SerialTool.readFromPort(Port5);
                        /*System.out.println("打印"+data);*/
                        String resultHEX = byteArrayToHexString(data);


                        //
                        //获得语句对象statement
                        Statement statement = connection.createStatement();
                        float f3 = Float.parseFloat(resultHEX);
                        System.out.println(f3/10000);
                        //执行sql语句
                        String sql="insert into farmland (time,illumination) values (NOW(),'"+f3+"'/100000)";
                        int rows = statement.executeUpdate(sql);
                        System.out.println("影响数据行数"+rows);
                        //释放资源
                        //注意顺序，后获得的先关闭，先获得的后关闭
                        statement.close();

                        jTextArea3.append(resultHEX + "\n");
                        System.out.println(resultHEX);
                    }
                } catch (Exception e) {
                    System.out.println("错误");
                }
            }
        });
        //SerialTool.sendToPort(Port3, bs3);//写入,写入应该在监听器打开之后而不是之前


        //串口7
        //土壤相对湿度
        byte[] bs7 = hex2Bytes("BB");
        SerialPort Port7 = SerialTool.openPort("COM7", 9600);//打开串口
        System.out.println(Port7.getName());//获取串口名
        SerialTool.addListener(Port7, new SerialTool.DataAvailableListener() {
            @SuppressWarnings("unused")
            @Override
            public void dataAvailable() {
                byte[] data = null;
                try {
                    if (Port7 == null) {
                        System.out.println("串口对象为空，监听失败！");
                    } else {
                        // 读取串口数据
                        data = SerialTool.readFromPort(Port7);
                        /*System.out.println("打印"+data);*/
                        String resultHEX = byteArrayToHexString(data);


                        //
                        //获得语句对象statement
                        Statement statement = connection.createStatement();
                        float f4 = Float.parseFloat(resultHEX);
                        System.out.println(f4/10000);
                        //执行sql语句
                        String sql="insert into farmland (time,soilhumidity) values (NOW(),'"+f4+"'/10000)";
                        int rows = statement.executeUpdate(sql);
                        System.out.println("影响数据行数"+rows);
                        //释放资源
                        //注意顺序，后获得的先关闭，先获得的后关闭
                        statement.close();

                        jTextArea4.append(resultHEX + "\n");
                        System.out.println(resultHEX);
                    }
                } catch (Exception e) {
                    System.out.println("错误");
                }
            }
        });
        //SerialTool.sendToPort(Port3, bs3);//写入,写入应该在监听器打开之后而不是之前


        /*byte[] readFromPort = SerialTool.readFromPort(Port);
        System.out.println(readFromPort);*/
        /*SerialTool.closePort(Port);*/
    }

    public static void JDBC() throws SQLException {
        /**
         * 向Dept表添加一个数据
         */
        //加载驱动
        Driver driver = new Driver();
        //注册驱动
        DriverManager.registerDriver(driver);
        //获得链接
        /**
         *  url：统一资源定位符
         *  1协议      jdbc:mysql:
         *  2IP       127.0.0.1
         *  3端口号    3306
         *  4数据库名  mydb
         *  5参数
         *
         */
        String url="jdbc:mysql://127.0.0.1:3306/agriculture?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai";
        String user="root";
        String password="root";
        Connection connection = DriverManager.getConnection(url,user,password);
        //获得语句对象statement
        Statement statement = connection.createStatement();
        //执行sql语句
        String sql="insert into dept values(60,'教学部','北京')";
        int rows = statement.executeUpdate(sql);
        System.out.println("影响数据行数"+rows);
        //释放资源
        //注意顺序，后获得的先关闭，先获得的后关闭
        statement.close();
        connection.close();
    }



    //字符串转化成byte[]数组
    public static byte[] hex2Bytes(String hex) {
        if (hex == null || hex.length() == 0) {
            return null;
        }

        char[] hexChars = hex.toCharArray();
        byte[] bytes = new byte[hexChars.length / 2];   // 如果 hex 中的字符不是偶数个, 则忽略最后一个

        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) Integer.parseInt("" + hexChars[i * 2] + hexChars[i * 2 + 1], 16);
        }

        return bytes;
    }
    /**
     * byte[]转十六进制字符串
     *
     * @param array
     *            byte[]
     * @return 十六进制字符串
     */
    public static String byteArrayToHexString(byte[] array) {
        if (array == null) {
            return "";
        }
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < array.length; i++) {
            buffer.append(byteToHex(array[i]));
        }
        return buffer.toString();
    }
    /**
     * byte转十六进制字符
     *
     * @param b
     *            byte
     * @return 十六进制字符
     */
    public static String byteToHex(byte b) {
        String hex = Integer.toHexString(b & 0xFF);
        if (hex.length() == 1) {
            hex = '0' + hex;
        }
        return hex.toUpperCase(Locale.getDefault());
    }

}