package ru.khmelev.tm.enumeration;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.api.endpoint.ProjectDTO;
import ru.khmelev.tm.api.endpoint.TaskDTO;
import ru.khmelev.tm.util.Printer;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public enum Sort {

    START("Создание"), FINISH("Завершение"), CREATE("Добавление"), STATUS("Статус");

    String name;

    Sort(String name) {
        this.name = name;
    }

    public static void sortProject(@NotNull List<ProjectDTO> list, @NotNull Sort sort) {
        if (sort == CREATE) {
            Collections.sort(list, new Comparator<ProjectDTO>() {
                @Override
                public int compare(ProjectDTO projectDTO1, ProjectDTO projectDTO2) {
                    if (projectDTO1.equals(projectDTO2)) {
                        return 0;
                    }
                    return Printer.printDate(projectDTO1.getDateCreate()).compareTo(Printer.printDate(projectDTO2.getDateCreate()));
                }
            });
        }
        if (sort == START) {
            Collections.sort(list, new Comparator<ProjectDTO>() {
                @Override
                public int compare(ProjectDTO projectDTO1, ProjectDTO projectDTO2) {
                    if (projectDTO1.equals(projectDTO2)) {
                        return 0;
                    }
                    return Printer.printDate(projectDTO1.getDateStart()).compareTo(Printer.printDate(projectDTO2.getDateStart()));
                }
            });
        }
        if (sort == FINISH) {
            Collections.sort(list, new Comparator<ProjectDTO>() {
                @Override
                public int compare(ProjectDTO projectDTO1, ProjectDTO projectDTO2) {
                    if (projectDTO1.equals(projectDTO2)) {
                        return 0;
                    }
                    return Printer.printDate(projectDTO1.getDateFinish()).compareTo(Printer.printDate(projectDTO2.getDateFinish()));
                }
            });
        }
        if (sort == STATUS) {
            Collections.sort(list, new Comparator<ProjectDTO>() {
                @Override
                public int compare(ProjectDTO projectDTO1, ProjectDTO projectDTO2) {
                    if (projectDTO1.equals(projectDTO2)) {
                        return 0;
                    }
                    return projectDTO1.getStatus().compareTo(projectDTO2.getStatus());
                }
            });
        }
    }

    public static void sortTask(@NotNull List<TaskDTO> list, @NotNull Sort sort) {
        if (sort == CREATE) {
            Collections.sort(list, new Comparator<TaskDTO>() {
                @Override
                public int compare(TaskDTO taskDTO1, TaskDTO taskDTO2) {
                    if (taskDTO1.equals(taskDTO2)) {
                        return 0;
                    }
                    return Printer.printDate(taskDTO1.getDateCreate()).compareTo(Printer.printDate(taskDTO2.getDateCreate()));
                }
            });
        }
        if (sort == START) {
            Collections.sort(list, new Comparator<TaskDTO>() {
                @Override
                public int compare(TaskDTO taskDTO1, TaskDTO taskDTO2) {
                    if (taskDTO1.equals(taskDTO2)) {
                        return 0;
                    }
                    return Printer.printDate(taskDTO1.getDateStart()).compareTo(Printer.printDate(taskDTO2.getDateStart()));
                }
            });
        }
        if (sort == FINISH) {
            Collections.sort(list, new Comparator<TaskDTO>() {
                @Override
                public int compare(TaskDTO taskDTO1, TaskDTO taskDTO2) {
                    if (taskDTO1.equals(taskDTO2)) {
                        return 0;
                    }
                    return Printer.printDate(taskDTO1.getDateFinish()).compareTo(Printer.printDate(taskDTO2.getDateFinish()));
                }
            });
        }
        if (sort == STATUS) {
            Collections.sort(list, new Comparator<TaskDTO>() {
                @Override
                public int compare(TaskDTO taskDTO1, TaskDTO taskDTO2) {
                    if (taskDTO1.equals(taskDTO2)) {
                        return 0;
                    }
                    return taskDTO1.getStatus().compareTo(taskDTO2.getStatus());
                }
            });
        }
    }
}