package com.msb.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 空气温度
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AirTemp {
    private int id;
    private String farmlandType;
    private String farmer;
    private String location;
    private String time;
    private float airTemperature;
}
