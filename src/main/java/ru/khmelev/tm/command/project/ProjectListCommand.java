package ru.khmelev.tm.command.project;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.khmelev.tm.api.IProjectService;
import ru.khmelev.tm.command.Command;
import ru.khmelev.tm.command.util.Printer;
import ru.khmelev.tm.entity.Project;
import ru.khmelev.tm.entity.Role;
import ru.khmelev.tm.entity.User;

public final class ProjectListCommand extends Command {

    @Override
    public String getNameCommand() {
        return "project-list";
    }

    @Override
    public String getDescriptionCommand() {
        return "Show all projects";
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
    public void execute() {
        System.out.println("!!!Start command!!!");
        @NotNull final IProjectService projectService = serviceLocator.getProjectService();

        @Nullable final User user = serviceLocator.getUserSession();
        if (user == null) {
            return;
        }

        @NotNull final String userId = serviceLocator.getUserService().getId(user);

        for (@NotNull Project project : projectService.findAll(userId)) {
            Printer.showListProject(project);
        }
        System.out.println("!!!DONE!!!");
    }
}