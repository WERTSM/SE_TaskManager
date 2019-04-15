package ru.khmelev.tm.entity;

import lombok.Getter;
import lombok.Setter;


public abstract class Identifiable {

    @Getter
    @Setter
    private String id;

    @Getter
    private String userId;
}