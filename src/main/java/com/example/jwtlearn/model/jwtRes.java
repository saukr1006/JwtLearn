package com.example.jwtlearn.model;

public class jwtRes {

    String token;

    public jwtRes() {
    }

    public jwtRes(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
