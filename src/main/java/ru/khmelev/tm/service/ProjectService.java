package ru.khmelev.tm.service;

import com.fasterxml.jackson.core.type.TypeReference;
import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.api.IEntityService;
import ru.khmelev.tm.api.IProjectRepository;
import ru.khmelev.tm.api.IProjectService;
import ru.khmelev.tm.api.ISerializationService;
import ru.khmelev.tm.entity.Project;
import ru.khmelev.tm.entity.Sort;
import ru.khmelev.tm.service.util.SortedEntity;

import java.util.List;

public class ProjectService extends AbstractEntityService<Project> implements IEntityService<Project>, ISerializationService<Project>, IProjectService {

    public ProjectService(@NotNull final IProjectRepository projectRepository) {
        super(projectRepository);
    }

    protected TypeReference getTypeReference() {
        return new TypeReference<List<Project>>() {
        };
    }

    public void sort(@NotNull final Sort sortParameter, @NotNull final List<Project> list) {
        new SortedEntity<Project>().sort(list, sortParameter);
    }
}