package ru.Hmelev.tm.command.task;

import ru.Hmelev.tm.command.Command;
import ru.Hmelev.tm.entity.Role;
import ru.Hmelev.tm.entity.Status;
import ru.Hmelev.tm.entity.Task;

import java.io.IOException;
import java.text.ParseException;

import static ru.Hmelev.tm.command.util.Printer.DEFAULT_DATE_FORMAT;

public final class TaskEditCommand extends Command {
    public TaskEditCommand() {
        super("task-edit", "Edit selected task", true, Role.USER);
    }

    @Override
    public void execute() throws IOException, ParseException {
        System.out.println("!!!Start command!!!");

        taskService = serviceLocator.getTaskService();
        terminalService = serviceLocator.getTerminalService();

        System.out.println("ID task: ");
        id = terminalService.readLine();

        System.out.println("Name task: ");
        name = terminalService.readLine();

        System.out.println("Description task: ");
        description = terminalService.readLine();

        System.out.println("Start date task: \"dd.MM.yyyy\" ");
        date = terminalService.readLine();
        startDate = DEFAULT_DATE_FORMAT.parse(date);

        System.out.println("Finish date task: \"dd.MM.yyyy\" ");
        date = terminalService.readLine();
        finishDate = DEFAULT_DATE_FORMAT.parse(date);

        System.out.println("Id project or \'0\': ");

        idProject = terminalService.readLine();
        if (idProject.equals("0")) {
            idProject = "00000000-0000-0000-0000-000000000000";
        }

        System.out.println("Status: (PLANNED, INPROGRESS, DONE)");
        String statusString = terminalService.readLine();
        Status status = Status.valueOf(statusString.toUpperCase());

        user = serviceLocator.getUserSession();

        if (id != null && !id.isEmpty() && name != null && !name.isEmpty() && description != null && !description.isEmpty() && startDate != null && finishDate != null && idProject != null && !idProject.isEmpty()) {
            if (user != null) {
                Task task;
                task = taskService.findEntity(id, user);
                task.setName(name);
                task.setDescription(description);
                task.setStartDate(startDate);
                task.setFinishDate(finishDate);
                task.setIdProject(idProject);
                task.setStatus(status);
                taskService.editEntity(id, task, user);
            }
        }
        System.out.println("!!!DONE!!!");
    }
}