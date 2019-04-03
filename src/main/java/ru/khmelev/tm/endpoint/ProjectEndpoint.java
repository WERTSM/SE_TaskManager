package ru.khmelev.tm.endpoint;

import com.fasterxml.jackson.core.type.TypeReference;
import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.api.IProjectRepository;
import ru.khmelev.tm.api.IProjectService;
import ru.khmelev.tm.entity.Project;
import ru.khmelev.tm.entity.Sort;
import ru.khmelev.tm.service.util.SortedEntity;

import java.util.List;

public class ProjectEndpoint extends AbstractEntityEndpoint<Project> implements IProjectService {

    public ProjectEndpoint(final IProjectRepository projectRepository) {
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

}