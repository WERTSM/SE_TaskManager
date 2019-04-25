package ru.khmelev.tm.util;

import com.jcabi.manifests.Manifests;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.khmelev.tm.api.endpoint.ProjectDTO;
import ru.khmelev.tm.api.endpoint.Status;
import ru.khmelev.tm.api.endpoint.TaskDTO;
import ru.khmelev.tm.api.endpoint.UserDTO;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class PrinterUtil {

    public static void showProject(@NotNull final ProjectDTO projectDTO, @Nullable final UserDTO userDTO) {
        if (userDTO != null) {
            @NotNull final Status status = projectDTO.getStatus();
            System.out.println(
                    "[ ID = " + projectDTO.getId()
                            + "; Name = " + projectDTO.getName()
                            + "; Description = " + projectDTO.getDescription()
                            + "; Start date = " + ConverterUtil.convertFromXMLDateToString(projectDTO.getDateStart())
                            + "; Finish date = " + ConverterUtil.convertFromXMLDateToString(projectDTO.getDateFinish())
                            + "; Status = " + status.value()
                            + "; Name user = " + userDTO.getLogin()
                            + "; Create date = " + ConverterUtil.convertFromXMLDateToString(projectDTO.getDateCreate())
                            + " ]");
        }
    }

    public static void showTask(@NotNull final TaskDTO taskDTO, @Nullable final UserDTO userDTO) {
        if (userDTO != null) {
            System.out.println(
                    "[ ID = " + taskDTO.getId()
                            + "; Name = " + taskDTO.getName()
                            + "; Description = " + taskDTO.getDescription()
                            + "; Start date = " + ConverterUtil.convertFromXMLDateToString(taskDTO.getDateStart())
                            + "; Finish date = " + ConverterUtil.convertFromXMLDateToString(taskDTO.getDateFinish())
                            + "; id Project= " + taskDTO.getProjectId()
                            + "; Status = " + taskDTO.getStatus().value()
                            + "; Name user = " + userDTO.getLogin()
                            + "; Create date = " + ConverterUtil.convertFromXMLDateToString(taskDTO.getDateCreate())
                            + " ]");
        }
    }

    public static void showListTask(@NotNull final TaskDTO taskDTO) {
        System.out.println(
                "[ ID = " + taskDTO.getId()
                        + "; Name = " + taskDTO.getName()
                        + "; Description = " + taskDTO.getDescription()
                        + " ]");
    }

    public static void showListProject(@NotNull final ProjectDTO projectDTO) {
        System.out.println(
                "[ ID = " + projectDTO.getId()
                        + "; Name = " + projectDTO.getName()
                        + "; Description = " + projectDTO.getDescription()
                        + " ]");
    }

    public static void showTaskInProject(@NotNull final TaskDTO taskDTO) {
        System.out.println("Task in project: \n"
                + "[ ID = " + taskDTO.getId()
                + "; Name = " + taskDTO.getName()
                + "; Description = " + taskDTO.getDescription()
                + "; Status = " + taskDTO.getStatus().value()
                + "; UserId = " + taskDTO.getUserId()
                + " ]");
    }

    public static void showListUser(@Nullable final UserDTO userDTO) {
        if (userDTO != null) {
            System.out.println(
                    "[ ID = " + userDTO.getId()
                            + "; Login = " + userDTO.getLogin()
                            + "; Role = " + userDTO.getRole().value()
                            + " ]");
        }
    }

    public static void showUser(@Nullable final UserDTO userDTO) {
        if (userDTO != null) {
            System.out.println(
                    "Сейчас в системе:"
                            + "[ ID = " + userDTO.getId()
                            + "; Login = " + userDTO.getLogin()
                            + "; Role = " + userDTO.getRole().value()
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
                + "\nApplicationClient login: " + name
                + "\nApplicationClient version: " + version
                + "\nApplicationClient build: " + build);
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
}