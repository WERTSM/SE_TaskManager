package ru.khmelev.tm.command.project;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.khmelev.tm.api.IProjectService;
import ru.khmelev.tm.api.ITerminalService;
import ru.khmelev.tm.command.Command;
import ru.khmelev.tm.command.util.Printer;
import ru.khmelev.tm.entity.Project;
import ru.khmelev.tm.entity.Role;
import ru.khmelev.tm.entity.Status;
import ru.khmelev.tm.entity.User;

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

        @NotNull final IProjectService projectService = serviceLocator.getProjectService();

        @Nullable final User user = serviceLocator.getUserSession();
        if (user == null) {
            return;
        }

        @NotNull final String userId = serviceLocator.getUserService().getId(user);

        System.out.println("ID project: ");
        @NotNull final String id = terminalService.readLine();
        if (id.isEmpty()) {
            return;
        }

        @NotNull final Project project = projectService.findEntity(id, userId);

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
            @NotNull final Date dataFinish = Printer.parse(dateFinishString);
            project.setDataFinish(dataFinish);
        } catch (ParseException e) {
            System.out.println("Error! Finish date: \"dd.MM.yyyy\". NOT: " + dateFinishString);
        }

        System.out.println("Status: (PLANNED, INPROGRESS, DONE)");
        @NotNull Status status = Status.valueOf(terminalService.readLine().toUpperCase());

        project.setStatus(status);

        projectService.editEntity(id, project, userId);
        System.out.println("!!!DONE!!!");
    }
}