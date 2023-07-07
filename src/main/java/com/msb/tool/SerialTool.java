package com.msb.tool;

import gnu.io.*;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.TooManyListenersException;

public class SerialTool {
    static JFrame jf;
    static JPanel jPanel;
    static JLabel jLabel1;
    static JTextArea jTextArea1;
    static JScrollPane jScrollPane1;

    static JLabel jLabel2;
    static JTextArea jTextArea2;
    static JScrollPane jScrollPane2;

    public static void Gui(){
        jf=new JFrame("各类农业传感器实时参数");// 创建一个标题为"JTextArea"的窗口
        jf.setBounds(100, 100, 600, 400);// 设置窗口的坐标和大小

        jPanel = new JPanel();

        jLabel1 = new JLabel("温度");
        jPanel.add(jLabel1);
        jTextArea1 = new JTextArea("123",4,15);
        jTextArea1.setLineWrap(true);
        jScrollPane1 = new JScrollPane(jTextArea1);
        jPanel.add(jScrollPane1);

        jLabel2 = new JLabel("湿度");
        jPanel.add(jLabel2);
        jTextArea2 = new JTextArea("123",4,15);
        jTextArea2.setLineWrap(true);
        jScrollPane2 = new JScrollPane(jTextArea2);
        jPanel.add(jScrollPane2);



        jf.add(jPanel);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);// 设置窗口关闭即退出程序
        jf.setVisible(true);// 设置窗口可见
    };


    /**
     * 查找所有可用端口
     * @return 可用端口名称列表
     */
    public static final ArrayList<String> findPort() {

        //获得当前所有可用串口
        @SuppressWarnings("unchecked")
        Enumeration<CommPortIdentifier> portList = CommPortIdentifier.getPortIdentifiers();
        ArrayList<String> portNameList = new ArrayList<String>();
        //将可用串口名添加到List并返回该List
        while (portList.hasMoreElements()) {
            String portName = portList.nextElement().getName();
            portNameList.add(portName);
        }

        return portNameList;

    }

    /**
     * 打开串口
     * @param portName 端口名称
     * @param baudrate 波特率
     * @throws SerialPortParameterFailure 设置串口参数失败
     * @throws NoSuchPort 没有该端口对应的串口设备
     * @return
     */
    public static final SerialPort openPort(String portName, int baudrate) throws SerialPortParameterFailure, NoSuchPort {

        try {

            //通过端口名识别端口
            CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);

            //打开端口，并给端口名字和一个timeout（打开操作的超时时间）
            CommPort commPort = portIdentifier.open(portName, 2000);

            //判断是不是串口
            if (commPort instanceof SerialPort) {

                SerialPort serialPort = (SerialPort) commPort;

                try {
                    //设置一下串口的波特率等参数
                    serialPort.setSerialPortParams(baudrate, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
                } catch (UnsupportedCommOperationException e) {
                    throw new SerialPortParameterFailure();
                }

                System.out.println("Open " + portName + " successfully !");
                return serialPort;

            }
            else {
                //不是串口
                System.out.println("---Exception : NotASerialPort");
            }
        } catch (NoSuchPortException e1) {
            throw new NoSuchPort();
        } catch (PortInUseException e2) {

            System.out.println("---Exception : PortInUse");
        }
        return null;
    }

    /**
     * 关闭串口
     */
    public static void closePort(SerialPort serialPort) {
        if (serialPort != null) {
            serialPort.close();
            //serialPort = null;

        }
    }

    /**
     * 往串口发送数据
     * @param serialPort 串口对象
     * @param order 待发送数据
     */
    public static void sendToPort(SerialPort serialPort, byte[] order) throws Exception{

        OutputStream out = null;

        try {

            out = serialPort.getOutputStream();
            out.write(order);
            out.flush();
            System.out.println("写入成功");

        } catch (IOException e) {
            System.out.println("SendDataToSerialPortFailure");
        } finally {
            try {
                if (out != null) {
                    out.close();
                    out = null;
                }
            } catch (IOException e) {
                System.out.println("throw new SerialPortOutputStreamCloseFailure();\n");
            }
        }

    }

    /**
     * 从串口读取数据
     * @param serialPort 当前已建立连接的SerialPort对象
     * @return 读取到的数据
     */
    public static byte[] readFromPort(SerialPort serialPort) throws Exception {

        InputStream in = null;
        byte[] bytes = null;

        try {

            in = serialPort.getInputStream();
            int bufflenth = in.available(); //获取buffer里的数据长度

            while (bufflenth != 0) {
                bytes = new byte[bufflenth]; //初始化byte数组为buffer中数据的长度
                in.read(bytes);
                bufflenth = in.available();
            }
        } catch (IOException e) {
            System.out.println("throw new ReadDataFromSerialPortFailure();");

        } finally {
            try {
                if (in != null) {
                    in.close();
                    in = null;
                }
            } catch(IOException e) {
                System.out.println("throw new SerialPortInputStreamCloseFailure();");
            }

        }
        System.out.println("读取成功");
        return bytes;

    }


    /**
     * 添加监听器
     * @param port 串口对象
     * @param listener 串口监听器
     */
    public static void addListener(SerialPort port, DataAvailableListener listener) throws Exception {

        try {

            //给串口添加监听器
            port.addEventListener(new SerialPortListener(listener));
            //设置当有数据到达时唤醒监听接收线程
            port.notifyOnDataAvailable(true);
            //设置当通信中断时唤醒中断线程
            port.notifyOnBreakInterrupt(true);

        } catch (TooManyListenersException e) {
            System.out.println("throw new TooManyListeners();\n");
        }
    }
    /**
     * 串口监听
     */
    public static class SerialPortListener implements SerialPortEventListener {

        private DataAvailableListener mDataAvailableListener;

        public SerialPortListener(DataAvailableListener mDataAvailableListener) {
            this.mDataAvailableListener = mDataAvailableListener;
        }

        public void serialEvent(SerialPortEvent serialPortEvent) {
            switch (serialPortEvent.getEventType()) {
                case SerialPortEvent.DATA_AVAILABLE: // 1.串口存在有效数据
                    if (mDataAvailableListener != null) {
                        mDataAvailableListener.dataAvailable();
                    }
                    break;

                case SerialPortEvent.OUTPUT_BUFFER_EMPTY: // 2.输出缓冲区已清空
                    break;

                case SerialPortEvent.CTS: // 3.清除待发送数据
                    break;

                case SerialPortEvent.DSR: // 4.待发送数据准备好了
                    break;

                case SerialPortEvent.RI: // 5.振铃指示
                    break;

                case SerialPortEvent.CD: // 6.载波检测
                    break;

                case SerialPortEvent.OE: // 7.溢位（溢出）错误
                    break;

                case SerialPortEvent.PE: // 8.奇偶校验错误
                    break;

                case SerialPortEvent.FE: // 9.帧错误
                    break;

                case SerialPortEvent.BI: // 10.通讯中断
                    System.out.println("与串口设备通讯中断");
                    break;

                default:
                    break;
            }
        }
    }

    /**
     * 串口存在有效数据监听
     */
    public interface DataAvailableListener {
        /**
         * 串口存在有效数据
         */
        void dataAvailable();
    }


}