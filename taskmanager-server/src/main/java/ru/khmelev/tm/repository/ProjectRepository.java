//package ru.khmelev.tm.repository;
//
//import org.jetbrains.annotations.NotNull;
//import ru.khmelev.tm.api.repository.IProjectRepository;
//import ru.khmelev.tm.entity.Project;
//
//import javax.persistence.EntityManager;
//import javax.persistence.TypedQuery;
//import java.util.Collection;
//
//public final class ProjectRepository implements IProjectRepository {
//
//    @NotNull
//    private final EntityManager entityManager;
//
//    public ProjectRepository(@NotNull final EntityManager entityManager) {
//        this.entityManager = entityManager;
//    }
//
//    @Override
//    public void persist(@NotNull final Project project) {
//        entityManager.persist(project);
//    }
//
//    @Override
//    @NotNull
//    public Project findById(@NotNull final String id, @NotNull final String userId) {
//        @NotNull final String query = "Select project from Project project where project.id = :id and userId = :userId";
//        @NotNull final TypedQuery<Project> typedQuery = entityManager.createQuery(query, Project.class);
//        typedQuery.setParameter("id", id);
//        typedQuery.setParameter("userId", userId);
//        return typedQuery.getSingleResult();
//    }
//
//    @Override
//    @NotNull
//    public Collection<Project> findAll(@NotNull final String userId) {
//        @NotNull final String query = "Select project from Project project where userId = :userId";
//        @NotNull final TypedQuery<Project> typedQuery = entityManager.createQuery(query, Project.class);
//        typedQuery.setParameter("userId", userId);
//        return typedQuery.getResultList();
//    }
//
//    @Override
//    public void merge(@NotNull final Project project) {
//        entityManager.merge(project);
//    }
//
//    @Override
//    public void remove(@NotNull final Project project) {
//        entityManager.remove(project);
//    }
//
//    @Override
//    public void removeAll(@NotNull final String userId) {
//        for (Project project : findAll(userId)) {
//            remove(project);
//        }
//    }
//}