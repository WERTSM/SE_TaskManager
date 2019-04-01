package ru.khmelev.tm.service;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.api.IProjectRepository;
import ru.khmelev.tm.api.IProjectService;
import ru.khmelev.tm.entity.Project;
import ru.khmelev.tm.entity.Sort;
import ru.khmelev.tm.service.util.SortedEntity;

import java.util.List;

public class ProjectService extends AbstractEntityService<Project> implements IProjectService {

    public ProjectService(final IProjectRepository projectRepository) {
        super(projectRepository);
    }

    @Override
    public void sort(@NotNull List<Project> list, @NotNull Sort sortParameter) {
        new SortedEntity<Project>().sort(list, sortParameter);
    }

}