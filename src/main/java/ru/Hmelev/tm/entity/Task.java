package ru.Hmelev.tm.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class Task {
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    private UUID id;
    private String name;
    private String description;
    private Date startDate;
    private Date finishDate;
    private UUID idProject;

    public Task(UUID id, String name, String description, Date startDate, Date finishDate, UUID idProject) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.idProject = idProject;
    }

    public UUID getIdProject() {
        return idProject;
    }

    public void setIdProject(UUID idProject) {
        this.idProject = idProject;
    }

    public UUID getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }


    public void viewTask() {
        System.out.println(
                "[ ID = " + id
                        + "; Name = " + name
                        + "; Description = " + description
                        + "; Start date = " + dateFormat.format(startDate)
                        + "; Finish date = " + dateFormat.format(finishDate)
                        + "; idProject = " + idProject
                        + " ]");
    }
}