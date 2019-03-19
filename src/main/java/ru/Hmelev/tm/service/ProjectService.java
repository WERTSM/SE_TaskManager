package ru.Hmelev.tm.service;

import ru.Hmelev.tm.entity.Project;
import ru.Hmelev.tm.repository.ProjectsRepository;

import java.util.Collection;
import java.util.Date;

public class ProjectService extends Service {
    private ProjectsRepository projectsRepository;
    private Project project;

    public ProjectService(ProjectsRepository projectsRepository) {
        this.projectsRepository = projectsRepository;
    }

    public void createProject(String id, String name, String description, Date startDate, Date finishDate) {
        project = new Project(id, name, description, startDate, finishDate);
        projectsRepository.persist(id, project);
    }

    public Collection<Project> findAllProjects() {
        return projectsRepository.findAll();
    }

    public void clearProject() {
        projectsRepository.removeAll();
    }

    public void listProject() {
        for (Project project : findAllProjects()) {
            showProject(project.getId());
        }
    }

    public void editProject(String id, String name, String description, Date startDate, Date finishDate) {
        project = projectsRepository.findOne(id);
        project.setName(name);
        project.setDescription(description);
        project.setDateStart(startDate);
        project.setDataFinish(finishDate);
    }

    public void showProject(String id) {
        project = projectsRepository.findOne(id);
        if (project != null) {
            System.out.println(
                    "[ ID = " + project.getId()
                            + "; Name = " + project.getName()
                            + "; Description = " + project.getDescription()
                            + "; Start date = " + DEFAULT_DATE_FORMAT.format(project.getDateStart())
                            + "; Finish date = " + DEFAULT_DATE_FORMAT.format(project.getDataFinish())
                            + " ]");
        }
    }

    public void removeProject(String id) {
        projectsRepository.remove(id);
    }
}