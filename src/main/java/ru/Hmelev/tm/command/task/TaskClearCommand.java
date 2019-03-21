package ru.Hmelev.tm.command.task;

import ru.Hmelev.tm.bootstrap.Bootstrap;
import ru.Hmelev.tm.command.Command;
import ru.Hmelev.tm.command.util.Security;
import ru.Hmelev.tm.entity.Role;

public class TaskClearCommand extends Command {
    public TaskClearCommand(Bootstrap bootstrap) {
        super(bootstrap, "task-clear", "Remove all tasks.", Security.PRIVATE, Role.ADMIN);
    }

    @Override
    public void execute() {
        System.out.println("!!!Start command!!!");
        taskService.clearTask();
        System.out.println("!!!DONE!!!");
    }
}