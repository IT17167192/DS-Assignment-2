/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dc.model;

import java.io.Serializable;

/**
 *
 * @author kasun
 */
public class SensorModel implements Serializable {
    
    private int id;
    private String floorNo;
    private String roomNo;
    private int smokeLevel;
    private int coLevel;
    private int status;
    private int resId;
    private String resName;
    private UserModel um;
    
    public SensorModel() {
        
    }
    
    public SensorModel(String floorNo, String roomNo, int status) {
        this.floorNo = floorNo;
        this.roomNo = roomNo;
        this.status = status;
    }

    public String getFloorNo() {
        return floorNo;
    }

    public void setFloorNo(String floorNo) {
        this.floorNo = floorNo;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSmokeLevel() {
        return smokeLevel;
    }

    public void setSmokeLevel(int smokeLevel) {
        this.smokeLevel = smokeLevel;
    }

    public int getCoLevel() {
        return coLevel;
    }

    public void setCoLevel(int coLevel) {
        this.coLevel = coLevel;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public String getResName() {
        return resName;
    }

    public void setResName(String resName) {
        this.resName = resName;
    }

    public UserModel getUm() {
        return um;
    }

    public void setUm(UserModel um) {
        this.um = um;
    }
}
