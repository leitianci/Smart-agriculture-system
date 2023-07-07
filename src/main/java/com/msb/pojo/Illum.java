package com.msb.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 光照强度
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Illum {
    private int id;
    private String farmlandType;
    private String farmer;
    private String location;
    private String time;
    private float illumination;
}
