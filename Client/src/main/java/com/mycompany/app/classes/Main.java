/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.app.classes;

import com.mycompany.app.frames.ClientFrame;
import com.mycompany.app.frames.LoginFrame;

/**
 *
 * @author Timax
 */
public class Main {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
          public void run() {
               LoginFrame frame = new LoginFrame();
               frame.setVisible(true);
          }
    }); 
    }
}
