package ru.khmelev.tm.entity;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.Date;

public abstract class Entity {

    @Setter
    @Getter
    private String id;

    @Setter
    @Getter
    private String name;

    @Setter
    @Getter
    private String description;

    @Getter
    private String userId;

    @NotNull
    public abstract Date getDateCreate();

    @NotNull
    public abstract Date getDateStart();

    @NotNull
    public abstract Date getDateFinish();

    public abstract Status getStatus();
}