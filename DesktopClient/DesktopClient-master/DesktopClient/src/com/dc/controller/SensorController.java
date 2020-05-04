/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dc.controller;

import com.dc.model.SensorModel;
import com.dc.model.UserModel;
import com.dc.server.ProxyHandler;
import com.dc.server.SensorService;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author kasun
 */
public class SensorController {
    
    //method to invoke server side add sensor method
    public static boolean add(SensorModel se, UserModel um) throws Exception {
        SensorService service = ProxyHandler.getInstance().getService();
        return service.addSensor(se, um);
    }
    
    //method to invoke server side edit sensor method
    public static boolean edit(SensorModel se, UserModel um) throws Exception {
        SensorService service = ProxyHandler.getInstance().getService();
        return service.editSensor(se, um);
    }
    
    //method to invoke server side get all sensors method
    public static ArrayList<SensorModel> get() throws Exception {
        SensorService service = ProxyHandler.getInstance().getService();
        return service.getSensors();
    }
    
    //method to invoke server side get sensor by id method
    public static SensorModel getById(String id) throws Exception {
        SensorService service = ProxyHandler.getInstance().getService();
        return service.getSensorById(id);
    }
    
    //method to invoke server side send alert method
    public static boolean sendAlert(SensorModel sm, UserModel um) throws Exception {
        SensorService service = ProxyHandler.getInstance().getService();
        return service.sendAlert(sm, um);
    }
}
