package ru.khmelev.tm.bootstrap;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.khmelev.tm.api.ITerminalService;
import ru.khmelev.tm.api.ServiceLocator;
import ru.khmelev.tm.api.endpoint.IUserEndpoint;
import ru.khmelev.tm.api.endpoint.Role;
import ru.khmelev.tm.api.endpoint.SessionDTO;
import ru.khmelev.tm.command.Command;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import java.io.IOException;
import java.util.SortedMap;
import java.util.TreeMap;

@NoArgsConstructor
@ApplicationScoped
public class Bootstrap implements ServiceLocator {

    @Getter
    @NotNull
    private final SortedMap<String, Command> commandMap = new TreeMap<>();

    @Inject
    private IUserEndpoint userEndpoint;

    @Inject
    private ITerminalService terminalService;

    @Getter
    @Setter
    @Nullable
    private SessionDTO sessionDTO;

    public void init(Class[] commandClassArray) throws Exception {
        registrationCommands(commandClassArray);
        startCommands();
    }

    private void startCommands() throws IOException {
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println("Enter the command:");
            Command command;
            String commandCmd = terminalService.readLine();
            command = commandMap.get(commandCmd);
            if (command == null) {
                continue;
            }
            if (permitCommand(command)) {
                try {
                    command.execute();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    private void registrationCommands(@NotNull final Class[] commandClassArray){
        for (Class classCommand : commandClassArray) {
            if (classCommand.getSuperclass().equals(Command.class)) {
                Command command = (Command) CDI.current().select(classCommand).get();
                commandMap.put(command.getNameCommand(), command);
            }
        }
    }

    private boolean permitCommand(@NotNull final Command commandString) {
        if (!commandString.isSecurity()) {
            return true;
        }

        if (sessionDTO == null) {
            System.out.println("Сначала зарегистрируйтесь");
            return false;
        }

        if ("user-login".equals(commandString.getNameCommand()) && sessionDTO != null) {
            System.out.println("Сначала выйдете из программы");
            return false;
        }

        if (sessionDTO != null && userEndpoint.findUser(sessionDTO, sessionDTO.getUserId()).getRole() == Role.ADMIN) {
            return true;
        }
        if (sessionDTO != null && commandString.getRoleCommand() == userEndpoint.findUser(sessionDTO, sessionDTO.getUserId()).getRole()) {
            return true;
        }

        if (sessionDTO != null && userEndpoint.findUser(sessionDTO, sessionDTO.getUserId()).getRole() != Role.ADMIN) {
            System.out.println("Не хватает прав");
            return false;
        }
        return true;
    }
}