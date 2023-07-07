package com.msb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class ThymeleafController {

    @RequestMapping("showIndex")
    public String showIndex(Map<String,Object> map){
        map.put("msg","testMessage");
        return "index";
    }
}
