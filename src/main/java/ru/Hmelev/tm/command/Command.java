package ru.Hmelev.tm.command;

public abstract class Command {
    private String name;
    private String description;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void execute() {
    }
}