package ru.khmelev.tm.repository;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.api.repository.IUserRepository;
import ru.khmelev.tm.entity.User;
import ru.khmelev.tm.exception.RepositoryException;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Collection;

public final class UserRepository implements IUserRepository {

    @NotNull
    private final EntityManager entityManager;

    public UserRepository(@NotNull final EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void persist(@NotNull final User user) {
        entityManager.persist(user);
    }

    @Override
    @NotNull
    public User findOne(@NotNull final String id) {
        @NotNull final String query = "SELECT user FROM User user WHERE id = :id";
        @NotNull final TypedQuery<User> typedQuery = entityManager.createQuery(query, User.class);
        typedQuery.setParameter("id", id);
        return typedQuery.getSingleResult();
    }

    @Override
    @NotNull
    public Collection<User> findAll() {
        @NotNull final String query = "Select user from User user";
        @NotNull final TypedQuery<User> typedQuery = entityManager.createQuery(query, User.class);
        return typedQuery.getResultList();
    }

    @Override
    public void merge(@NotNull final User user) {
        entityManager.merge(user);
    }

    @Override
    public void remove(@NotNull final User user) {
        entityManager.remove(user);
    }

    @Override
    public void removeAll(@NotNull final String userId) {
        throw new RepositoryException();
    }
}