package com.camaraturismoorosi.apicamaraturismoorosi.security;

public enum ApplicationUserPermission {
    DIRECTORY_READ("directory:read"),
    DIRECTORY_WRITE("directory:write");

    private final String permission;
    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }

}
