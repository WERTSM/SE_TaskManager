package ru.khmelev.tm.command.util;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.api.endpoint.Project;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortedProject {
    public void sort(@NotNull List<Project> list, @NotNull Sort sort) {
        if (sort == Sort.CREATE) {
            Collections.sort(list, new Comparator<Project>() {
                @Override
                public int compare(Project project1, Project project2) {
                    if (project1.equals(project2)) {
                        return 0;
                    }
                    return project1.getDateCreate().compareTo(project2.getDateCreate());
                }
            });
        }
        if (sort == Sort.START) {
            Collections.sort(list, new Comparator<Project>() {
                @Override
                public int compare(Project project1, Project project2) {
                    if (project1.equals(project2)) {
                        return 0;
                    }
                    return project1.getDateStart().compareTo(project2.getDateStart());
                }
            });
        }
        if (sort == Sort.FINISH) {
            Collections.sort(list, new Comparator<Project>() {
                @Override
                public int compare(Project project1, Project project2) {
                    if (project1.equals(project2)) {
                        return 0;
                    }
                    return project1.getDateFinish().compareTo(project2.getDateFinish());
                }
            });
        }
        if (sort == Sort.STATUS) {
            Collections.sort(list, new Comparator<Project>() {
                @Override
                public int compare(Project project1, Project project2) {
                    if (project1.equals(project2)) {
                        return 0;
                    }
                    return project1.getStatus().compareTo(project2.getStatus());
                }
            });
        }
    }
}