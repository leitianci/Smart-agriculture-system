package com.msb.mapper;

import com.msb.pojo.AirHumi;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AirHumiMapper {

    @Select("select id,farmlandType,location,time,airhumidity from farmland")
    List<AirHumi> findAll();

    @Select("select * from airHumidity")
    List<AirHumi> selectAll();

    @Select("select * from airHumidity where id = #{id}")
    List<AirHumi> selectById(int id);
}
