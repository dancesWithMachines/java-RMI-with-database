/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.server.models;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Timax
 */
@Entity
@Table(name = "Parts")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Parts.findAll", query = "SELECT p FROM Parts p"),
    @NamedQuery(name = "Parts.findById", query = "SELECT p FROM Parts p WHERE p.id = :id"),
    @NamedQuery(name = "Parts.findByPartNumber", query = "SELECT p FROM Parts p WHERE p.partNumber = :partNumber"),
    @NamedQuery(name = "Parts.findByName", query = "SELECT p FROM Parts p WHERE p.name = :name"),
    @NamedQuery(name = "Parts.findByManufacturer", query = "SELECT p FROM Parts p WHERE p.manufacturer = :manufacturer"),
    @NamedQuery(name = "Parts.findByQuantity", query = "SELECT p FROM Parts p WHERE p.quantity = :quantity")})
public class Part implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "part_number")
    private String partNumber;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "manufacturer")
    private String manufacturer;
    @Basic(optional = false)
    @Column(name = "quantity")
    private int quantity;
    @JoinColumn(name = "added_by", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User addedBy;

    public Part() {
    }

    public Part(Integer id) {
        this.id = id;
    }

    public Part(Integer id, String partNumber, String name, String manufacturer, int quantity) {
        this.id = id;
        this.partNumber = partNumber;
        this.name = name;
        this.manufacturer = manufacturer;
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public User getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(User addedBy) {
        this.addedBy = addedBy;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Part)) {
            return false;
        }
        Part other = (Part) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.server.models.Parts[ id=" + id + " ]";
    }
    
}
