package ru.Hmelev.tm.api;

import ru.Hmelev.tm.entity.Project;

import java.util.Collection;

public interface IProjectRepository {
    void persist(String id, Project project);

    Project findOne(String id, String userId);

    Collection<Project> findAll(String userId);

    void merge(String id, String userId);

    void remove(String id, String userId);

    void removeAll(String userId);
}