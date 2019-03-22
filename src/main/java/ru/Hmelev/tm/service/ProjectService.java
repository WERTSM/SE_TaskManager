package ru.Hmelev.tm.service;

import ru.Hmelev.tm.entity.Project;
import ru.Hmelev.tm.repository.ProjectsRepository;

import java.util.*;

public class ProjectService extends Service {
    private ProjectsRepository projectsRepository;
    private Project project;

    public ProjectService(ProjectsRepository projectsRepository) {
        this.projectsRepository = projectsRepository;
    }

    public void createProject(String name, String description, Date startDate, Date finishDate, String userId) {
        if (name != null && !name.isEmpty() && description != null && !description.isEmpty() && startDate != null && finishDate != null) {
            String id = UUID.randomUUID().toString();
            project = new Project(id, name, description, startDate, finishDate, userId);
            projectsRepository.persist(id, project);
        }
    }

    public Collection<Project> findAllProjects(String userId) {
        Collection<Project> list = new ArrayList<>(projectsRepository.findAll());
        Iterator<Project> it = list.iterator();
        while (it.hasNext()) {
            project = it.next();
            if (!project.getUserId().equals(userId)) {
                it.remove();
            }
        }
        return list;
    }

    public void clearProject() {
        projectsRepository.removeAll();
    }

    public void editProject(String id, String name, String description, Date startDate, Date finishDate, String userId) {
        if (id != null && !id.isEmpty() && name != null && !name.isEmpty() && description != null && !description.isEmpty() && startDate != null && finishDate != null) {
            project = projectsRepository.findOne(id);
            project.setName(name);
            project.setDescription(description);
            project.setDateStart(startDate);
            project.setDataFinish(finishDate);
            project.setUserId(userId);
        }
    }

    public Project findProject(String id) {
        if (id != null && !id.isEmpty()) {
            return projectsRepository.findOne(id);
        }
        return null;
    }

    public void removeProject(String id) {
        if (id != null && !id.isEmpty()) {
            projectsRepository.remove(id);
        }
    }
}