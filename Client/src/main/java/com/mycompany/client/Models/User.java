/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.client.Models;

/**
 *
 * @author Timax
 */
public class User {

    public Integer id;
    public String login;
    public String password;
    public User(Integer id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }
}
