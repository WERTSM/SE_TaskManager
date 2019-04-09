package ru.khmelev.tm.command.util;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.api.endpoint.Task;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortedTask {
    public void sort(@NotNull List<Task> list, @NotNull Sort sort) {
        if (sort == Sort.CREATE) {
            Collections.sort(list, new Comparator<Task>() {
                @Override
                public int compare(Task task1, Task task2) {
                    if (task1.equals(task2)) {
                        return 0;
                    }
                    return task1.getDateCreate().compareTo(task2.getDateCreate());
                }
            });
        }
        if (sort == Sort.START) {
            Collections.sort(list, new Comparator<Task>() {
                @Override
                public int compare(Task task1, Task task2) {
                    if (task1.equals(task2)) {
                        return 0;
                    }
                    return task1.getDateStart().compareTo(task2.getDateStart());
                }
            });
        }
        if (sort == Sort.FINISH) {
            Collections.sort(list, new Comparator<Task>() {
                @Override
                public int compare(Task task1, Task task2) {
                    if (task1.equals(task2)) {
                        return 0;
                    }
                    return task1.getDateFinish().compareTo(task2.getDateFinish());
                }
            });
        }
        if (sort == Sort.STATUS) {
            Collections.sort(list, new Comparator<Task>() {
                @Override
                public int compare(Task task1, Task task2) {
                    if (task1.equals(task2)) {
                        return 0;
                    }
                    return task1.getStatus().compareTo(task2.getStatus());
                }
            });
        }
    }
}