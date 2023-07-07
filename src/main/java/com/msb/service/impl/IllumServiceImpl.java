package com.msb.service.impl;

import com.msb.mapper.IllumMapper;
import com.msb.pojo.Illum;
import com.msb.service.IllumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IllumServiceImpl implements IllumService {


    @Autowired
    private IllumMapper illumMapper;

    @Override
    public List<Illum> findAll() {
        return illumMapper.findAll();
    }
}
