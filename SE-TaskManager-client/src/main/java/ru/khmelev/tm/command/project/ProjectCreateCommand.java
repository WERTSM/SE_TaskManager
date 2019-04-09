package ru.khmelev.tm.command.project;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.khmelev.tm.api.endpoint.Project;
import ru.khmelev.tm.api.endpoint.Role;
import ru.khmelev.tm.api.endpoint.Session;
import ru.khmelev.tm.api.endpoint.Status;
import ru.khmelev.tm.api.service.ITerminalService;
import ru.khmelev.tm.command.Command;
import ru.khmelev.tm.command.util.Printer;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.UUID;

public final class ProjectCreateCommand extends Command {

    @Override
    public String getNameCommand() {
        return "project-create";
    }

    @Override
    public String getDescriptionCommand() {
        return "Create new project";
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

        @NotNull final Project project = new Project();

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
        project.setDateStart(Printer.printXMLDate(dateStart));

        System.out.println("Finish date: \"dd.MM.yyyy\" ");
        @NotNull String dateFinishString = terminalService.readLine();

        @NotNull final Date dateFinish = Printer.parse(dateFinishString);
        project.setDateFinish(Printer.printXMLDate(dateFinish));

        @NotNull final String id = UUID.randomUUID().toString();
        project.setId(id);

        project.setDateCreate(Printer.printXMLDate(new Date()));
        project.setStatus(Status.PLANNED);

        @NotNull final String userId = session.getUserId();
        project.setUserId(userId);

        serviceLocator.getProjectEndpoint().createProject(session, id, project);
        System.out.println("!!!DONE!!!");
    }
}