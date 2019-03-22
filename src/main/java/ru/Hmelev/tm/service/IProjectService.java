package ru.Hmelev.tm.service;

import ru.Hmelev.tm.entity.Project;

import java.util.Collection;
import java.util.Date;

public interface IProjectService {
    void createProject(String name, String description, Date startDate, Date finishDate, String userId);
    Project findProject(String id);
    Collection<Project> findAllProjects(String userId);
    void editProject(String id, String name, String description, Date startDate, Date finishDate, String userId);
    void removeProject(String id);
    void clearProject();
}