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
    
    public Server() throws RemoteException {
        try {
            Registry reg = LocateRegistry.createRegistry(4444);
            reg.rebind("hi serwer", this);
        } catch (RemoteException e) {
            System.out.println("Exception" + e);
        }
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
            partList.add(part);
        }
        resultSet.close();
        System.out.println("There is a part no " + partList.get(0).getId());
        connection.close();
        return partList;
    }

    @Override
    public Part getPart(int partId) throws Exception {
        Class.forName(driver);
        connection = DriverManager.getConnection(url, user, password);
        statement = connection.createStatement();
        String sql = "SELECT * FROM Parts WHERE part_number=\""+String.valueOf(partId) +"\"";
        ResultSet resultSet = statement.executeQuery(sql);
        Part part = null;
        while (resultSet.next()){
            part = new Part(resultSet.getInt("id"), resultSet.getString("part_number"), resultSet.getString("name"), resultSet.getString("manufacturer"), resultSet.getInt("quantity"));
        }
        resultSet.close();
        connection.close();
        return part;
    }

    @Override
    public void addPart(Part part, User creator) throws Exception {
        Class.forName(driver);
        connection = DriverManager.getConnection(url, user, password);
        statement = connection.createStatement();
        String sql = "INSERT INTO Parts VALUES (\n" +
            "	null,\n" +
            "   \""+ part.getPartNumber() +"\",\n" +
            "   \""+ part.getName() +"\",\n" +
            "   \""+ part.getManufacturer() +"\",\n" +
            "   "+ part.getQuantity() +",\n" +
            "   "+ creator.getId() +"\n" +
            ")";
        ResultSet resultSet = statement.executeQuery(sql);
        connection.close();
    }

    @Override
    public void removePart(int partId) throws Exception {
        Class.forName(driver);
        connection = DriverManager.getConnection(url, user, password);
        statement = connection.createStatement();
        String sql = "DELETE FROM Parts WHERE id=\""+ String.valueOf(partId) +"\"";
        ResultSet resultSet = statement.executeQuery(sql);
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
    
}
