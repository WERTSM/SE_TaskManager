package ru.khmelev.tm.service;

import com.fasterxml.jackson.core.type.TypeReference;
import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.api.IProjectRepository;
import ru.khmelev.tm.api.IProjectService;
import ru.khmelev.tm.entity.Project;
import ru.khmelev.tm.entity.Sort;
import ru.khmelev.tm.service.util.SortedEntity;

import java.util.Collection;
import java.util.List;

public class ProjectService extends AbstractEntityService<Project> implements IProjectService {

    public ProjectService(final IProjectRepository projectRepository) {
        super(projectRepository);
    }

    protected TypeReference getTypeReference() {
        return new TypeReference<List<Project>>() {
        };
    }

    @Override
    public void sort(@NotNull List<Project> list, @NotNull Sort sortParameter) {
        new SortedEntity<Project>().sort(list, sortParameter);
    }

    @NotNull
    @Override
    public Project findEntity(@NotNull String id) {
        return null;
    }

    @Override
    public @NotNull Collection<Project> findAll() {
        return null;
    }

    @Override
    public void editEntity(@NotNull String id, @NotNull Project entity) { }

    @Override
    public void removeEntity(@NotNull String id) {}

    @Override
    public void clearEntity() {}
}