package com.msb.service;


import com.msb.pojo.User;

public interface UserService {
    User findUser(String name, String password);
}
