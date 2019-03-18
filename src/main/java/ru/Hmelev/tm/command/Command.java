package ru.Hmelev.tm.command;

import java.io.BufferedReader;

public abstract class Command {
    private String name;
    private String description;
    private BufferedReader reader;


    public Command(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void execute() {
    }
}