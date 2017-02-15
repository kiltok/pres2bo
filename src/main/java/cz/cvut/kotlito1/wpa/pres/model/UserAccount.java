/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kotlito1.wpa.pres.model;

import cz.cvut.kotlito1.wpa.pres.model.util.UserRoleType;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Tomas Kotlik <kotlito1 at fel.cvut.cz>
 */
@Entity
public class UserAccount implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;
    
    @Column(nullable = false, unique=true)
    private String login;
    
    @Column(nullable = false)
    private String psswd;
    
    @Temporal(TemporalType.DATE)
    private Date lastLoginDate;
    
    @Enumerated(EnumType.STRING)
    private UserRoleType urole;

    public UserAccount() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPsswd() {
        return psswd;
    }

    public void setPsswd(String psswd) {
        this.psswd = psswd;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public UserRoleType getUrole() {
        return urole;
    }

    public void setUrole(UserRoleType urole) {
        this.urole = urole;
    }
    
    
}
