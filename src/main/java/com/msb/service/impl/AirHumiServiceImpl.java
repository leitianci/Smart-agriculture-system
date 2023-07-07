package com.msb.service.impl;

import com.msb.mapper.AirHumiMapper;
import com.msb.pojo.AirHumi;
import com.msb.service.AirHumiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirHumiServiceImpl implements AirHumiService {

    @Autowired
    private AirHumiMapper airHumiMapper;

    @Override
    public List<AirHumi> findAll() {
        return airHumiMapper.findAll();
    }
}
