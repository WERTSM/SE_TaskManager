package ru.khmelev.tm.command.task;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.khmelev.tm.api.endpoint.Role;
import ru.khmelev.tm.api.endpoint.Session;
import ru.khmelev.tm.api.endpoint.Status;
import ru.khmelev.tm.api.endpoint.Task;
import ru.khmelev.tm.api.service.ITerminalService;
import ru.khmelev.tm.command.Command;
import ru.khmelev.tm.command.util.Printer;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.UUID;

public final class TaskCreateCommand extends Command {

    @Override
    public String getNameCommand() {
        return "task-create";
    }

    @Override
    public String getDescriptionCommand() {
        return "Create new task";
    }

    @Override
    public boolean isSecurity() {
        return true;
    }

    @Override
    public Role getRoleCommand() {
        return Role.USER;
    }


    @Override
    public void execute() throws IOException {
        System.out.println("!!!Start command!!!");
        @NotNull final ITerminalService terminalService = serviceLocator.getTerminalService();

        @Nullable final Session session = serviceLocator.getSession();
        if (session == null) {
            return;
        }

        @NotNull final Task task = new Task();

        System.out.println("Name task: ");
        @NotNull final String name = terminalService.readLine();
        if (name.isEmpty()) {
            return;
        }
        task.setName(name);

        System.out.println("Description task: ");
        @NotNull final String description = terminalService.readLine();
        if (description.isEmpty()) {
            return;
        }
        task.setDescription(description);

        System.out.println("Start date: \"dd.MM.yyyy\" ");
        @NotNull String dateStartString = terminalService.readLine();

        try {
            @NotNull final Date dateStart = Printer.parse(dateStartString);
            task.setDateStart(dateStart);
        } catch (ParseException e) {
            System.out.println("Error! Start date: \"dd.MM.yyyy\". NOT: " + dateStartString);
        }

        System.out.println("Finish date: \"dd.MM.yyyy\" ");
        @NotNull String dateFinishString = terminalService.readLine();

        try {
            @NotNull final Date dateFinish = Printer.parse(dateFinishString);
            task.setDateFinish(dateFinish);
        } catch (ParseException e) {
            System.out.println("Error! Finish date: \"dd.MM.yyyy\". NOT: " + dateFinishString);
        }

        @NotNull final String id = UUID.randomUUID().toString();
        task.setId(id);

        task.setDateCreate(new Date());

        task.setStatus(Status.PLANNED);

        @NotNull final String userId = session.getUserId();
        task.setUserId(userId);

        System.out.println("Id project or \'0\': ");
        @NotNull String idProject = terminalService.readLine();
        if (idProject.equals("0")) {
            idProject = "00000000-0000-0000-0000-000000000000";
        }
        task.setIdProject(idProject);

        serviceLocator.getTaskEndpoint().createTask(session, id, task);
        System.out.println("!!!DONE!!!");
    }
}