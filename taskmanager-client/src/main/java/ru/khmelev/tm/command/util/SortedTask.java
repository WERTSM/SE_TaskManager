package ru.khmelev.tm.command.util;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.api.endpoint.TaskDTO;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortedTask {
    public void sort(@NotNull List<TaskDTO> list, @NotNull Sort sort) {
        if (sort == Sort.CREATE) {
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
        if (sort == Sort.START) {
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
        if (sort == Sort.FINISH) {
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
        if (sort == Sort.STATUS) {
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