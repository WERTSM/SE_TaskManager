package ru.Hmelev.tm.service;

import ru.Hmelev.tm.api.EntityRepository;
import ru.Hmelev.tm.api.InterfaceTaskService;
import ru.Hmelev.tm.entity.Task;
import ru.Hmelev.tm.entity.User;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class TaskService extends AbstractEntityService<Task> implements InterfaceTaskService {
    public TaskService(final EntityRepository<Task> entityRepository) {
        super(entityRepository);
    }

    public List<Task> listTaskFromProject(final String idProject, final User user) {
        if (idProject != null && !idProject.isEmpty() && user != null) {
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
        return null;
    }

    public void removeAllTaskFromProject(final String idProject, final User user) {
        if (idProject != null && !idProject.isEmpty() && user != null) {
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