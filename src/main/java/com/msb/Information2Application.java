package com.msb;

import com.msb.tool.Client;
import com.msb.tool.NoSuchPort;
import com.msb.tool.SerialPortParameterFailure;
import com.msb.tool.SerialTool;
import com.mysql.cj.jdbc.Driver;
import gnu.io.SerialPort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

@SpringBootApplication
public class Information2Application {

    public static void main(String[] args) throws Exception {
        System.setProperty("java.awt.headless", "false");
        SpringApplication.run(Information2Application.class, args);

        Client.run();


    }

    @Value("${server.port}")
    private String appport;  //站点端口号
    /*当端口启动后，直接跳转界面*/
    @EventListener({ApplicationReadyEvent.class})
    void applicationReadyEvent() {
        String url = "http://localhost:" + appport;
        //url可以直接写死,如 http://localhost:8080
        Runtime runtime = Runtime.getRuntime();
        try {
            runtime.exec("rundll32 url.dll,FileProtocolHandler " + url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
