package com.msb.service.impl;

import com.msb.mapper.SoilHumiMapper;
import com.msb.pojo.SoilHumi;
import com.msb.service.SoilHumiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SoilHumiServiceImpl implements SoilHumiService {

    @Autowired
    private SoilHumiMapper soilHumiMapper;

    @Override
    public List<SoilHumi> findAll() {
        return soilHumiMapper.findAll();
    }
}
