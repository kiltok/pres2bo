/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kotlito1.wpa.pres.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Tomas Kotlik <kotlito1 at fel.cvut.cz>
 */
@Entity
public class Person implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;
    
    @Column(nullable = false)
    private String fname;
    
    @Column(nullable = false)
    private String sname;
    
    @ManyToOne
    private Address address;
    
    @OneToMany(cascade=CascadeType.ALL, fetch= FetchType.EAGER)
    @JoinColumn(name="PERSON_ID")
    private Collection<Contact> contacts;
    
    @OneToOne(cascade=CascadeType.ALL)               //owns
    UserAccount uaccount;

    //ctor
    public Person() {
    }

    public Person(Integer id, String fname, String sname, Address address, Collection<Contact> contacts, UserAccount uaccount) {
        this.id = id;
        this.fname = fname;
        this.sname = sname;
        this.address = address;
        this.contacts = contacts;
        this.uaccount = uaccount;
    }

    //g+s
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Collection<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(Collection<Contact> contacts) {
        this.contacts = contacts;
    }

    public UserAccount getUaccount() {
        return uaccount;
    }

    public void setUaccount(UserAccount uaccount) {
        this.uaccount = uaccount;
    }
   
    
}
