package ru.khmelev.tm;

import org.jetbrains.annotations.NotNull;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.khmelev.tm.bootstrap.Bootstrap;
import ru.khmelev.tm.command.project.*;
import ru.khmelev.tm.command.system.AboutCommand;
import ru.khmelev.tm.command.system.ExitCommand;
import ru.khmelev.tm.command.system.HelpCommand;
import ru.khmelev.tm.command.system.ServerInfoCommand;
import ru.khmelev.tm.command.task.*;
import ru.khmelev.tm.command.user.*;
import ru.khmelev.tm.util.SpringJPAConfigUtil;

public final class ApplicationClient {

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
            AboutCommand.class,
            ExitCommand.class,
            ServerInfoCommand.class
    };

    public static void main(String[] args) throws Exception {
        System.setProperty("org.apache.logging.log4j.simplelog.StatusLogger.level", "INFO");

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringJPAConfigUtil.class);
        applicationContext.getBean(Bootstrap.class).init(CLASS);
    }
}