package ru.khmelev.tm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.khmelev.tm.enumeration.Status;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@javax.persistence.Entity
@Table(name = "task")
public class Task extends Entity implements Serializable {

    private String name;

    private String description;

    private Date dateStart;

    private Date dateFinish;

    @JoinColumn(name = "projectId")
    @ManyToOne(fetch = FetchType.LAZY)
    private Project project;

    @Enumerated(EnumType.STRING)
    private Status status;

    private Date dateCreate;
}