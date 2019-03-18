package ru.Hmelev.tm.command;

import ru.Hmelev.tm.service.ServiceProject;
import ru.Hmelev.tm.service.ServiceTask;

import java.io.BufferedReader;

public class TaskListCommand extends Command {
    public TaskListCommand(BufferedReader reader, ServiceProject serviceProject, ServiceTask serviceTask) {
        super(reader, serviceProject, serviceTask, "task-list", "Show all tasks.");
    }

    @Override
    public void execute() {
        System.out.println("!!!Start command!!!");
        serviceTask.listTask();
        System.out.println("!!!DONE!!!");
    }
}