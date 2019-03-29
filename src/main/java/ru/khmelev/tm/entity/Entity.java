package ru.khmelev.tm.entity;

import lombok.Getter;
import lombok.Setter;

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

}