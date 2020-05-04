/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dc.server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import com.dc.model.SensorModel;
import com.dc.model.UserModel;
import java.util.ArrayList;

/**
 *
 * @author kasun
 */
public interface SensorService extends Remote {
    
    //server side method to add sensors
    public boolean addSensor(SensorModel se, UserModel um) throws RemoteException;
    
    //method to edit sensors
    public boolean editSensor(SensorModel se, UserModel um) throws RemoteException;
    
    //method to get all sensors
    public ArrayList<SensorModel> getSensors() throws RemoteException;
    
    //method to get a sensor by id
    public SensorModel getSensorById(String id) throws RemoteException;
    
    //method to login
    public boolean login(UserModel um) throws RemoteException;
    
    //method to get all users
    public ArrayList<UserModel> getUsers() throws RemoteException;
    
    //method to get a user by id
    public UserModel getUserById(int id) throws RemoteException;
    
    //method to send alerts
    public boolean sendAlert(SensorModel sm, UserModel um) throws RemoteException;
}
