package ru.Hmelev.tm.repository;

import ru.Hmelev.tm.entity.Project;

import java.util.Collection;

public interface IProjectRepository {
    void persist(String id, Project project);

    Project findOne(String id);

    Collection<Project> findAll();

    void merge();

    void remove(String id);

    void removeAll();
}