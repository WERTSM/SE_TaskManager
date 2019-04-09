package ru.khmelev.tm.command.util;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.api.endpoint.Project;
import ru.khmelev.tm.dSortdddd.Sort;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortedEntity {
    public void sort(@NotNull List<Project> list, @NotNull Sort sort) {
        if (sort == Sort.CREATE) {
            Collections.sort(list, new Comparator<Project>() {
                @Override
                public int compare(Project entity1, Project entity2) {
                    if (entity1.equals(entity2)) {
                        return 0;
                    }
                    return entity1.getDateCreate().compareTo(entity2.getDateCreate());
                }
            });
        }
        if (sort == Sort.START) {
            Collections.sort(list, new Comparator<Project>() {
                @Override
                public int compare(Project entity1, Project entity2) {
                    if (entity1.equals(entity2)) {
                        return 0;
                    }
                    return entity1.getDateStart().compareTo(entity2.getDateStart());
                }
            });
        }
        if (sort == Sort.FINISH) {
            Collections.sort(list, new Comparator<Project>() {
                @Override
                public int compare(Project entity1, Project entity2) {
                    if (entity1.equals(entity2)) {
                        return 0;
                    }
                    return entity1.getDateFinish().compareTo(entity2.getDateFinish());
                }
            });
        }
        if (sort == Sort.STATUS) {
            Collections.sort(list, new Comparator<Project>() {
                @Override
                public int compare(Project entity1, Project entity2) {
                    if (entity1.equals(entity2)) {
                        return 0;
                    }
                    return entity1.getStatus().compareTo(entity2.getStatus());
                }
            });
        }
    }
}