package com.webapp.ui.model;

import java.io.Serializable;

public class AuthenticationResponse implements Serializable {

//    private String type = "Bearer";
    private int id;
    private String username;
    private String role;
    private User user;


    private final String jwt;

    public AuthenticationResponse(String jwt, int id, String username, String role) {
        this.jwt = jwt;
        this.id = id;
        this.username = username;
        this.role = role;
    }

    public AuthenticationResponse(String jwt, User user) {
        this.jwt = jwt;
        this.user = user;
    }

    public String getJwt() {
        return jwt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public User getUser() {
        return user;
    }
}
