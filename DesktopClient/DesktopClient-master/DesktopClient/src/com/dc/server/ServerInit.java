/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dc.server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kasun
 */
public class ServerInit {

    public static void main(String[] args) {
        try {
            //create rmi registry on port 5052
            Registry registry = LocateRegistry.createRegistry(5052);
            SensorService stub = new SensorServer();    //create server object
            registry.rebind("sensor", stub);    //bind server to registry
            System.out.println("Server Has Been Started");
        } catch (RemoteException ex) {
            Logger.getLogger(ServerInit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
