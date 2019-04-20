package ru.khmelev.tm.service;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.api.service.IProjectService;
import ru.khmelev.tm.entity.Project;
import ru.khmelev.tm.dto.ProjectDTO;
import ru.khmelev.tm.exception.ServiceException;
import ru.khmelev.tm.repository.ProjectRepository;
import ru.khmelev.tm.repository.UserRepository;
import ru.khmelev.tm.util.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class ProjectService implements IProjectService {

    @NotNull
    private final EntityManagerFactory entityManagerFactory = HibernateUtil.getEntityManagerFactory();

    private EntityManager entityManager;

    @Override
    public void createEntity(@NotNull final String id, @NotNull final ProjectDTO projectDTO) {
        try {
            entityManager = entityManagerFactory.createEntityManager();
            @NotNull final EntityTransaction transaction = entityManager.getTransaction();
            @NotNull final ProjectRepository projectRepository = new ProjectRepository(entityManager);
            transaction.begin();

            @NotNull final Project project = new Project();
            project.setId(id);
            fromDTOToProject(projectDTO, project);

            projectRepository.persist(project);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new ServiceException(e);
        } finally {
            entityManager.close();
        }
    }

    @NotNull
    @Override
    public ProjectDTO findEntity(@NotNull final String id, @NotNull final String userId) {
        if (id.isEmpty() || userId.isEmpty()) throw new ServiceException();

        try {
            entityManager = entityManagerFactory.createEntityManager();
            @NotNull final EntityTransaction transaction = entityManager.getTransaction();
            @NotNull final ProjectRepository projectRepository = new ProjectRepository(entityManager);
            transaction.begin();

            @NotNull final Project project = projectRepository.findOne(id, userId);
            return fromProjectToDTO(project);
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new ServiceException(e);
        } finally {
            entityManager.close();
        }
    }

    @NotNull
    @Override
    public Collection<ProjectDTO> findAll(@NotNull final String userId) {
        if (userId.isEmpty()) throw new ServiceException();

        try {
            entityManager = entityManagerFactory.createEntityManager();
            @NotNull final EntityTransaction transaction = entityManager.getTransaction();
            @NotNull final ProjectRepository projectRepository = new ProjectRepository(entityManager);
            transaction.begin();

            @NotNull final Collection<Project> list = projectRepository.findAll(userId);
            @NotNull final List<ProjectDTO> listDTO = new ArrayList<>();
            for (Project pr : list) {
                listDTO.add(fromProjectToDTO(pr));
            }
            transaction.commit();
            return listDTO;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new ServiceException(e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void editEntity(@NotNull final String id, @NotNull ProjectDTO projectDTO, @NotNull final String userId) {
        if (id.isEmpty() || userId.isEmpty()) throw new ServiceException();

        try {
            entityManager = entityManagerFactory.createEntityManager();
            @NotNull final EntityTransaction transaction = entityManager.getTransaction();
            @NotNull final ProjectRepository projectRepository = new ProjectRepository(entityManager);
            transaction.begin();

            Project project = projectRepository.findOne(id, userId);
            projectRepository.merge(fromDTOToProject(projectDTO, project));
            transaction.commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new ServiceException(e);
        } finally {
            entityManager.close();
        }
    }

    @NotNull
    @Override
    public Collection<ProjectDTO> findAllName(@NotNull final String findParameter, @NotNull final String userId) {
        if (findParameter.isEmpty() || userId.isEmpty()) throw new ServiceException();

        try {
            entityManager = entityManagerFactory.createEntityManager();
            @NotNull final EntityTransaction transaction = entityManager.getTransaction();
            @NotNull final ProjectRepository projectRepository = new ProjectRepository(entityManager);
            transaction.begin();

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
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new ServiceException(e);
        } finally {
            entityManager.close();
        }
    }

    @NotNull
    @Override
    public Collection<ProjectDTO> findAllDescription(@NotNull final String findParameter, @NotNull final String userId) {
        if (findParameter.isEmpty() || userId.isEmpty()) throw new ServiceException();

        try {
            entityManager = entityManagerFactory.createEntityManager();
            @NotNull final EntityTransaction transaction = entityManager.getTransaction();
            @NotNull final ProjectRepository projectRepository = new ProjectRepository(entityManager);
            transaction.begin();

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
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new ServiceException(e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void removeEntity(@NotNull final String id, @NotNull final String userId) {
        if (id.isEmpty() || userId.isEmpty()) throw new ServiceException();

        try {
            entityManager = entityManagerFactory.createEntityManager();
            @NotNull final EntityTransaction transaction = entityManager.getTransaction();
            @NotNull final ProjectRepository projectRepository = new ProjectRepository(entityManager);
            transaction.begin();
            projectRepository.remove(projectRepository.findOne(id, userId));
            transaction.commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new ServiceException(e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void clearEntity(@NotNull final String userId) {
        if (userId.isEmpty()) throw new ServiceException();

        try {
            entityManager = entityManagerFactory.createEntityManager();
            @NotNull final EntityTransaction transaction = entityManager.getTransaction();
            @NotNull final ProjectRepository projectRepository = new ProjectRepository(entityManager);
            transaction.begin();
            projectRepository.removeAll(userId);
            transaction.commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new ServiceException(e);
        } finally {
            entityManager.close();
        }
    }

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