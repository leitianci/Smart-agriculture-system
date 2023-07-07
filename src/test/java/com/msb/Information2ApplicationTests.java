package com.msb;

import com.msb.mapper.AirHumiMapper;
import com.msb.mapper.IllumMapper;
import com.msb.pojo.AirHumi;
import com.msb.pojo.Illum;
import com.msb.pojo.User;
import com.msb.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class Information2ApplicationTests {





    @Test
    void contextLoads() {
    }

    @Autowired
    private AirHumiMapper airHumiMapper;

    /**
     * airHumiMapper 空气湿度
     */
    @Test
    void selectAll(){
        List<AirHumi> airHumis = airHumiMapper.selectAll();
        airHumis.forEach(System.out::println);
    }


    @Test
    void queryAirHUmiById() {
        List<AirHumi> airHumi = airHumiMapper.selectById(1);
        airHumi.forEach(System.out::println);
    }

    @Autowired
    private UserService userService;

    @Test
    void findUser(){
        User user = userService.findUser("admin","123456");
        System.out.println(user);
    }


    @Autowired
    private IllumMapper illumMapper;

    /**
     * airHumiMapper 空气湿度
     */
    @Test
    void findIllumAll(){
        List<Illum> illums = illumMapper.findAll();
        illums.forEach(System.out::println);
    }

}
