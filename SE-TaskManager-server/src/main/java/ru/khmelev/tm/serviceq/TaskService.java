package ru.khmelev.tm.serviceq;

import com.fasterxml.jackson.core.type.TypeReference;
import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.api.repository.ITaskRepository;
import ru.khmelev.tm.api.service.IEntityService;
import ru.khmelev.tm.api.service.ISerializationService;
import ru.khmelev.tm.api.service.ITaskService;
import ru.khmelev.tm.entity.Task;
import ru.khmelev.tm.exception.ServiceException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class TaskService extends AbstractEntityService<Task> implements IEntityService<Task>, ISerializationService<Task>, ITaskService {
    @NotNull
    private final ITaskRepository taskRepository;

    public TaskService(@NotNull final ITaskRepository taskRepository) {
        super(taskRepository);
        this.taskRepository = taskRepository;
    }

    protected TypeReference getTypeReference() {
        return new TypeReference<List<Task>>() {
        };
    }

    @NotNull
    public List<Task> listTaskFromProject(@NotNull final String idProject, @NotNull final String userId) {
        if (!idProject.isEmpty() && !userId.isEmpty()) {
            List<Task> list = new ArrayList<>(taskRepository.findAll(userId));
            Iterator<Task> it = list.iterator();
            while (it.hasNext()) {
                Task task = it.next();
                if (!task.getIdProject().equals(idProject)) {
                    it.remove();
                }
            }
            return list;
        }
        throw new ServiceException();
    }

    public void removeAllTaskFromProject(@NotNull final String idProject, @NotNull final String userId) {
        if (!idProject.isEmpty() && !userId.isEmpty()) {
            List<Task> list = new ArrayList<>(taskRepository.findAll(userId));
            Iterator<Task> it = list.iterator();
            while (it.hasNext()) {
                Task task = it.next();
                if (task.getIdProject().equals(idProject)) {
                    taskRepository.remove(task.getId(), userId);
                }
            }
        }
    }
}