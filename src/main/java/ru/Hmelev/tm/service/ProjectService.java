package ru.Hmelev.tm.service;

import ru.Hmelev.tm.entity.Project;
import ru.Hmelev.tm.repository.ProjectsRepository;

import java.util.Collection;
import java.util.Date;
import java.util.UUID;

public class ProjectService extends Service {
    private ProjectsRepository projectsRepository;
    private Project project;

    public ProjectService(ProjectsRepository projectsRepository) {
        this.projectsRepository = projectsRepository;
    }

    public void createProject(String name, String description, Date startDate, Date finishDate) {
        if (name != null && !name.isEmpty() && description != null && !description.isEmpty() && startDate != null && finishDate != null) {
            String id = UUID.randomUUID().toString();
            project = new Project(id, name, description, startDate, finishDate);
            projectsRepository.persist(id, project);
        }
    }

    public Collection<Project> findAllProjects() {
        return projectsRepository.findAll();
    }

    public void clearProject() {
        projectsRepository.removeAll();
    }

    public void editProject(String id, String name, String description, Date startDate, Date finishDate) {
        if (id != null && !id.isEmpty() && name != null && !name.isEmpty() && description != null && !description.isEmpty() && startDate != null && finishDate != null) {
            project = projectsRepository.findOne(id);
            project.setName(name);
            project.setDescription(description);
            project.setDateStart(startDate);
            project.setDataFinish(finishDate);
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