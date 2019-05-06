package ru.khmelev.tm.service;

import org.apache.deltaspike.jpa.api.transaction.Transactional;
import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.api.repository.IProjectRepository;
import ru.khmelev.tm.api.repository.ITaskRepository;
import ru.khmelev.tm.api.repository.IUserRepository;
import ru.khmelev.tm.api.service.ITaskService;
import ru.khmelev.tm.dto.TaskDTO;
import ru.khmelev.tm.entity.Task;
import ru.khmelev.tm.exception.ServiceException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@ApplicationScoped
public class TaskService implements ITaskService {

    @Inject
    private ITaskRepository taskRepository;

    @Inject
    private IProjectRepository projectRepository;

    @Inject
    private IUserRepository userRepository;

    @Override
    @Transactional
    public void createEntity(@NotNull final String id, @NotNull final TaskDTO taskDTO) {
        @NotNull final Task task = new Task();
        task.setId(id);
        fromDTOToTask(taskDTO, task);

        taskRepository.persist(task);
    }

    @NotNull
    @Override
    @Transactional
    public TaskDTO findEntity(@NotNull final String id, @NotNull final String userId) {
        if (id.isEmpty() || userId.isEmpty()) throw new ServiceException();
        @NotNull final Task task = taskRepository.findById(id, userId);
        return fromTaskToDTO(task);
    }

    @NotNull
    @Override
    @Transactional
    public Collection<TaskDTO> findAll(@NotNull final String userId) {
        if (userId.isEmpty()) throw new ServiceException();
        @NotNull final Collection<Task> list = taskRepository.findAll(userId);
        @NotNull final List<TaskDTO> listDTO = new ArrayList<>();
        for (Task task : list) {
            listDTO.add(fromTaskToDTO(task));
        }
        return listDTO;
    }

    @Override
    @Transactional
    public void editEntity(@NotNull final String id, @NotNull TaskDTO taskDTO, @NotNull final String userId) {
        if (id.isEmpty() || userId.isEmpty()) throw new ServiceException();
        @NotNull final Task task = taskRepository.findById(id, userId);
        taskRepository.merge(fromDTOToTask(taskDTO, task));
    }

    @NotNull
    @Override
    @Transactional
    public Collection<TaskDTO> findAllName(@NotNull final String findParameter, @NotNull final String userId) {
        if (findParameter.isEmpty() || userId.isEmpty()) throw new ServiceException();
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
    }

    @NotNull
    @Override
    @Transactional
    public Collection<TaskDTO> findAllDescription(@NotNull final String findParameter, @NotNull final String userId) {
        if (findParameter.isEmpty() || userId.isEmpty()) throw new ServiceException();
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
    }

    @Override
    @Transactional
    public void removeEntity(@NotNull final String id, @NotNull final String userId) {
        if (id.isEmpty() || userId.isEmpty()) throw new ServiceException();
        taskRepository.remove(taskRepository.findById(id, userId));
    }

    @Override
    @Transactional
    public void clearEntity(@NotNull final String userId) {
        if (userId.isEmpty()) throw new ServiceException();
        taskRepository.removeAll(userId);
    }

    @NotNull
    @Override
    @Transactional
    public Collection<TaskDTO> listTaskFromProject(@NotNull String projectId, @NotNull String userId) {
        if (projectId.isEmpty() || userId.isEmpty()) throw new ServiceException();
        @NotNull final Collection<Task> list = taskRepository.findAllTaskFromProject(projectId, userId);

        @NotNull final List<TaskDTO> listDTO = new ArrayList<>();
        for (Task task : list) {
            listDTO.add(fromTaskToDTO(task));
        }
        return listDTO;
    }

    @NotNull
    private Task fromDTOToTask(@NotNull final TaskDTO taskDTO, @NotNull final Task task) {
        task.setName(taskDTO.getName());
        task.setDescription(taskDTO.getDescription());
        task.setDateStart(taskDTO.getDateStart());
        task.setDateFinish(taskDTO.getDateFinish());
        task.setDateCreate(taskDTO.getDateCreate());
        task.setStatus(taskDTO.getStatus());
        task.setProject(projectRepository.findById(taskDTO.getProjectId(), taskDTO.getUserId()));
        task.setUser(userRepository.findById(taskDTO.getUserId()));
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