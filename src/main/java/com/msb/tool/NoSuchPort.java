package com.msb.tool;

public class NoSuchPort extends Exception {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Override
    public String toString() {
        return "没有该端口对应的串口设备！";
    }
}