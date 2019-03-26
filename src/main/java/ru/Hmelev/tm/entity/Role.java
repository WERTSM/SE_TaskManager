package ru.Hmelev.tm.entity;

public enum Role {
    ADMIN("Админ"), USER("Пользователь");

    String displayName;

    Role(final String displayName) {
        this.displayName = displayName;
    }

    public String displayName() {
        return displayName;
    }
}