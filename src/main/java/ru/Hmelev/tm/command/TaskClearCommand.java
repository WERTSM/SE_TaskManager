package ru.Hmelev.tm.command;

import ru.Hmelev.tm.entity.Project;
import ru.Hmelev.tm.service.ServiceProject;
import ru.Hmelev.tm.service.ServiceTask;

import java.io.BufferedReader;

public class TaskClearCommand extends Command {
    public TaskClearCommand(BufferedReader reader, ServiceProject serviceProject, ServiceTask serviceTask) {
        super(reader, serviceProject, serviceTask, "task-clear", "Remove all tasks.");
    }

    @Override
    public void execute() {
        System.out.println("!!!Start command!!!");
        serviceTask.clearTask();
        System.out.println("!!!DONE!!!");
    }
}