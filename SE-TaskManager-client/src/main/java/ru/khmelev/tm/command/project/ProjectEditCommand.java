package ru.khmelev.tm.command.project;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.khmelev.tm.api.endpoint.*;
import ru.khmelev.tm.api.service.ITerminalService;
import ru.khmelev.tm.command.Command;
import ru.khmelev.tm.command.util.Printer;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import static ru.khmelev.tm.command.util.Printer.printXMLDate;

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

        Project project = projectEndpoint.findProject(session, id);

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

        @NotNull final Date dateStart = Printer.parse(dateStartString);
        project.setDateStart(printXMLDate(dateStart));

        System.out.println("Finish date: \"dd.MM.yyyy\" ");
        @NotNull String dateFinishString = terminalService.readLine();

        @NotNull final Date dateFinish = Printer.parse(dateFinishString);
        project.setDateFinish(printXMLDate(dateFinish));

        System.out.println("Status: (PLANNED, INPROGRESS, DONE)");
        @NotNull Status status = Status.valueOf(terminalService.readLine().toUpperCase());
        project.setStatus(status);

        projectEndpoint.editProject(session, id, project);
        System.out.println("!!!DONE!!!");
    }
}