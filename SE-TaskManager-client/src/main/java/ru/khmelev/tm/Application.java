package ru.khmelev.tm;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.bootstrap.Bootstrap;
import ru.khmelev.tm.command.project.*;
import ru.khmelev.tm.command.system.*;
import ru.khmelev.tm.command.task.*;
import ru.khmelev.tm.command.user.*;

public final class Application {

    @NotNull
    private static final Class[] CLASS = new Class[]{
            HelpCommand.class,
            ProjectCreateCommand.class,
            ProjectClearCommand.class,
            ProjectListCommand.class,
            ProjectFindCommand.class,
            ProjectEditCommand.class,
            ProjectShowCommand.class,
            ProjectSortCommand.class,
            ProjectRemoveCommand.class,
            TaskCreateCommand.class,
            TaskClearCommand.class,
            TaskListCommand.class,
            TaskFindCommand.class,
            TaskEditCommand.class,
            TaskShowCommand.class,
            TaskSortCommand.class,
            TaskRemoveCommand.class,
            UserRegistryCommand.class,
            UserLoginCommand.class,
            UserListCommand.class,
            UserLogoutCommand.class,
            UserSetPasswordCommand.class,
            UserProfileCommand.class,
            UserUpdateCommand.class,
            SaveSerializationCommand.class,
            SaveXMLCommand.class,
            SaveJSONCommand.class,
            SaveFasterXMLCommand.class,
            SaveFasterXmlJSONCommand.class,
            LoadSerializationCommand.class,
            LoadXMLCommand.class,
            LoadJSONCommand.class,
            LoadFasterXMLCommand.class,
            LoadFasterXmlJSONCommand.class,
            AboutCommand.class,
            ExitCommand.class
    };

    public static void main(String[] args) throws Exception {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.init(CLASS);
    }
}