package com.msb.mapper;

import com.msb.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("select * from user where name =#{param1} and password =#{param2}")
    User findUser(String name, String password);

}
