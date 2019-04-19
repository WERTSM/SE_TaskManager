package ru.khmelev.tm.command.util;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.api.endpoint.ProjectDTO;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortedProject {
    public void sort(@NotNull List<ProjectDTO> list, @NotNull Sort sort) {
        if (sort == Sort.CREATE) {
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
        if (sort == Sort.START) {
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
        if (sort == Sort.FINISH) {
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
        if (sort == Sort.STATUS) {
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
}