package com.camaraturismoorosi.apicamaraturismoorosi.security;

public enum ApplicationUserPermission {
    DIRECTORY_READ("directory:read"),
    DIRECTORY_WRITE("directory:write"),
    USER_WRITE("user:write"),
    USER_READ("user:read");

    private final String permission;
    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }

}
