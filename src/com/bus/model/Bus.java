package com.bus.model;
public class Bus{

    private final String busNo;
    private int capacity;
    private final String busType;

    public Bus(String busNo, int capacity, String busType){
        this.busNo = busNo;
        this.capacity = capacity;
        this.busType = busType;
    }

    public String getBusNo(){
        return this.busNo;
    }

    public void setCapacity(int capacity){
        this.capacity = capacity;
    }

    public int getCapacity(){
        return this.capacity;
    }

    public String getBusType(){
        return this.busType;
    }
}