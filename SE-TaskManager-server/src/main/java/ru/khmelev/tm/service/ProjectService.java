package ru.khmelev.tm.service;

import com.fasterxml.jackson.core.type.TypeReference;
import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.api.repository.IProjectRepository;
import ru.khmelev.tm.api.service.IEntityService;
import ru.khmelev.tm.api.service.IProjectService;
import ru.khmelev.tm.api.service.ISerializationService;
import ru.khmelev.tm.entity.Project;

import java.util.List;

public class ProjectService extends AbstractEntityService<Project> implements IEntityService<Project>, ISerializationService<Project>, IProjectService {

    public ProjectService(@NotNull final IProjectRepository projectRepository) {
        super(projectRepository);
    }

    protected TypeReference getTypeReference() {
        return new TypeReference<List<Project>>() {
        };
    }
}