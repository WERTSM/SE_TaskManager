package ru.khmelev.tm.entity.enumeration;

import org.jetbrains.annotations.NotNull;

public enum Role {

    ADMIN("ADMIN"), USER("USER");

    String displayName;

    Role(@NotNull final String displayName) {
        this.displayName = displayName;
    }

    @NotNull
    public String displayName() {
        return displayName;
    }
}