package ru.Hmelev.tm.repository;

import ru.Hmelev.tm.entity.Project;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ProjectsRepository implements Repository {
    private HashMap<UUID, Project> mapProject = new HashMap<>();

    @Override
    public void findAll() {
        for (Map.Entry<UUID, Project> item : mapProject.entrySet()) {
            item.getValue().viewProject();
        }
    }

    public Collection<Project> findAllProjects() {
        return mapProject.values();
    }

    public Project findOne(UUID uuid) {
        return this.mapProject.get(uuid);
    }

    public void persist(UUID id, Project project) {
        mapProject.put(id, project);
    }

    public void merge() {
    }

    @Override
    public void removeAll() {
        mapProject.clear();
    }

    public void remove(UUID id) {
        mapProject.remove(id);
    }
}