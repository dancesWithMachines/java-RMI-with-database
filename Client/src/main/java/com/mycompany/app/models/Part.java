/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.app.models;

/**
 *
 * @author Timax
 */
public class Part {

    public String partNumber;
    public Integer id;
    public String name;
    public String manufacturer;
    public int quantity;
    public Part(Integer id, String partNumber, String name, String manufacturer, int quantity) {
        this.id = id;
        this.partNumber = partNumber;
        this.name = name;
        this.manufacturer = manufacturer;
        this.quantity = quantity;
    }
}
