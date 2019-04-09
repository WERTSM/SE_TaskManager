package ru.khmelev.tm.bootstrap;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.khmelev.tm.api.endpoint.*;
import ru.khmelev.tm.api.service.ITerminalService;
import ru.khmelev.tm.command.Command;
import ru.khmelev.tm.endpoint.AdminEndpointService;
import ru.khmelev.tm.endpoint.ProjectEndpointService;
import ru.khmelev.tm.endpoint.TaskEndpointService;
import ru.khmelev.tm.endpoint.UserEndpointService;
import ru.khmelev.tm.endpoint.util.PasswordHashUtil;
import ru.khmelev.tm.service.TerminalService;

import javax.sql.rowset.serial.SerialException;
import java.io.IOException;
import java.lang.Exception;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;

public final class Bootstrap implements ServiceLocator {

    @Getter
    @NotNull
    private final SortedMap<String, Command> commandMap = new TreeMap<>();

    @Setter
    @Getter
    @Nullable
    private Session session;


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
    private final AdminEndpointService adminEndpointService = new AdminEndpointService();

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
    private final IAdminEndpoint adminEndpoint = adminEndpointService.getAdminEndpointPort();

    @Getter
    @NotNull
    private final ITerminalService terminalService = new TerminalService();

    public void init(Class[] commandClassArray) throws Exception {
        registrationCommands(commandClassArray);

        defaultCommands();
        startCommands();
    }

    private void startCommands() throws IOException, SerialException, SerialException_Exception {
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

    private void defaultCommands() {
        @NotNull User user = new User();
        user.setLogin("user");
        user.setHashPassword(PasswordHashUtil.md5("user"));
        user.setRole(Role.USER);
        //@NotNull String id = UUID.randomUUID().toString();
        @NotNull String id = "11111111-1111-1111-1111-111111111111";
        user.setId(id);
        userEndpoint.createUser(id, user);
        Session session = userEndpoint.userLogin("user", "user");
        setSession(session);

        user = new User();
        user.setLogin("admin");
        user.setHashPassword(PasswordHashUtil.md5("admin"));
        user.setRole(Role.ADMIN);
        id = UUID.randomUUID().toString();
        user.setId(id);
        userEndpoint.createUser(id, user);
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

    private boolean permitCommand(@NotNull final Command commandString) throws SerialException, SerialException_Exception {

        if (commandString.isSecurity() && session == null) {
            System.out.println("Сначала зарегистрируйтесь");
            return false;
        }

        //if (session == null) {

        //}

        if ("user-login".equals(commandString.getNameCommand()) && session != null) {
            System.out.println("Сначала выйдете из программы");
            return false;
        }

        if (session != null && userEndpoint.findUser(session, session.getUserId()).getRole() == Role.ADMIN) {
            return true;
        }
        if (session != null && commandString.getRoleCommand() == userEndpoint.findUser(session, session.getUserId()).getRole()) {
            return true;
        }

        if (session != null && userEndpoint.findUser(session, session.getUserId()).getRole() != Role.ADMIN) {
            System.out.println("Не хватает прав");
            return false;
        }
        return true;
    }
}