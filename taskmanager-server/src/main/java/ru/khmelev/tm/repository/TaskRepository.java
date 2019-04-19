package ru.khmelev.tm.repository;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.api.repository.ITaskRepository;
import ru.khmelev.tm.entity.Task;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Collection;

public final class TaskRepository implements ITaskRepository {

    @NotNull
    private final EntityManager entityManager;

    public TaskRepository(@NotNull EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void persist(@NotNull final Task task) {
        entityManager.persist(task);
    }

    @Override
    @NotNull
    public Task findOne(@NotNull final String id, @NotNull final String userId) {
        @NotNull final String query = "Select task from Task task where task.id = :id and userId = :userId";
        @NotNull final TypedQuery<Task> typedQuery = entityManager.createQuery(query, Task.class);
        typedQuery.setParameter("id", id);
        typedQuery.setParameter("userId", userId);
        return typedQuery.getSingleResult();
    }

    @Override
    @NotNull
    public Collection<Task> findAll(@NotNull final String userId) {
        @NotNull final String query = "Select task from Task task where userId = :userId";
        @NotNull final TypedQuery<Task> typedQuery = entityManager.createQuery(query, Task.class);
        typedQuery.setParameter("userId", userId);
        return typedQuery.getResultList();
    }

    @Override
    public void merge(@NotNull final Task task) {
        entityManager.merge(task);
    }

    @Override
    public void remove(@NotNull final Task task) {
        entityManager.remove(task);
    }

    @Override
    public void removeAll(@NotNull final String userId) {
        for (Task task : findAll(userId)) {
            remove(task);
        }
    }

    @Override
    public Collection<Task> findAllTaskFromProject(@NotNull final String projectId, @NotNull final String userId) {
        @NotNull final String query = "Select task from Task task where projectId = :projectId and userId = :userId";
        @NotNull final TypedQuery<Task> typedQuery = entityManager.createQuery(query, Task.class);
        typedQuery.setParameter("projectId", projectId);
        typedQuery.setParameter("userId", userId);
        return typedQuery.getResultList();
    }
}