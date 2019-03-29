package ru.khmelev.tm.command.util;

import com.jcabi.manifests.Manifests;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.khmelev.tm.entity.Project;
import ru.khmelev.tm.entity.Task;
import ru.khmelev.tm.entity.User;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public final class Printer {

    @NotNull
    private static final SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");

    public static void showProject(@NotNull final Project project, @Nullable final User user) {
        if (user != null) {
            System.out.println(
                    "[ ID = " + project.getId()
                            + "; Name = " + project.getName()
                            + "; Description = " + project.getDescription()
                            + "; Start date = " + DEFAULT_DATE_FORMAT.format(project.getDateStart())
                            + "; Finish date = " + DEFAULT_DATE_FORMAT.format(project.getDateFinish())
                            + "; Status = " + project.getStatus().getDisplayName()
                            + "; Name user = " + user.getLogin()
                            + "; Create date = " + DEFAULT_DATE_FORMAT.format(project.getDateCreate())
                            + " ]");
        }
    }

    public static void showTask(@NotNull final Task task, @Nullable final User user) {
        if (user != null) {
            System.out.println(
                    "[ ID = " + task.getId()
                            + "; Name = " + task.getName()
                            + "; Description = " + task.getDescription()
                            + "; Start date = " + DEFAULT_DATE_FORMAT.format(task.getDateStart())
                            + "; Finish date = " + DEFAULT_DATE_FORMAT.format(task.getDateFinish())
                            + "; id Project= " + task.getIdProject()
                            + "; Status = " + task.getStatus().getDisplayName()
                            + "; Name user = " + user.getLogin()
                            + "; Create date = " + DEFAULT_DATE_FORMAT.format(task.getDateCreate())
                            + " ]");
        }
    }

    public static void showListTask(@NotNull final Task task) {
        System.out.println(
                "[ ID = " + task.getId()
                        + "; Name = " + task.getName()
                        + "; Description = " + task.getDescription()
                        + " ]");
    }

    public static void showListProject(@NotNull final Project project) {
        System.out.println(
                "[ ID = " + project.getId()
                        + "; Name = " + project.getName()
                        + "; Description = " + project.getDescription()
                        + " ]");
    }

    public static void showTaskInProject(@NotNull final Task task) {
        System.out.println("Task in project: \n"
                + "[ ID = " + task.getId()
                + "; Name = " + task.getName()
                + "; Description = " + task.getDescription()
                + "; Status = " + task.getStatus().getDisplayName()
                + "; UserId = " + task.getUserId()
                + " ]");
    }

    public static void showListUser(@Nullable final User user) {
        if (user != null) {
            System.out.println(
                    "[ ID = " + user.getId()
                            + "; Login = " + user.getLogin()
                            + "; Role = " + user.getRole().displayName()
                            + " ]");
        }
    }

    public static void showUser(@Nullable final User user) {
        if (user != null) {
            System.out.println(
                    "Сейчас в системе:"
                            + "[ ID = " + user.getId()
                            + "; Login = " + user.getLogin()
                            + "; Role = " + user.getRole().displayName()
                            + " ]");
        }
    }

    public static void showProperties(@NotNull final InputStream inputStreamProperties) throws IOException {
        @NotNull final Properties properties = new Properties();
        properties.load(inputStreamProperties);

        @NotNull final String name = properties.getProperty("application.name");
        @NotNull final String version = properties.getProperty("application.version");
        @NotNull final String build = properties.getProperty("application.build");

        System.out.println("Вывод информации из application.properties: "
                + "\nApplication login: " + name
                + "\nApplication version: " + version
                + "\nApplication build: " + build);
    }

    public static void showManifest() {
        @NotNull final String builtBy = Manifests.read("Built-By");
        @NotNull final String name = Manifests.read("login");
        @NotNull final String createdBy = Manifests.read("Created-By");
        @NotNull final String builtJdk = Manifests.read("Build-Jdk");
        @NotNull final String mainClass = Manifests.read("Main-Class");
        @NotNull final String implementationBuild = Manifests.read("Implementation-Build");

        System.out.println("\nВывод информации из МАНИФЕСТА: "
                + "\nBuilt-By: " + builtBy
                + "\nName: " + name
                + "\nCreated-By: " + createdBy
                + "\nBuild-Jdk: " + builtJdk
                + "\nMain-Clas: " + mainClass
                + "\nImplementation-Build: " + implementationBuild
        );
    }

    @NotNull
    public static Date parse(@NotNull final String dateString) throws ParseException {
        return DEFAULT_DATE_FORMAT.parse(dateString);
    }
}