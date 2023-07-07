package com.msb.mapper;

import com.msb.pojo.Illum;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface IllumMapper {

    @Select("select id,farmlandType,location,time,illumination from farmland")
    List<Illum> findAll();
}
