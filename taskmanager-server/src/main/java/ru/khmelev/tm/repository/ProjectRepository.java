package ru.khmelev.tm.repository;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.entity.Project;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Collection;

public class ProjectRepository {

    @NotNull
    private final EntityManager entityManager;

    public ProjectRepository(@NotNull final EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void persist(@NotNull Project project) {
        entityManager.persist(project);
    }

    @NotNull
    public Project findOne(@NotNull final String id, @NotNull final String userId) {
        String query = "Select project from Project project where project.id = :id and userId = :userId";
        TypedQuery<Project> typedQuery = entityManager.createQuery(query, Project.class);
        typedQuery.setParameter("id", id);
        typedQuery.setParameter("userId", userId);
        return typedQuery.getSingleResult();
    }

    @NotNull
    public Collection<Project> findAll(@NotNull final String userId) {
        String query = "Select project from Project project where userId = :userId";
        TypedQuery<Project> typedQuery = entityManager.createQuery(query, Project.class);
        typedQuery.setParameter("userId", userId);
        return typedQuery.getResultList();
    }

    public void merge(@NotNull final Project project) {
        entityManager.merge(project);
    }

    public void remove(@NotNull final Project project) {
        entityManager.remove(project);
    }

    public void removeAll(@NotNull final String userId) {
        for (Project project : findAll(userId)) {
            remove(project);
        }
    }
}