package ru.Hmelev.tm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Command command = new Command(reader);
        String commandReader;
        while (true) {
            System.out.println("Enter the command:");
            commandReader = reader.readLine();
            if (commandReader.equals("project-create")) {
                command.projectCreate();
            } else if (commandReader.equals("project-show")) {
                command.projectShow();
            } else if (commandReader.equals("project-list")) {
                command.projectList();
            } else if (commandReader.equals("project-edit")) {
                command.projectEdit();
            } else if (commandReader.equals("project-remove")) {
                command.projectRemove();
            } else if (commandReader.equals("task-create")) {
                command.taskCreate();
            } else if (commandReader.equals("task-show")) {
                command.taskShow();
            } else if (commandReader.equals("task-list")) {
                command.taskList();
            } else if (commandReader.equals("task-edit")) {
                command.taskEdit();
            } else if (commandReader.equals("task-remove")) {
                command.taskRemove();
            }
            if (commandReader.equals("help")) {
                command.help();
            }
        }
    }
}