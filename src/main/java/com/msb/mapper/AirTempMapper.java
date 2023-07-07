package com.msb.mapper;

import com.msb.pojo.AirTemp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AirTempMapper {

    @Select("select id,farmlandType,location,time,airtemperature from farmland")
    List<AirTemp> findAll();
}
