package ru.khmelev.tm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.khmelev.tm.enumeration.Status;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@javax.persistence.Entity
@Table(name = "project")
public class Project extends Entity implements Serializable {

    private String name;

    private String description;

    private Date dateStart;

    private Date dateFinish;

    private Date dateCreate;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Task> tasks;
}