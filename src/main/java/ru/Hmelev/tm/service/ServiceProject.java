package ru.Hmelev.tm.service;

import ru.Hmelev.tm.entity.Project;
import ru.Hmelev.tm.repository.ProjectsRepository;

import java.util.Date;
import java.util.UUID;

public class ServiceProject extends Service {
    private ProjectsRepository projectsRepository;
    private Project project;

    public ServiceProject(ProjectsRepository projectsRepository) {
        this.projectsRepository = projectsRepository;
    }

    public void createProject(UUID id, String name, String description, Date startDate, Date finishDate) {
        project = new Project(id, name, description, startDate, finishDate);
        projectsRepository.persist(id, project);
    }

    public void clearProject() {
        projectsRepository.removeAll();
    }

    public void listProject() {
        projectsRepository.findAll();
    }

    public void editProject(UUID id, String name, String description, Date startDate, Date finishDate) {
        project = projectsRepository.findOne(id);
        project.setName(name);
        project.setDescription(description);
        project.setStartDate(startDate);
        project.setFinishDate(finishDate);
    }

    public boolean showProject(UUID id) {
        project = projectsRepository.findOne(id);
        if (project != null) {
            project.viewProject();
        }
        return true;
    }

    public void removeProject(UUID id) {
        projectsRepository.remove(id);
    }
}