package com.camaraturismoorosi.apicamaraturismoorosi.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.google.firebase.database.annotations.NotNull;

public class User {

    private final String userId;
    @NotNull
    @NotBlank(message = "El nombre de usuario no puede estar vacio")
    private final String userName;
    @Email(message = "Correo inválido")
    @NotNull
    @NotBlank(message = "El correo de unuario no puede estar vacio")
    private final String userEmail;
    @Size(min = 6, message = "La contraseña debe tener como mínimo 6 caracteres")
    private String password;
    private final String role;

    public User(String userId, String userName, String userEmail, String password, String role) {
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.password = password;
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "user {" + "userId=" + userId +", userName='" + userName + '\'' + '}';
    }

}
