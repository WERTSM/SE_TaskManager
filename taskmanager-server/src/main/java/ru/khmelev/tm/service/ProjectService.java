package ru.khmelev.tm.service;

import org.apache.deltaspike.jpa.api.transaction.Transactional;
import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.api.repository.IProjectRepository;
import ru.khmelev.tm.api.service.IProjectService;
import ru.khmelev.tm.dto.ProjectDTO;
import ru.khmelev.tm.entity.Project;
import ru.khmelev.tm.exception.ServiceException;
import ru.khmelev.tm.repository.UserRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@ApplicationScoped
public class ProjectService implements IProjectService {

    @Inject
    private
    IProjectRepository projectRepository;

    @Inject
    private
    EntityManager entityManager;
    @Transactional
    @Override
    public void createEntity(@NotNull final String id, @NotNull final ProjectDTO projectDTO) {
        @NotNull final Project project = new Project();
        project.setId(id);
        fromDTOToProject(projectDTO, project);
        projectRepository.persist(project);
    }
    @Transactional
    @NotNull
    @Override
    public ProjectDTO findEntity(@NotNull final String id, @NotNull final String userId) {
        if (id.isEmpty() || userId.isEmpty()) throw new ServiceException();

        @NotNull final Project project = projectRepository.findById(id, userId);
        return fromProjectToDTO(project);
    }
    @Transactional
    @NotNull
    @Override
    public Collection<ProjectDTO> findAll(@NotNull final String userId) {
        if (userId.isEmpty()) throw new ServiceException();

        @NotNull final Collection<Project> list = projectRepository.findAll(userId);
        @NotNull final List<ProjectDTO> listDTO = new ArrayList<>();
        for (Project pr : list) {
            listDTO.add(fromProjectToDTO(pr));
        }
        return listDTO;
    }
    @Transactional
    @Override
    public void editEntity(@NotNull final String id, @NotNull ProjectDTO projectDTO, @NotNull final String userId) {
        if (id.isEmpty() || userId.isEmpty()) throw new ServiceException();

        Project project = projectRepository.findById(id, userId);
        projectRepository.merge(fromDTOToProject(projectDTO, project));
    }
    @Transactional
    @NotNull
    @Override
    public Collection<ProjectDTO> findAllName(@NotNull final String findParameter, @NotNull final String userId) {
        if (findParameter.isEmpty() || userId.isEmpty()) throw new ServiceException();

        @NotNull final List<Project> list = new ArrayList<>(projectRepository.findAll(userId));
        @NotNull final Iterator<Project> iterator = list.iterator();
        while (iterator.hasNext()) {
            if (!iterator.next().getName().contains(findParameter)) {
                iterator.remove();
            }
        }
        @NotNull final List<ProjectDTO> listDTO = new ArrayList<>();
        for (Project pr : list) {
            listDTO.add(fromProjectToDTO(pr));
        }
        return listDTO;
    }
    @Transactional
    @NotNull
    @Override
    public Collection<ProjectDTO> findAllDescription(@NotNull final String findParameter,
                                                     @NotNull final String userId) {
        if (findParameter.isEmpty() || userId.isEmpty()) throw new ServiceException();

        @NotNull final List<Project> list = new ArrayList<>(projectRepository.findAll(userId));
        @NotNull final Iterator<Project> iterator = list.iterator();
        while (iterator.hasNext()) {
            if (!iterator.next().getDescription().contains(findParameter)) {
                iterator.remove();
            }
        }
        @NotNull final List<ProjectDTO> listDTO = new ArrayList<>();
        for (Project pr : list) {
            listDTO.add(fromProjectToDTO(pr));
        }
        return listDTO;
    }
    @Transactional
    @Override
    public void removeEntity(@NotNull final String id, @NotNull final String userId) {
        if (id.isEmpty() || userId.isEmpty()) throw new ServiceException();

        projectRepository.remove(projectRepository.findById(id, userId));
    }
    @Transactional
    @Override
    public void clearEntity(@NotNull final String userId) {
        if (userId.isEmpty()) throw new ServiceException();

        projectRepository.removeAll(userId);
    }
    @Transactional
    @NotNull
    private Project fromDTOToProject(@NotNull final ProjectDTO projectDTO, @NotNull final Project project) {
        @NotNull final UserRepository userRepository = new UserRepository(entityManager);
        project.setName(projectDTO.getName());
        project.setDescription(projectDTO.getDescription());
        project.setDateStart(projectDTO.getDateStart());
        project.setDateFinish(projectDTO.getDateFinish());
        project.setDateCreate(projectDTO.getDateCreate());
        project.setStatus(projectDTO.getStatus());
        project.setUser(userRepository.findOne(projectDTO.getUserId()));
        return project;
    }

    @NotNull
    private ProjectDTO fromProjectToDTO(@NotNull final Project project) {
        @NotNull final ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setId(project.getId());
        projectDTO.setName(project.getName());
        projectDTO.setDescription(project.getDescription());
        projectDTO.setDateStart(project.getDateStart());
        projectDTO.setDateFinish(project.getDateFinish());
        projectDTO.setDateCreate(project.getDateCreate());
        projectDTO.setStatus(project.getStatus());
        projectDTO.setUserId(project.getUser().getId());
        return projectDTO;
    }
}