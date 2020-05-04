/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dc.server;

import java.rmi.Naming;
import java.rmi.RemoteException;
import com.dc.model.SensorModel;
import com.dc.model.UserModel;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author kasun
 */
public class SensorServer extends UnicastRemoteObject implements SensorService {

    public SensorServer() throws RemoteException {

    }

    //method to add sensors
    @Override
    public boolean addSensor(SensorModel se, UserModel um) throws RemoteException {
        try {
            //put given user details from the client side
            JSONObject userDetails = new JSONObject();
            userDetails.put("id", um.getId());
            userDetails.put("name", um.getName());
            userDetails.put("userName", um.getUsername());
            userDetails.put("password", um.getPassword());
            userDetails.put("email", um.getEmail());
            userDetails.put("mobile", um.getMobile());

            //add all user and sensor details to one object
            JSONObject sensorDetails = new JSONObject();
            sensorDetails.put("id", se.getId());
            sensorDetails.put("roomNo", se.getRoomNo());
            sensorDetails.put("floorNo", se.getFloorNo());
            sensorDetails.put("status", se.getStatus());
            sensorDetails.put("responsiblePerson", userDetails);

            JSONObject sensorObject = new JSONObject();
            sensorObject.put("Sensor", sensorDetails);

            URL url;
            try {
                url = new URL("http://localhost:8080/api/sensor");  //api url to save sensor details
                HttpURLConnection con = (HttpURLConnection) url.openConnection();   //set connection
                con.setRequestMethod("POST");   //set rest method as post
                con.setRequestProperty("Content-Type", "application/json; utf-8");
                con.setRequestProperty("Accept", "application/json");   //set headers
                con.setDoOutput(true);

                //convert input data to string
                String jsonInputString = sensorDetails.toString();

                //save input data
                try (OutputStream os = con.getOutputStream()) {
                    byte[] input = jsonInputString.getBytes("utf-8");
                    os.write(input, 0, input.length);
                }

                try (BufferedReader br = new BufferedReader(
                        new InputStreamReader(con.getInputStream(), "utf-8"))) {
                    StringBuilder response = new StringBuilder();
                    String responseLine = null;
                    while ((responseLine = br.readLine()) != null) {
                        response.append(responseLine.trim());
                    }
                    System.out.println(response.toString());
                }
                con.disconnect();   //disconnect the connection

            } catch (MalformedURLException ex) {
                Logger.getLogger(SensorServer.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(SensorServer.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (JSONException ex) {
            Logger.getLogger(SensorServer.class.getName()).log(Level.SEVERE, null, ex);
        }

        return true;
    }

    //method to add sensors
    @Override
    public boolean editSensor(SensorModel se, UserModel um) throws RemoteException {
        try {
            //put given user details from the client side
            JSONObject userDetails = new JSONObject();
            userDetails.put("id", um.getId());
            userDetails.put("name", um.getName());
            userDetails.put("userName", um.getUsername());
            userDetails.put("password", um.getPassword());
            userDetails.put("email", um.getEmail());
            userDetails.put("mobile", um.getMobile());

            //add all user and sensor details to one object
            JSONObject sensorDetails = new JSONObject();
            sensorDetails.put("id", se.getId());
            sensorDetails.put("roomNo", se.getRoomNo());
            sensorDetails.put("floorNo", se.getFloorNo());
            sensorDetails.put("status", se.getStatus());
            sensorDetails.put("responsiblePerson", userDetails);

            JSONObject sensorObject = new JSONObject();
            sensorObject.put("Sensor", sensorDetails);

            URL url;
            try {
                url = new URL("http://localhost:8080/api/sensor/" + se.getId());    //api url to update sensor details
                HttpURLConnection con = (HttpURLConnection) url.openConnection();   //set connection
                con.setRequestMethod("PUT");    //set rest method as put
                con.setRequestProperty("Content-Type", "application/json; utf-8");
                con.setRequestProperty("Accept", "application/json");   //set headers
                con.setDoOutput(true);

                //convert input data to string
                String jsonInputString = sensorDetails.toString();

                //save input data
                try (OutputStream os = con.getOutputStream()) {
                    byte[] input = jsonInputString.getBytes("utf-8");
                    os.write(input, 0, input.length);
                }

                try (BufferedReader br = new BufferedReader(
                        new InputStreamReader(con.getInputStream(), "utf-8"))) {
                    StringBuilder response = new StringBuilder();
                    String responseLine = null;
                    while ((responseLine = br.readLine()) != null) {
                        response.append(responseLine.trim());
                    }
                    System.out.println(response.toString());
                }
                con.disconnect();   //disconnect the connection

            } catch (MalformedURLException ex) {
                Logger.getLogger(SensorServer.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(SensorServer.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (JSONException ex) {
            Logger.getLogger(SensorServer.class.getName()).log(Level.SEVERE, null, ex);
        }

        return true;
    }

    //method to get all sensors
    @Override
    public ArrayList<SensorModel> getSensors() throws RemoteException {
        URL obj;
        ArrayList<SensorModel> list = null; //array to save data
        try {
            obj = new URL("http://localhost:8080/api/sensors"); //rest url to get sensor details

            HttpURLConnection con = (HttpURLConnection) obj.openConnection();   //set connection
            con.setRequestMethod("GET");    //set rest method as get
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");   //set headers
            
            //get conncetion response code
            int responseCode = con.getResponseCode();
            System.out.println("GET Response Code :: " + responseCode);
            
            //if conncetion success, retrieve data
            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                //convert output data to string and make JSON array
                String result = response.toString();
                JSONArray array = new JSONArray(result);

                list = new ArrayList<>();

                //loop through json array
                for (int i = 0; i < array.length(); i++) {
                    JSONObject jsonObj = array.getJSONObject(i);

                    //set data to sensor object
                    SensorModel sm = new SensorModel();
                    sm.setId(jsonObj.getInt("id"));
                    sm.setFloorNo(String.valueOf(jsonObj.getInt("floorNo")));
                    sm.setRoomNo(String.valueOf(jsonObj.getInt("roomNo")));
                    sm.setSmokeLevel(jsonObj.getInt("smokeLevel"));
                    sm.setCoLevel(jsonObj.getInt("co2Level"));
                    sm.setStatus(jsonObj.getInt("status"));

                    JSONObject jsonObj1 = jsonObj.getJSONObject("responsiblePerson");

                    //set user data to user object
                    UserModel um = new UserModel();
                    um.setId(jsonObj1.getInt("id"));
                    um.setName(jsonObj1.getString("name"));
                    um.setUsername(jsonObj1.getString("userName"));
                    um.setPassword(jsonObj1.getString("password"));
                    um.setEmail(jsonObj1.getString("email"));
                    um.setMobile(jsonObj1.getString("mobile"));

                    sm.setUm(um);

                    list.add(sm);
                }

            } else {
                System.out.println("Error Occured");
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(SensorServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ProtocolException ex) {
            Logger.getLogger(SensorServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SensorServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(SensorServer.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;    //return sensor data to client side
    }

    //method to get sensor by id
    @Override
    public SensorModel getSensorById(String id) throws RemoteException {
        URL obj;
        SensorModel sm = new SensorModel(); //create an sensor object
        try {
            obj = new URL("http://localhost:8080/api/sensor/" + id);    //rest url to get sensor by id details

            HttpURLConnection con = (HttpURLConnection) obj.openConnection();   //set connection
            con.setRequestMethod("GET");    //set rest method as get
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");   //set headers
            
            //get conncetion response code
            int responseCode = con.getResponseCode();
            System.out.println("GET Response Code :: " + responseCode);
            
            //if conncetion success, retrieve data
            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                if ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                //convert output data to string
                String result = response.toString();

                //set output sensor and user details to the sensor object
                JSONObject jsonObj = new JSONObject(result);
                sm.setId(jsonObj.getInt("id"));
                sm.setFloorNo(String.valueOf(jsonObj.getInt("floorNo")));
                sm.setRoomNo(String.valueOf(jsonObj.getInt("roomNo")));
                sm.setStatus(jsonObj.getInt("status"));

                JSONObject jsonUser = jsonObj.getJSONObject("responsiblePerson");
                sm.setResId(jsonUser.getInt("id"));
                sm.setResName(jsonUser.getString("name"));

            } else {
                System.out.println("Error Occured");
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(SensorServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ProtocolException ex) {
            Logger.getLogger(SensorServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SensorServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(SensorServer.class.getName()).log(Level.SEVERE, null, ex);
        }

        return sm;  //return data to client side
    }

    //method to login users
    @Override
    public boolean login(UserModel um) throws RemoteException {
        URL obj;
        try {
            obj = new URL("http://localhost:8080/api/users");   //rest url to login users

            HttpURLConnection con = (HttpURLConnection) obj.openConnection();   //set conncetion
            con.setRequestMethod("GET");    //set rest method as get
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");   //set headers
            
            //get conncetion response code
            int responseCode = con.getResponseCode();
            System.out.println("GET Response Code :: " + responseCode);
            
            //if conncetion success, retrieve data
            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                //convert output data to string and make JSON array
                String result = response.toString();
                JSONArray array = new JSONArray(result);

                //loop through json array while given username and password matches
                for (int i = 0; i < array.length(); i++) {
                    JSONObject jsonObj = array.getJSONObject(i);
                    
                    //if matches return true
                    if (jsonObj.getString("userName").equals(um.getUsername())
                            && jsonObj.getString("password").equals(um.getPassword())) {
                        return true;
                    }
                }

            } else {
                System.out.println("Error Occured");
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(SensorServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ProtocolException ex) {
            Logger.getLogger(SensorServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SensorServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(SensorServer.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;   //if not matches return false
    }

    //method to get all users
    @Override
    public ArrayList<UserModel> getUsers() throws RemoteException {
        URL obj;
        ArrayList<UserModel> list = null;
        try {
            obj = new URL("http://localhost:8080/api/users");   //rest url to get all users

            HttpURLConnection con = (HttpURLConnection) obj.openConnection();   //set connection
            con.setRequestMethod("GET");    //set rest method as get
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");   //set headers
            
            //get connection response code
            int responseCode = con.getResponseCode();
            System.out.println("GET Response Code :: " + responseCode);
            
            //if conncetion success, retrieve data
            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                //convert output data to string and make JSON array
                String result = response.toString();
                JSONArray array = new JSONArray(result);

                list = new ArrayList<>();

                //loop through json array while given username and password matches
                for (int i = 0; i < array.length(); i++) {
                    JSONObject jsonObj = array.getJSONObject(i);

                    UserModel um = new UserModel();
                    um.setId(jsonObj.getInt("id"));
                    um.setName(jsonObj.getString("name"));
                    um.setUsername(jsonObj.getString("userName"));
                    um.setPassword(jsonObj.getString("password"));
                    um.setEmail(jsonObj.getString("email"));
                    um.setMobile(jsonObj.getString("mobile"));

                    list.add(um);
                }

            } else {
                System.out.println("Error Occured");
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(SensorServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ProtocolException ex) {
            Logger.getLogger(SensorServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SensorServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(SensorServer.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    //method to get users by id
    @Override
    public UserModel getUserById(int id) throws RemoteException {
        URL obj;
        try {
            obj = new URL("http://localhost:8080/api/users");   //rest url to get users by id

            HttpURLConnection con = (HttpURLConnection) obj.openConnection();   //set connection
            con.setRequestMethod("GET");    //set rest method as get
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");   //set headers
            
            //get connection response code
            int responseCode = con.getResponseCode();
            System.out.println("GET Response Code :: " + responseCode);
            
            //if conncetion success, retrieve data
            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                //convert output data to string and make JSON array
                String result = response.toString();
                JSONArray array = new JSONArray(result);

                //loop through json array
                for (int i = 0; i < array.length(); i++) {
                    JSONObject jsonObj = array.getJSONObject(i);

                    //if given id is equals retrieve only those data
                    if (jsonObj.getInt("id") == id) {
                        UserModel um = new UserModel();

                        um.setId(jsonObj.getInt("id"));
                        um.setName(jsonObj.getString("name"));
                        um.setUsername(jsonObj.getString("userName"));
                        um.setPassword(jsonObj.getString("password"));
                        um.setEmail(jsonObj.getString("email"));
                        um.setMobile(jsonObj.getString("mobile"));

                        return um; //return object
                    }
                }

            } else {
                System.out.println("Error Occured");
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(SensorServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ProtocolException ex) {
            Logger.getLogger(SensorServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SensorServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(SensorServer.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    //method to send alerts
    @Override
    public boolean sendAlert(SensorModel se, UserModel um) throws RemoteException {
        try {
            //put given user details from the client side
            JSONObject userDetails = new JSONObject();
            userDetails.put("id", um.getId());
            userDetails.put("name", um.getName());
            userDetails.put("userName", um.getUsername());
            userDetails.put("password", um.getPassword());
            userDetails.put("email", um.getEmail());
            userDetails.put("mobile", um.getMobile());

            //add all user and sensor details to one object
            JSONObject sensorDetails = new JSONObject();
            sensorDetails.put("id", se.getId());
            sensorDetails.put("roomNo", se.getRoomNo());
            sensorDetails.put("floorNo", se.getFloorNo());
            sensorDetails.put("status", se.getStatus());
            sensorDetails.put("smokeLevel", se.getSmokeLevel());
            sensorDetails.put("co2Level", se.getCoLevel());
            sensorDetails.put("responsiblePerson", userDetails);

            JSONObject sensorObject = new JSONObject();
            sensorObject.put("Sensor", sensorDetails);

            URL url;
            try {
                url = new URL("http://localhost:8080/api/sensor/sendAlert");    //rest url to send alerts
                HttpURLConnection con = (HttpURLConnection) url.openConnection();   //set connection
                con.setRequestMethod("POST");    //set rest method as post
                con.setRequestProperty("Content-Type", "application/json; utf-8");
                con.setRequestProperty("Accept", "application/json");   //set headers
                con.setDoOutput(true);

                //convert input data to string
                String jsonInputString = sensorDetails.toString();

                //save input data
                try (OutputStream os = con.getOutputStream()) {
                    byte[] input = jsonInputString.getBytes("utf-8");
                    os.write(input, 0, input.length);
                }

                try (BufferedReader br = new BufferedReader(
                        new InputStreamReader(con.getInputStream(), "utf-8"))) {
                    StringBuilder response = new StringBuilder();
                    String responseLine = null;
                    while ((responseLine = br.readLine()) != null) {
                        response.append(responseLine.trim());
                    }
                    System.out.println(response.toString());
                }
                con.disconnect();

            } catch (MalformedURLException ex) {
                Logger.getLogger(SensorServer.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(SensorServer.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (JSONException ex) {
            Logger.getLogger(SensorServer.class.getName()).log(Level.SEVERE, null, ex);
        }

        return true;
    }
}
