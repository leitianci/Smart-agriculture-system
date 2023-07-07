package com.msb.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 土壤湿度
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SoilHumi {
    private int id;
    private String farmlandType;
    private String farmer;
    private String location;
    private String time;
    private float soilHumidity;
}
