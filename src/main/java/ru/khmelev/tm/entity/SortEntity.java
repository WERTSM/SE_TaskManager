package ru.khmelev.tm.entity;

import org.jetbrains.annotations.NotNull;

import java.util.Date;

public abstract class SortEntity extends Entity {

    @NotNull
    public abstract Date getDateCreate();

    @NotNull
    public abstract Date getDateStart();

    @NotNull
    public abstract Date getDateFinish();

    @NotNull
    public abstract Status getStatus();
}
