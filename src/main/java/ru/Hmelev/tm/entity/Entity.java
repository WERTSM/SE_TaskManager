package ru.Hmelev.tm.entity;

import org.jetbrains.annotations.NotNull;

public abstract class Entity {
    @NotNull
    public abstract String getId();

    @NotNull
    abstract String getDescription();

    abstract void setDescription(String description);

    @NotNull
    public abstract String getUserId();
}