package ru.Hmelev.tm.command;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import ru.Hmelev.tm.api.InterfaceProjectService;
import ru.Hmelev.tm.api.InterfaceTaskService;
import ru.Hmelev.tm.bootstrap.ServiceLocator;
import ru.Hmelev.tm.entity.Role;
import ru.Hmelev.tm.entity.User;
import ru.Hmelev.tm.service.TerminalService;
import ru.Hmelev.tm.service.UserService;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

public abstract class Command {
    protected InterfaceProjectService projectService;
    protected InterfaceTaskService taskService;
    protected UserService userService;
    protected TerminalService terminalService;

    protected String name;
    protected String description;
    protected String id;
    protected String date;
    protected Date startDate;
    protected Date finishDate;
    protected String idProject;
    protected String idTask;
    protected String idProjectFromTask;

    protected User user;
    protected String login;
    protected String password;
    protected String role;

    @Setter
    protected ServiceLocator serviceLocator;

    @Getter
    private boolean security;

    @Getter
    private String nameCommand;
    @Getter
    private String descriptionCommand;
    @Getter
    private Role roleCommand;

    public Command(@NotNull final String nameCommand, @NotNull final String descriptionCommand, final boolean security, @NotNull Role role) {
        this.nameCommand = nameCommand;
        this.descriptionCommand = descriptionCommand;
        this.security = security;
        this.roleCommand = role;
    }

    public Command(@NotNull final String nameCommand, @NotNull final String descriptionCommand, final boolean security) {
        this.nameCommand = nameCommand;
        this.descriptionCommand = descriptionCommand;
        this.security = security;
    }

    public void execute() throws IOException, ParseException {
    }
}