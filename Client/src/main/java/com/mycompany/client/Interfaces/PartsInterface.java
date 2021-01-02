/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.client.Interfaces;

import com.mycompany.client.Models.Part;
import com.mycompany.client.Models.User;
import java.util.*;
import java.rmi.Remote; 
import java.rmi.RemoteException; 
/**
 *
 * @author Timax
 */
public interface PartsInterface extends Remote {
    public List<Part> getAllParts() throws Exception;
    public Part getPart(int partId) throws Exception;
    public void addPart(Part part, User creator) throws Exception;
    public void removePart(int partId) throws Exception;
}
