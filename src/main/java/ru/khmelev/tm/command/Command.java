package ru.khmelev.tm.command;

import lombok.Getter;
import lombok.Setter;
import ru.khmelev.tm.bootstrap.ServiceLocator;
import ru.khmelev.tm.entity.Role;

import java.io.IOException;

public abstract class Command {

    @Getter
    @Setter
    protected ServiceLocator serviceLocator;

    @Getter
    private String nameCommand;

    @Getter
    private String descriptionCommand;

    @Getter
    private boolean security;

    @Setter
    @Getter
    private Role roleCommand;

    public abstract void execute() throws IOException;
}