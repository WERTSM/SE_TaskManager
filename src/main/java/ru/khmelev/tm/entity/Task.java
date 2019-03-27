package ru.khmelev.tm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
public final class Task extends Entity {

    @Setter
    @Getter
    private String id;

    @Setter
    @Getter
    private String name;

    @Setter
    @Getter
    private String description;

    @Setter
    @Getter
    private Date dateStart;

    @Setter
    @Getter
    private Date dateFinish;

    @Setter
    @Getter
    private String idProject;

    @Setter
    @Getter
    private Status status;

    @Setter
    @Getter
    private Date dataCreate;

    @Setter
    @Getter
    private String userId;
}