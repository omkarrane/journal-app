package com.example.springbootjournal.security;

public enum ApplicationUserPermission {
    JOURNAL_READ("journal:read"),
    JOURNAL_WRITE("journal:write");

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
