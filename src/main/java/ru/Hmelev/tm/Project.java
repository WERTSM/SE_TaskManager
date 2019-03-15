package ru.Hmelev.tm;

public class Project {
    private int id;
    private String name;
    private String description;
    private String startDate;
    private String finishDate;

    public Project(int id, String name, String description, String startDate, String finishDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.finishDate = finishDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setFinishDate(String finishDate) {
        this.finishDate = finishDate;
    }

    public int getId() {
        return id;
    }

    public void viewProject() {
        System.out.println("[ ID = " + id + "; Name = " + name + "; Description = " + description + "; Start date = " + startDate + "; Finish date = " + finishDate + " ]");
    }
}