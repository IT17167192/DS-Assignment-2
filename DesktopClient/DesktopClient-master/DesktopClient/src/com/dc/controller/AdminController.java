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

/**
 *
 * @author kasun
 */
public class AdminController {
    
    //method to invoke login method in server side
    public static boolean login(UserModel um) throws Exception {
        SensorService service = ProxyHandler.getInstance().getService();
        return service.login(um);
    }
    
    //method to invoke get all users method
    public static ArrayList<UserModel> getUsers() throws Exception {
        SensorService service = ProxyHandler.getInstance().getService();
        return service.getUsers();
    }
    
    //method to invoke get user by id method
    public static UserModel userById(int id) throws Exception {
        SensorService service = ProxyHandler.getInstance().getService();
        return service.getUserById(id);
    }
}