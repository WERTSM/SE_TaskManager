package ru.Hmelev.tm.entity;

import org.jetbrains.annotations.NotNull;

public enum Role {
    ADMIN("Админ"), USER("Пользователь");

    String displayName;

    Role(@NotNull final String displayName) {
        this.displayName = displayName;
    }

    @NotNull
    public String displayName() {
        return displayName;
    }
}