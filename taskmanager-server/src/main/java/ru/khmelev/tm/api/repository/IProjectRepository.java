package ru.khmelev.tm.api.repository;

import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.Repository;
import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.entity.Project;

import java.util.Collection;

@Repository(forEntity = Project.class)
public interface IProjectRepository {

    void persist(@NotNull Project project);

    @NotNull Project findOne(@NotNull final String id, @NotNull final String userId);

//    @Query(named = )
    @NotNull Collection<Project> findAll(@NotNull final String userId);

    void merge(@NotNull final Project project);

    void remove(@NotNull final Project project);

    void removeAll(@NotNull final String userId);
}
