package ru.Hmelev.tm.repository;

import ru.Hmelev.tm.entity.Project;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public final class ProjectRepository implements IProjectRepository {
    private final Map<String, Project> mapProject = new HashMap<>();

    @Override
    public void persist(String id, Project project) {
        if (id != null && !id.isEmpty() && project != null) {
            mapProject.put(id, project);
        }
    }

    @Override
    public Project findOne(String id) {
        if (id != null && !id.isEmpty()) {
            return this.mapProject.get(id);
        }
        return null;
    }

    @Override
    public Collection<Project> findAll() {
        return mapProject.values();
    }

    @Override
    public void merge() {
    }

    @Override
    public void remove(String id) {
        if (id != null && !id.isEmpty()) {
            mapProject.remove(id);
        }
    }

    @Override
    public void removeAll() {
        mapProject.clear();
    }
}