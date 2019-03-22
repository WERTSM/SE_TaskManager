package ru.Hmelev.tm.entity;

import java.util.Date;

public class Project {
    private String id;
    private String name;
    private String description;
    private Date dateStart;
    private Date dataFinish;

    private String userId;

    public Project(String id, String name, String description, Date dateStart, Date dataFinish, String userId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dateStart = dateStart;
        this.dataFinish = dataFinish;
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDataFinish() {
        return dataFinish;
    }

    public void setDataFinish(Date dataFinish) {
        this.dataFinish = dataFinish;
    }
}