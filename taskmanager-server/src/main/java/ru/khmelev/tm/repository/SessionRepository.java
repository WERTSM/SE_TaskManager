package ru.khmelev.tm.repository;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.api.repository.ISessionRepository;
import ru.khmelev.tm.entity.Session;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Collection;

public class SessionRepository implements ISessionRepository {

    @NotNull
    private final EntityManager entityManager;
    private Object TypedQuery;

    public SessionRepository(@NotNull final EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void persist(@NotNull final Session session) {
        entityManager.persist(session);
    }

    @Override
    @NotNull
    public Session findOne(@NotNull final String id) {
        @NotNull final String query = "SELECT session FROM Session session WHERE id = :id";
        @NotNull final TypedQuery<Session> typedQuery = entityManager.createQuery(query, Session.class);
        typedQuery.setParameter("id", id);
        return typedQuery.getSingleResult();
    }

    @Override
    @NotNull
    public Collection<Session> findAll() {
        @NotNull final String query = "Select session from Session session";
        @NotNull final TypedQuery<Session> typedQuery = entityManager.createQuery(query, Session.class);
        return typedQuery.getResultList();
    }

    @Override
    public void merge(@NotNull final Session session) {
        entityManager.merge(session);
    }

    @Override
    public void remove(@NotNull final Session session) {
        entityManager.remove(session);
    }

    @Override
    public void removeAll() {
        for (Session session : findAll()) {
            remove(session);
        }
    }
}