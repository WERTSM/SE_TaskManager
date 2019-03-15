package ru.Hmelev.tm;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Task {
    private int id;
    private String name;
    private String description;
    private Date startDate;
    private Date finishDate;

    SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    public Task(int id, String name, String description, Date startDate, Date finishDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.finishDate = finishDate;
    }

    public int getId() {
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
        System.out.println("[ ID = " + id + "; Name = " + name + "; Description = " + description + "; Start date = " + dateFormat.format(startDate) + "; Finish date = " + dateFormat.format(finishDate) + " ]");
    }
}