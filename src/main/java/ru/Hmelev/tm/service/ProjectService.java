package ru.Hmelev.tm.service;

import ru.Hmelev.tm.api.IProjectRepository;
import ru.Hmelev.tm.entity.Project;
import ru.Hmelev.tm.entity.User;

import java.util.Collection;
import java.util.Date;
import java.util.UUID;

public final class ProjectService extends Service {
    private IProjectRepository projectRepository;
    private Project project;
    private String userId;

    public ProjectService(IProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public void createProject(String name, String description, Date startDate, Date finishDate, User user) {
        if (name != null && !name.isEmpty() && description != null && !description.isEmpty() && startDate != null && finishDate != null) {
            userId = user.getId();
            String id = UUID.randomUUID().toString();
            project = new Project(id, name, description, startDate, finishDate, userId);
            projectRepository.persist(id, project);
        }
    }

    public Project findProject(String id, User user) {
        if (id != null && !id.isEmpty() && user != null) {
            userId = user.getId();
            return projectRepository.findOne(id, userId);
        }
        return null;
    }

    public Collection<Project> findAllProjects(User user) {
        if (user != null) {
            userId = user.getId();
            return projectRepository.findAll(userId);
        }
        return null;
    }

    public void editProject(String id, String name, String description, Date startDate, Date finishDate, String userId) {
        if (id != null && !id.isEmpty() && name != null && !name.isEmpty() && description != null && !description.isEmpty() && startDate != null && finishDate != null) {
            project = projectRepository.findOne(id, userId);
            project.setName(name);
            project.setDescription(description);
            project.setDateStart(startDate);
            project.setDataFinish(finishDate);
            project.setUserId(userId);
        }
        projectRepository.merge(id, userId);
    }

    public void removeProject(String id, User user) {
        if (id != null && !id.isEmpty() && userId != null && !userId.isEmpty()) {
            userId = user.getId();
            projectRepository.remove(id, userId);
        }
    }

    public void clearProject(User user) {
        if (userId != null && !userId.isEmpty()) {
            userId = user.getId();
            projectRepository.removeAll(userId);
        }
    }
}