package ru.Hmelev.tm.repository;

import ru.Hmelev.tm.api.IProjectRepository;
import ru.Hmelev.tm.entity.Project;

import java.util.*;

public final class ProjectRepository implements IProjectRepository {
    private final Map<String, Project> mapProject = new HashMap<>();

    @Override
    public void persist(String id, Project project) {
        if (id != null && !id.isEmpty() && project != null) {
            mapProject.put(id, project);
        }
    }

    @Override
    public Project findOne(String id, String userId) {
        if (id != null && !id.isEmpty() && userId != null && !userId.isEmpty()) {
            Collection<Project> list = new ArrayList<>(findAll(userId));
            for (Project project : list) {
                if (project.getId().equals(id)) {
                    return project;
                }
            }
        }
        return null;
    }

    @Override
    public Collection<Project> findAll(String userId) {
        if (userId != null && !userId.isEmpty()) {
            Collection<Project> list = new ArrayList<>(mapProject.values());
            Iterator<Project> it = list.iterator();
            while (it.hasNext()) {
                Project project = it.next();
                if (!project.getUserId().equals(userId)) {
                    it.remove();
                }
            }
            return list;
        }
        return null;
    }

    @Override
    public void merge(String id, String userId) {
    }

    @Override
    public void remove(String id, String userId) {
        if (id != null && !id.isEmpty() && userId != null && !userId.isEmpty()) {
            Collection<Project> list = mapProject.values();
            Iterator<Project> it = list.iterator();
            while (it.hasNext()) {
                Project project = it.next();
                if (project.getId().equals(id) && project.getUserId().equals(userId)) {
                    it.remove();
                    return;
                }
            }
        }
    }

    @Override
    public void removeAll(String userId) {
        if (userId != null && !userId.isEmpty()) {
            Collection<Project> list = mapProject.values();
            list.clear();
        }
    }
}