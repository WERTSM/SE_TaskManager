package ru.khmelev.tm.service;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.api.EntityRepository;
import ru.khmelev.tm.api.ITaskService;
import ru.khmelev.tm.entity.Task;
import ru.khmelev.tm.exception.ServiceException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class TaskService extends AbstractEntityService<Task> implements ITaskService {
    public TaskService(final EntityRepository<Task> entityRepository) {
        super(entityRepository);
    }

    public List<Task> listTaskFromProject(@NotNull final String idProject, @NotNull final String userId) {
        if (!idProject.isEmpty() && !userId.isEmpty()) {
            List<Task> list = new ArrayList<>(entityRepository.findAll(userId));
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
            List<Task> list = new ArrayList<>(entityRepository.findAll(userId));
            Iterator<Task> it = list.iterator();
            while (it.hasNext()) {
                Task task = it.next();
                if (task.getIdProject().equals(idProject)) {
                    entityRepository.remove(task.getId(), userId);
                }
            }
        }
    }
}