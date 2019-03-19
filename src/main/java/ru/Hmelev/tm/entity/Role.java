package ru.Hmelev.tm.entity;

public enum Role {
    ADMIN("admin"), USER("user");

    private String displayName;

    Role(String displayName) {
        this.displayName = displayName;
    }

    public String displayName() {
        return displayName;
    }
}