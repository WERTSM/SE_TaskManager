package ru.Hmelev.tm;

import org.jetbrains.annotations.NotNull;
import ru.Hmelev.tm.bootstrap.Bootstrap;
import ru.Hmelev.tm.command.project.*;
import ru.Hmelev.tm.command.system.AboutCommand;
import ru.Hmelev.tm.command.system.ExitCommand;
import ru.Hmelev.tm.command.system.HelpCommand;
import ru.Hmelev.tm.command.task.*;
import ru.Hmelev.tm.command.user.*;

public final class Application {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {

        @NotNull final Class[] commandClassArray = new Class[]{
                HelpCommand.class,
                ExitCommand.class,
                ProjectCreateCommand.class,
                ProjectClearCommand.class,
                ProjectListCommand.class,
                ProjectEditCommand.class,
                ProjectShowCommand.class,
                ProjectRemoveCommand.class,
                TaskCreateCommand.class,
                TaskClearCommand.class,
                TaskListCommand.class,
                TaskEditCommand.class,
                TaskShowCommand.class,
                TaskRemoveCommand.class,
                UserRegistryCommand.class,
                UserLoginCommand.class,
                UserListCommand.class,
                UserLogoutCommand.class,
                UserSetPasswordCommand.class,
                UserProfileCommand.class,
                UserUpdateCommand.class,
                AboutCommand.class
        };
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.init(commandClassArray);
    }
}