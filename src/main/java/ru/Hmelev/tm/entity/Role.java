package ru.Hmelev.tm.entity;

public enum Role {
    ADMIN("admin"), USER("user");

    private String name;

    Role(String name) {
        this.name = name;
    }

    public String displayName() {
        return name;
    }
}