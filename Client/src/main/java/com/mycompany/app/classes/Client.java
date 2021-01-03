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
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Timax
 */
public class Client {
    
    private Registry registry = null;
    private PartsInterface parts= null;
    private UsersInterface users= null;
    
    private User user = null;
    
    public Client() throws RemoteException, NotBoundException {
        //Client c = new Client();
        this.connectRemote();
        parts = (PartsInterface) registry.lookup("hi serwer");
        users = (UsersInterface) registry.lookup("hi serwer"); 
    }

    private void connectRemote() throws RemoteException {
        registry = LocateRegistry.getRegistry("localhost", 4444);
    }
    
    public List<Part> getAllParts() throws Exception {
       if (user != null) 
            return parts.getAllParts();
       else
           throw new Exception("NO USER");
    }
    
    public Part getPart(int partId) throws Exception{
        if (user != null)
            return parts.getPart(partId);
        else
            throw new Exception("NO USER");
    }
    
    public void addPart(Part part) throws Exception{
        if (user == null)
            throw new Exception("NO USER");
        parts.addPart(part, user);
    }
    
    public void removePart (int partId) throws Exception{
        if (user != null)
            parts.removePart(partId);
    }
    
    public void checkCredentials (String login, String password) throws Exception{
        user = users.checkCredentials(login, password);
    }
    
    public String getUserName (int usrId) throws Exception{
        return users.getUserName(usrId);
    }
}
