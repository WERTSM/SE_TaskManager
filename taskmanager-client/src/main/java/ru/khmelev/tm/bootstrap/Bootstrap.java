package ru.khmelev.tm.bootstrap;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.khmelev.tm.api.endpoint.*;
import ru.khmelev.tm.api.service.ITerminalService;
import ru.khmelev.tm.command.Command;
import ru.khmelev.tm.endpoint.ProjectEndpointService;
import ru.khmelev.tm.endpoint.TaskEndpointService;
import ru.khmelev.tm.endpoint.UserEndpointService;
import ru.khmelev.tm.service.TerminalService;

import java.io.IOException;
import java.util.SortedMap;
import java.util.TreeMap;

public final class Bootstrap implements ServiceLocator {

    @Getter
    @NotNull
    private final SortedMap<String, Command> commandMap = new TreeMap<>();
    @Getter
    @NotNull
    private final ProjectEndpointService projectEndpointService = new ProjectEndpointService();
    @Getter
    @NotNull
    private final TaskEndpointService taskEndpointService = new TaskEndpointService();
    @Getter
    @NotNull
    private final UserEndpointService userEndpointService = new UserEndpointService();
    @Getter
    @NotNull
    private final IProjectEndpoint projectEndpoint = projectEndpointService.getProjectEndpointPort();
    @Getter
    @NotNull
    private final ITaskEndpoint taskEndpoint = taskEndpointService.getTaskEndpointPort();
    @Getter
    @NotNull
    private final IUserEndpoint userEndpoint = userEndpointService.getUserEndpointPort();
    @Getter
    @NotNull
    private final ITerminalService terminalService = new TerminalService();
    @Setter
    @Getter
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

    private void registrationCommands(@NotNull final Class[] commandClassArray) throws IllegalAccessException, InstantiationException {
        for (Class classCommand : commandClassArray) {
            if (classCommand.getSuperclass().equals(Command.class)) {
                Command command = (Command) classCommand.newInstance();
                command.setServiceLocator(this);
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