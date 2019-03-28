package ru.khmelev.tm.service;

import ru.khmelev.tm.api.EntityRepository;
import ru.khmelev.tm.api.IProjectService;
import ru.khmelev.tm.entity.Project;

public class ProjectService extends AbstractEntityService<Project> implements IProjectService {
    public ProjectService(final EntityRepository<Project> entityRepository) {
        super(entityRepository);
    }
}