package com.msb.controller;

import com.msb.pojo.AirHumi;
import com.msb.pojo.AirTemp;
import com.msb.pojo.Illum;
import com.msb.pojo.SoilHumi;
import com.msb.service.AirHumiService;
import com.msb.service.AirTempService;
import com.msb.service.IllumService;
import com.msb.service.SoilHumiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 *
 */
@Controller
public class HomeController {


    //空气湿度
    @Autowired
    private AirHumiService airHumiService;

    @RequestMapping("/showAirHumi")
    public String showAirHumi(Map<String, Object> map){
        List<AirHumi> airHumiList = airHumiService.findAll();
        map.put("airHumiList", airHumiList);
        map.put("airHumi", airHumiList.get(0));
        return "showAirHumi";
    }


    /**
     * 空气温度
     */
    @Autowired
    private AirTempService airTempService;

    @RequestMapping("/showAirTemp")
    public String showAirTemp(Map<String, Object> map){
        List<AirTemp> airTempList = airTempService.findAll();
        map.put("airTempList", airTempList);
        map.put("airTemp", airTempList.get(0));
        return "showAirTemp";
    }


    @Autowired
    private IllumService illumService;

    @RequestMapping("/showIllum")
    public String showIllum(Map<String, Object> map){
        List<Illum> illumList = illumService.findAll();
        map.put("illumList",illumList);
        map.put("illum",illumList.get(0));
        return "showIllum";
    }

    //土壤湿度
    @Autowired
    private SoilHumiService soilHumiService;

    @RequestMapping("/showSoilHumi")
    public String showSoilHumi(Map<String, Object> map){
        List<SoilHumi> soilHumiList = soilHumiService.findAll();
        map.put("soilHumiList", soilHumiList);
        map.put("soilHumi", soilHumiList.get(0));
        return "showSoilHumi";
    }



}
