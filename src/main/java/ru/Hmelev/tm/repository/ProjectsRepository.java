package ru.Hmelev.tm.repository;

import ru.Hmelev.tm.entity.Project;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ProjectsRepository implements Repository {

    private static ProjectsRepository _instance = new ProjectsRepository();
    private HashMap<UUID, Project> mapProject = new HashMap<>();

    private ProjectsRepository() {
    }

    public static ProjectsRepository getInstance() {
        if (_instance == null)
            _instance = new ProjectsRepository();
        return _instance;
    }

    @Override
    public void findAll() {
        for (Map.Entry<UUID, Project> item : mapProject.entrySet()) {
            item.getValue().viewProject();
        }
    }

    public Project findOne(UUID uuid) {
        return this.mapProject.get(uuid);
    }

    public void persist(UUID uuid, Project project) {
        mapProject.put(uuid, project);
    }

    public void merge() {
    }

    @Override
    public void removeAll() {
        mapProject.clear();
    }

    public void remove(UUID uuid) {
        mapProject.remove(uuid);
    }
}