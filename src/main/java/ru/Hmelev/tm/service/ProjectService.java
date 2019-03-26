package ru.Hmelev.tm.service;

import ru.Hmelev.tm.api.EntityRepository;
import ru.Hmelev.tm.api.InterfaceProjectService;
import ru.Hmelev.tm.entity.Project;

public class ProjectService extends AbstractEntityService<Project> implements InterfaceProjectService {
    public ProjectService(final EntityRepository<Project> entityRepository) {
        super(entityRepository);
    }
}