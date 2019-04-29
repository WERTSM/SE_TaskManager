package ru.khmelev.tm.service;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.api.repository.IProjectRepository;
import ru.khmelev.tm.api.service.ITaskService;
import ru.khmelev.tm.dto.TaskDTO;
import ru.khmelev.tm.entity.Task;
import ru.khmelev.tm.exception.ServiceException;
import ru.khmelev.tm.repository.TaskRepository;
import ru.khmelev.tm.repository.UserRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@ApplicationScoped
public class TaskService implements ITaskService {

    @Inject
    IProjectRepository projectRepository;
    @Inject
    private EntityManagerFactory entityManagerFactory;

    private EntityManager entityManager;

    @Override
    public void createEntity(@NotNull final String id, @NotNull final TaskDTO taskDTO) {
        try {
            entityManager = entityManagerFactory.createEntityManager();
            @NotNull final EntityTransaction transaction = entityManager.getTransaction();
            @NotNull final TaskRepository taskRepository = new TaskRepository(entityManager);
            transaction.begin();

            @NotNull final Task task = new Task();
            task.setId(id);
            fromDTOToTask(taskDTO, task);

            taskRepository.persist(task);
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
    public TaskDTO findEntity(@NotNull final String id, @NotNull final String userId) {
        if (id.isEmpty() || userId.isEmpty()) throw new ServiceException();

        try {
            entityManager = entityManagerFactory.createEntityManager();
            @NotNull final EntityTransaction transaction = entityManager.getTransaction();
            @NotNull final TaskRepository taskRepository = new TaskRepository(entityManager);
            transaction.begin();

            @NotNull final Task task = taskRepository.findOne(id, userId);
            return fromTaskToDTO(task);
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new ServiceException(e);
        } finally {
            entityManager.close();
        }
    }

    @NotNull
    @Override
    public Collection<TaskDTO> findAll(@NotNull final String userId) {
        if (userId.isEmpty()) throw new ServiceException();

        try {
            entityManager = entityManagerFactory.createEntityManager();
            @NotNull final EntityTransaction transaction = entityManager.getTransaction();
            @NotNull final TaskRepository taskRepository = new TaskRepository(entityManager);
            transaction.begin();

            @NotNull final Collection<Task> list = taskRepository.findAll(userId);
            @NotNull final List<TaskDTO> listDTO = new ArrayList<>();
            for (Task task : list) {
                listDTO.add(fromTaskToDTO(task));
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
    public void editEntity(@NotNull final String id, @NotNull TaskDTO taskDTO, @NotNull final String userId) {
        if (id.isEmpty() || userId.isEmpty()) throw new ServiceException();

        try {
            entityManager = entityManagerFactory.createEntityManager();
            @NotNull final EntityTransaction transaction = entityManager.getTransaction();
            @NotNull final TaskRepository taskRepository = new TaskRepository(entityManager);
            transaction.begin();

            @NotNull final Task task = taskRepository.findOne(id, userId);
            taskRepository.merge(fromDTOToTask(taskDTO, task));
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
    public Collection<TaskDTO> findAllName(@NotNull final String findParameter, @NotNull final String userId) {
        if (findParameter.isEmpty() || userId.isEmpty()) throw new ServiceException();

        try {
            entityManager = entityManagerFactory.createEntityManager();
            @NotNull final EntityTransaction transaction = entityManager.getTransaction();
            @NotNull final TaskRepository taskRepository = new TaskRepository(entityManager);
            transaction.begin();

            @NotNull final List<Task> list = new ArrayList<>(taskRepository.findAll(userId));
            @NotNull final Iterator<Task> iterator = list.iterator();
            while (iterator.hasNext()) {
                if (!iterator.next().getName().contains(findParameter)) {
                    iterator.remove();
                }
            }
            @NotNull final List<TaskDTO> listDTO = new ArrayList<>();
            for (Task task : list) {
                listDTO.add(fromTaskToDTO(task));
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
    public Collection<TaskDTO> findAllDescription(@NotNull final String findParameter, @NotNull final String userId) {
        if (findParameter.isEmpty() || userId.isEmpty()) throw new ServiceException();

        try {
            entityManager = entityManagerFactory.createEntityManager();
            @NotNull final EntityTransaction transaction = entityManager.getTransaction();
            @NotNull final TaskRepository taskRepository = new TaskRepository(entityManager);
            transaction.begin();

            @NotNull final List<Task> list = new ArrayList<>(taskRepository.findAll(userId));
            @NotNull final Iterator<Task> iterator = list.iterator();
            while (iterator.hasNext()) {
                if (!iterator.next().getDescription().contains(findParameter)) {
                    iterator.remove();
                }
            }
            @NotNull final List<TaskDTO> listDTO = new ArrayList<>();
            for (Task task : list) {
                listDTO.add(fromTaskToDTO(task));
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
            @NotNull final TaskRepository taskRepository = new TaskRepository(entityManager);
            transaction.begin();
            taskRepository.remove(taskRepository.findOne(id, userId));
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
            @NotNull final TaskRepository taskRepository = new TaskRepository(entityManager);
            transaction.begin();
            taskRepository.removeAll(userId);
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
    public Collection<TaskDTO> listTaskFromProject(@NotNull String projectId, @NotNull String userId) {
        if (projectId.isEmpty() || userId.isEmpty()) throw new ServiceException();

        try {
            entityManager = entityManagerFactory.createEntityManager();
            @NotNull final EntityTransaction transaction = entityManager.getTransaction();
            @NotNull final TaskRepository taskRepository = new TaskRepository(entityManager);
            transaction.begin();

            @NotNull final Collection<Task> list = taskRepository.findAllTaskFromProject(projectId, userId);

            @NotNull final List<TaskDTO> listDTO = new ArrayList<>();
            for (Task task : list) {
                listDTO.add(fromTaskToDTO(task));
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

    @NotNull
    private Task fromDTOToTask(@NotNull final TaskDTO taskDTO, @NotNull final Task task) {
        @NotNull final UserRepository userRepository = new UserRepository(entityManager);
        task.setName(taskDTO.getName());
        task.setDescription(taskDTO.getDescription());
        task.setDateStart(taskDTO.getDateStart());
        task.setDateFinish(taskDTO.getDateFinish());
        task.setDateCreate(taskDTO.getDateCreate());
        task.setStatus(taskDTO.getStatus());
        task.setProject(projectRepository.findById(taskDTO.getProjectId(), taskDTO.getUserId()));
        task.setUser(userRepository.findOne(taskDTO.getUserId()));
        return task;
    }

    @NotNull
    private TaskDTO fromTaskToDTO(@NotNull final Task task) {
        @NotNull final TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(task.getId());
        taskDTO.setName(task.getName());
        taskDTO.setDescription(task.getDescription());
        taskDTO.setDateStart(task.getDateStart());
        taskDTO.setDateFinish(task.getDateFinish());
        taskDTO.setDateCreate(task.getDateCreate());
        taskDTO.setStatus(task.getStatus());
        taskDTO.setProjectId(task.getProject().getId());
        taskDTO.setUserId(task.getUser().getId());
        return taskDTO;
    }
}