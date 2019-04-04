package ru.khmelev.tm.api;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.entity.Project;
import ru.khmelev.tm.entity.Sort;

import java.util.List;

public interface IProjectEndpoint extends IEntityService<Project> {

    void sort(@NotNull final List<Project> list, @NotNull final Sort sortParameter);
}