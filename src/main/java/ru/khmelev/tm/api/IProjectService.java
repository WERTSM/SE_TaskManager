package ru.khmelev.tm.api;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.entity.Project;
import ru.khmelev.tm.entity.Sort;

import java.util.List;

public interface IProjectService extends IEntityService<Project> {

    void sort(@NotNull final Sort sortParameter, @NotNull final List<Project> list);
}