package com.camaraturismoorosi.apicamaraturismoorosi.user;

public class User {

    private final Integer userId;
    private final String userName;
    private final String email;

    public User(Integer userId, String userName, String email) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "user {" + "userId=" + userId +", userName='" + userName + '\'' + '}';
    }

}
