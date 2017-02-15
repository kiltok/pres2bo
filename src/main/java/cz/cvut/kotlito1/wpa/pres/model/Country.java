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
public class Country  implements Serializable{
    @Id
    @GeneratedValue
    private Integer id;
    
    @Column(nullable=true)
    private String name;
    @Column(nullable=false, unique=true)
    private String abrev;

    public Country() {
    }

    public Country(Integer id, String name, String abrev) {
        this.id = id;
        this.name = name;
        this.abrev = abrev;
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

    public String getAbrev() {
        return abrev;
    }

    public void setAbrev(String abrev) {
        this.abrev = abrev;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.abrev);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Country other = (Country) obj;
        if (!Objects.equals(this.abrev, other.abrev)) {
            return false;
        }
        return true;
    }
    
    
    
}
