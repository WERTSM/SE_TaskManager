package ru.khmelev.tm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

@XmlRootElement(name = "Student")
@Setter
@Getter
@NoArgsConstructor
public class Project extends SortEntity implements Serializable {

    private String id;

    private String name;

    private String description;

    private Date dateStart;

    private Date dateFinish;

    private Date dateCreate;

    private Status status;

    private String userId;
}