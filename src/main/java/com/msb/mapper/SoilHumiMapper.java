package com.msb.mapper;

import com.msb.pojo.SoilHumi;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SoilHumiMapper {

    @Select("select id,farmlandType,location,time,soilhumidity from farmland")
    List<SoilHumi> findAll();

    @Select("select * from soilHumidity")
    List<SoilHumi> selectAll();

    @Select("select * from soilHumidity where id = #{id}")
    List<SoilHumi> selectById(int id);
}
