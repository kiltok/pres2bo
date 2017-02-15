/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kotlito1.wpa.pres.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author Tomas Kotlik <kotlito1 at fel.cvut.cz>
 */
@Entity
public class City implements Serializable{
    @Id
    @GeneratedValue
    private Integer id;
    
    @Column(nullable = false)
    private String name;
    @Column(nullable = true)
    private String zip;

    public City() {
    }

    public City(Integer id, String name, String zip) {
        this.id = id;
        this.name = name;
        this.zip = zip;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null || City.class != obj.getClass())
            return false;
        if (this == obj)
            return true;
        return this.name.equalsIgnoreCase(((City) obj).getName());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.name);
        return hash;
    }
    
    
}
