package ru.khmelev.tm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
public final class Task extends SortEntity implements Serializable {

    private String id;

    private String name;

    private String description;

    private Date dateStart;

    private Date dateFinish;

    private String idProject;

    private Status status;

    private Date dateCreate;

    private String userId;
}