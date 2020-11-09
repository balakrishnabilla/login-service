package com.ms.login.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "USER")
public class UserInfo {
    @Id
    private String userid;
    private String name;
    private String password;
    private String role;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
