/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dc.server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author kasun
 */
public class ProxyHandler {
    
    private static ProxyHandler handler;
    private SensorService service;
    
    //make sure only create one object from proxy handler using singleton
    public static ProxyHandler getInstance() throws Exception {
        if (handler == null) {
            handler = new ProxyHandler();
        }
        return handler;
    }
    
    private ProxyHandler() throws Exception {
        //get binded registry and lookup for sensor
        Registry reg = LocateRegistry.getRegistry("localhost", 5052);
        service = (SensorService) reg.lookup("sensor");
    }
    
    public SensorService getService() throws RemoteException {
        
        if (service == null) {
            service = new SensorServer();
        }
        return service;
    }
}
