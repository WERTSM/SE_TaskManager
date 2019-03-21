package ru.Hmelev.tm.repository;

import ru.Hmelev.tm.entity.Project;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ProjectsRepository {
    private Map<String, Project> mapProject = new HashMap<>();

    public Collection<Project> findAll() {
        return mapProject.values();
    }

    public Project findOne(String id) {
        if (id != null && !id.isEmpty()) {
            return this.mapProject.get(id);
        }
        return null;
    }

    public void persist(String id, Project project) {
        if (id != null && !id.isEmpty() && project != null) {
            mapProject.put(id, project);
        }
    }

    public void merge() {
    }

    public void removeAll() {
        mapProject.clear();
    }

    public void remove(String id) {
        if (id != null && !id.isEmpty()) {
            mapProject.remove(id);
        }
    }
}