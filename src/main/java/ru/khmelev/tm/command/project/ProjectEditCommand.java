package ru.khmelev.tm.command.project;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.khmelev.tm.api.IProjectEndpoint;
import ru.khmelev.tm.api.ITerminalService;
import ru.khmelev.tm.command.Command;
import ru.khmelev.tm.command.util.Printer;
import ru.khmelev.tm.entity.Project;
import ru.khmelev.tm.entity.Role;
import ru.khmelev.tm.entity.Session;
import ru.khmelev.tm.entity.Status;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

public final class ProjectEditCommand extends Command {

    @Override
    public String getNameCommand() {
        return "project-edit";
    }

    @Override
    public String getDescriptionCommand() {
        return "Edit selected project";
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

        @NotNull final IProjectEndpoint projectEndpoint = serviceLocator.getProjectEndpoint();

        @Nullable final Session session = serviceLocator.getSession();
        if (session == null) {
            return;
        }

        @NotNull final String userId = session.getUserId();

        System.out.println("ID project: ");
        @NotNull final String id = terminalService.readLine();
        if (id.isEmpty()) {
            return;
        }

        Project project = projectEndpoint.findEntity(session, id);

        System.out.println("Name project: ");
        @NotNull final String name = terminalService.readLine();
        if (name.isEmpty()) {
            return;
        }
        project.setName(name);

        System.out.println("Description: ");
        @NotNull final String description = terminalService.readLine();
        if (description.isEmpty()) {
            return;
        }
        project.setDescription(description);

        System.out.println("Start date: \"dd.MM.yyyy\" ");
        @NotNull String dateStartString = terminalService.readLine();

        try {
            @NotNull final Date dateStart = Printer.parse(dateStartString);
            project.setDateStart(dateStart);
        } catch (ParseException e) {
            System.out.println("Error! Start date: \"dd.MM.yyyy\". NOT: " + dateStartString);
        }

        System.out.println("Finish date: \"dd.MM.yyyy\" ");
        @NotNull String dateFinishString = terminalService.readLine();

        try {
            @NotNull final Date dateFinish = Printer.parse(dateFinishString);
            project.setDateFinish(dateFinish);
        } catch (ParseException e) {
            System.out.println("Error! Finish date: \"dd.MM.yyyy\". NOT: " + dateFinishString);
        }

        System.out.println("Status: (PLANNED, INPROGRESS, DONE)");
        @NotNull Status status = Status.valueOf(terminalService.readLine().toUpperCase());
        project.setStatus(status);

        projectEndpoint.editEntity(session, id, project);
        System.out.println("!!!DONE!!!");
    }
}