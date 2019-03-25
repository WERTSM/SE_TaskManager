package ru.Hmelev.tm.entity;

public abstract class Entity {
    public abstract String getId();

    abstract void setId(String id);

    abstract String getDescription();

    abstract void setDescription(String description);

    public abstract String getUserId();
}