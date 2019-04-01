package ru.khmelev.tm.api;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.entity.Project;
import ru.khmelev.tm.entity.Sort;

import java.io.IOException;
import java.util.List;

public interface IProjectService extends IEntityFindNameOrDescService<Project> {

    void sort(@NotNull final List<Project> list, @NotNull final Sort sortParameter);

}