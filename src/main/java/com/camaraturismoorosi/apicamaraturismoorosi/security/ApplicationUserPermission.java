package com.camaraturismoorosi.apicamaraturismoorosi.security;

public enum ApplicationUserPermission {
    DIRECTORY_READ("directory:read"),
    DIRECTORY_WRITE("directory:write"),
    USER_WRITE("user:write"),
    USER_READ("user:read"),
    PROMOTION_READ("promotion:read"),
    PROMOTION_WRITE("promotion:write"),
    CONFIGURATION_WRITE("configuration:write"),
    CONFIGURATION_READ("configuration:read");

    private final String permission;
    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }

}
