package ru.khmelev.tm.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.khmelev.tm.api.endpoint.Role;

import java.io.IOException;

@NoArgsConstructor
public abstract class Command {

    @Getter
    private String nameCommand;

    @Getter
    private String descriptionCommand;

    @Getter
    private boolean security;

    @Setter
    @Getter
    private Role roleCommand;

    public abstract void execute() throws IOException, ClassNotFoundException;
}