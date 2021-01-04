/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.app.classes;

import com.mycompany.app.interfaces.PartsInterface;
import com.mycompany.app.interfaces.UsersInterface;
import com.mycompany.app.models.Part;
import com.mycompany.app.models.User;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.sql.Statement;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.util.List;
import java.sql.*; 
import java.util.*;  

/**
 *
 * @author Timax
 */
public class Server extends UnicastRemoteObject implements PartsInterface, UsersInterface {
    
    Registry registry = null;
    String bindName = "Serwery aplikacyjne";
    public Server() throws RemoteException{
        registry = LocateRegistry.createRegistry(4444);
    }
    
    public void startServer() throws RemoteException {
        try {            
            registry.rebind(bindName, this);
            UnicastRemoteObject.exportObject(this, 4444);
        } catch (RemoteException e) {
            System.out.println("Exception" + e);            
        }
    }
    
    public void stopServer() throws RemoteException, AccessException, NotBoundException{
        registry.unbind(bindName);
        UnicastRemoteObject.unexportObject(this, false);
    }
    
    String url = "jdbc:mysql://remotemysql.com:3306/bBx5ctrGX5?zeroDateTimeBehavior=CONVERT_TO_NULL";
    String user = "bBx5ctrGX5";
    String driver = "com.mysql.cj.jdbc.Driver";
    String password = "b8rEvkFkDa";
    Connection connection = null; 
    Statement statement = null; 
    

    @Override
    public List<Part> getAllParts() throws Exception {
        Class.forName(driver);
        connection = DriverManager.getConnection(url, user, password);
        statement = connection.createStatement();
        String sql = "SELECT * FROM Parts";
        ResultSet resultSet = statement.executeQuery(sql);
        List<Part> partList = new ArrayList<Part>();
        while (resultSet.next()){
            Part part = new Part(resultSet.getInt("id"), resultSet.getString("part_number"), resultSet.getString("name"), resultSet.getString("manufacturer"), resultSet.getInt("quantity"));
            User temporaryUser = new User(resultSet.getInt("added_by"), "", "");
            part.setAddedBy(temporaryUser);
            partList.add(part);
        }
        resultSet.close();
        System.out.println("There is a part no " + partList.get(0).getId());
        connection.close();
        return partList;
    }

    @Override
    public List<Part> getPart(String partId) throws Exception {
        Class.forName(driver);
        connection = DriverManager.getConnection(url, user, password);
        statement = connection.createStatement();
        String sql = "SELECT * FROM Parts WHERE part_number=\""+ partId +"\"";
        ResultSet resultSet = statement.executeQuery(sql);
        List<Part> partList = new ArrayList<Part>();
        while (resultSet.next()){
            Part newPart = new Part(resultSet.getInt("id"), resultSet.getString("part_number"), resultSet.getString("name"), resultSet.getString("manufacturer"), resultSet.getInt("quantity"));
            User temporaryUser = new User(resultSet.getInt("added_by"), "", "");
            newPart.setAddedBy(temporaryUser);
            partList.add(newPart);
        }
        resultSet.close();
        connection.close();
        return partList;
    }

    @Override
    public void addPart(Part part) throws Exception {
        Class.forName(driver);
        connection = DriverManager.getConnection(url, user, password);
        statement = connection.createStatement();
        String sql = "INSERT INTO Parts VALUES (\n" +
            "	null,\n" +
            "   \""+ part.getPartNumber() +"\",\n" +
            "   \""+ part.getName() +"\",\n" +
            "   \""+ part.getManufacturer() +"\",\n" +
            "   "+ part.getQuantity() +",\n" +
            "   "+ part.getAddedBy().getId() +"\n" +
            ")";
        statement.executeUpdate(sql);
        connection.close();
    }

    @Override
    public void removePart(int partId) throws Exception {
        Class.forName(driver);
        connection = DriverManager.getConnection(url, user, password);
        statement = connection.createStatement();
        String sql = "DELETE FROM Parts WHERE id="+ String.valueOf(partId);
        statement.executeUpdate(sql);
        connection.close();
    }

    @Override
    public User checkCredentials(String userName, String password) throws Exception {
        System.out.println(userName);
        Class.forName(driver);
        connection = DriverManager.getConnection(url, user, this.password);
        statement = connection.createStatement();
        String sql = "SELECT * FROM Users where login=\""+ userName +"\"";
        ResultSet resultSet = statement.executeQuery(sql);
        User currentUser = null;
        while (resultSet.next()){
            currentUser = new User(resultSet.getInt("id"), resultSet.getString("login"), resultSet.getString("password"));
        }
        System.out.println(currentUser);
        resultSet.close();
        connection.close();
        if (!password.equals(currentUser.getPassword()))
            currentUser = null;
        return currentUser;
    }

    @Override
    public String getUserName(int usrId) throws Exception {
        Class.forName(driver);
        connection = DriverManager.getConnection(url, user, password);
        statement = connection.createStatement();
        String sql = "SELECT login FROM Users where id="+ String.valueOf(usrId);
        ResultSet resultSet = statement.executeQuery(sql);
        String userId ="";
        while (resultSet.next()){
            userId = resultSet.getString("login");
        }
        resultSet.close();
        connection.close();
        return userId;
    }

    @Override
    public Part getSinglePart(int partId) throws Exception {
        Class.forName(driver);
        connection = DriverManager.getConnection(url, user, password);
        statement = connection.createStatement();
        String sql = "SELECT * FROM Parts WHERE id="+ partId;
        ResultSet resultSet = statement.executeQuery(sql);
        Part part = new Part();
        while (resultSet.next()){
            part = new Part(resultSet.getInt("id"), resultSet.getString("part_number"), resultSet.getString("name"), resultSet.getString("manufacturer"), resultSet.getInt("quantity"));
            User temporaryUser = new User(resultSet.getInt("added_by"), "", "");
            part.setAddedBy(temporaryUser);
        }
        resultSet.close();
        connection.close();
        return part;
    }
    
}
