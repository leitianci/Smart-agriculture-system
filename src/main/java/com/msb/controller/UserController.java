package com.msb.controller;

import com.msb.pojo.User;
import com.msb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("login")
    public String login(String name,String password){
        String view = null;
        User user = userService.findUser(name,password);
        if(null != user){
            view = "home";//登陆成功
        }else{
            view = "fail";
        }
        return view;
    }
}
