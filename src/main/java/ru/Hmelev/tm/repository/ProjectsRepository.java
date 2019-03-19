package ru.Hmelev.tm.repository;

import ru.Hmelev.tm.entity.Project;

import java.util.Collection;
import java.util.HashMap;

public class ProjectsRepository {
    private HashMap<String, Project> mapProject = new HashMap<>();

    public Collection<Project> findAll() {
        return mapProject.values();
    }

    public Project findOne(String id) {
        return this.mapProject.get(id);
    }

    public void persist(String id, Project project) {
        mapProject.put(id, project);
    }

    public void merge() {
    }

    public void removeAll() {
        mapProject.clear();
    }

    public void remove(String id) {
        mapProject.remove(id);
    }
}