package com.camaraturismoorosi.apicamaraturismoorosi.jwt;

public class UsernameAndPasswordAuthenticationRequest {

    private String userName;
    private String password;

    public UsernameAndPasswordAuthenticationRequest() {
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
