package com.msb.service.impl;

import com.msb.mapper.AirTempMapper;
import com.msb.pojo.AirHumi;
import com.msb.pojo.AirTemp;
import com.msb.service.AirTempService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirTempServiceImp implements AirTempService {

    @Autowired
    private AirTempMapper airTempMapper;

    @Override
    public List<AirTemp> findAll() {
        return airTempMapper.findAll();
    }
}
