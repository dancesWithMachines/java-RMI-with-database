/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.app.interfaces;

import com.mycompany.app.models.User;
import java.rmi.Remote; 
import java.rmi.RemoteException; 
/**
 *
 * @author Timax
 */
public interface UsersInterface extends Remote {
    public User checkCredentials (String userName, String password) throws Exception;
    public String getUserName (int usrId) throws Exception;
}
