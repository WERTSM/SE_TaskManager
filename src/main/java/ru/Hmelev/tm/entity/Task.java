package ru.Hmelev.tm.entity;

import java.util.Date;

public class Task {
    private String id;
    private String name;
    private String description;
    private Date startDate;
    private Date finishDate;
    private String idProject;
    private String userId;

    public Task(String id, String name, String description, Date startDate, Date finishDate, String idProject, String userId) {
        this.id = id;
        this.name = name;

        this.description = description;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.idProject = idProject;
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public String getIdProject() {
        return idProject;
    }

    public void setIdProject(String idProject) {
        this.idProject = idProject;
    }

    public String getId() {
        return id;
    }
}