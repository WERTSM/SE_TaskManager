package ru.Hmelev.tm.service;

import ru.Hmelev.tm.entity.Project;
import ru.Hmelev.tm.repository.IProjectRepository;

import java.util.*;

public final class ProjectService extends Service {
    private IProjectRepository projectRepository;
    private Project project;

    public ProjectService(IProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public void createProject(String name, String description, Date startDate, Date finishDate, String userId) {
        if (name != null && !name.isEmpty() && description != null && !description.isEmpty() && startDate != null && finishDate != null) {
            String id = UUID.randomUUID().toString();
            project = new Project(id, name, description, startDate, finishDate, userId);
            projectRepository.persist(id, project);
        }
    }

    public Project findProject(String id) {
        if (id != null && !id.isEmpty()) {
            return projectRepository.findOne(id);
        }
        return null;
    }

    public Collection<Project> findAllProjects(String userId) {
        Collection<Project> list = new ArrayList<>(projectRepository.findAll());
        Iterator<Project> it = list.iterator();
        while (it.hasNext()) {
            project = it.next();
            if (!project.getUserId().equals(userId)) {
                it.remove();
            }
        }
        return list;
    }

    public void editProject(String id, String name, String description, Date startDate, Date finishDate, String userId) {
        if (id != null && !id.isEmpty() && name != null && !name.isEmpty() && description != null && !description.isEmpty() && startDate != null && finishDate != null) {
            project = projectRepository.findOne(id);
            project.setName(name);
            project.setDescription(description);
            project.setDateStart(startDate);
            project.setDataFinish(finishDate);
            project.setUserId(userId);
        }
    }

    public void removeProject(String id) {
        if (id != null && !id.isEmpty()) {
            projectRepository.remove(id);
        }
    }

    public void clearProject() {
        projectRepository.removeAll();
    }

}