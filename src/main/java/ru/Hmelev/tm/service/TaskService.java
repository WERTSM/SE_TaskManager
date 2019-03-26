package ru.Hmelev.tm.service;

import org.jetbrains.annotations.NotNull;
import ru.Hmelev.tm.api.EntityRepository;
import ru.Hmelev.tm.api.InterfaceTaskService;
import ru.Hmelev.tm.entity.Task;
import ru.Hmelev.tm.entity.User;
import ru.Hmelev.tm.exception.ServiceException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class TaskService extends AbstractEntityService<Task> implements InterfaceTaskService {
    public TaskService(final EntityRepository<Task> entityRepository) {
        super(entityRepository);
    }

    public List<Task> listTaskFromProject(@NotNull final String idProject, @NotNull final User user) {
        if (!idProject.isEmpty()) {
            userId = user.getId();
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

    public void removeAllTaskFromProject(@NotNull final String idProject, @NotNull final User user) {
        if (!idProject.isEmpty()) {
            userId = user.getId();
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