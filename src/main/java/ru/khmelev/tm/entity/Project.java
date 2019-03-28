package ru.khmelev.tm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
public class Project extends Entity {

    private String id;

    private String name;

    private String description;

    private Date dateStart;

    private Date dateFinish;

    private Date dateCreate;

    private Status status;

    private String userId;
}