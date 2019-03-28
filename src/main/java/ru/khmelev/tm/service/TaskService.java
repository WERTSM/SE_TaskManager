package ru.khmelev.tm.service;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.api.IEntityRepository;
import ru.khmelev.tm.api.ITaskService;
import ru.khmelev.tm.entity.Sort;
import ru.khmelev.tm.entity.Task;
import ru.khmelev.tm.exception.ServiceException;
import ru.khmelev.tm.service.util.SortedEntity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class TaskService extends AbstractEntityService<Task> implements ITaskService {
    public TaskService(final IEntityRepository<Task> IEntityRepository) {
        super(IEntityRepository);
    }

    @Override
    public List<Task> listTaskFromProject(@NotNull final String idProject, @NotNull final String userId) {
        if (!idProject.isEmpty() && !userId.isEmpty()) {
            List<Task> list = new ArrayList<>(IEntityRepository.findAll(userId));
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

    @Override
    public void removeAllTaskFromProject(@NotNull final String idProject, @NotNull final String userId) {
        if (!idProject.isEmpty() && !userId.isEmpty()) {
            List<Task> list = new ArrayList<>(IEntityRepository.findAll(userId));
            Iterator<Task> it = list.iterator();
            while (it.hasNext()) {
                Task task = it.next();
                if (task.getIdProject().equals(idProject)) {
                    IEntityRepository.remove(task.getId(), userId);
                }
            }
        }
    }

    @Override
    public void sort(@NotNull List<Task> list, @NotNull Sort sortParameter) {
        new SortedEntity<Task>().sort(list, sortParameter);
    }
}