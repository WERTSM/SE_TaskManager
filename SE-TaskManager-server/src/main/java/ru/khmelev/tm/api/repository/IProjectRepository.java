package ru.khmelev.tm.api.repository;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.entity.Project;

import java.util.Collection;

public interface IProjectRepository extends IEntityRepository<Project> {

    void persist(@NotNull String id, @NotNull Project project);

    @NotNull
    @SneakyThrows
    Collection<Project> findAll(@NotNull String userId);
}