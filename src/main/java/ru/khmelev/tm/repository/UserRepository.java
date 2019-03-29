package ru.khmelev.tm.repository;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.api.IUserRepository;
import ru.khmelev.tm.entity.User;
import ru.khmelev.tm.exception.RepositoryException;

import java.util.Collection;

public final class UserRepository extends EntityRepository<User> implements IUserRepository {

    @NotNull
    @Override
    public User findOne(@NotNull final String id) {
        if (!id.isEmpty()) {
            return this.mapEntity.get(id);
        }
        throw new RepositoryException();
    }

    @NotNull
    public Collection<User> findAll() {
        return mapEntity.values();
    }

    //In future...
    @NotNull
    @Override
    public User findOne(@NotNull String id, @NotNull String userId) {
        throw new RepositoryException();
    }

    @Override
    public @NotNull Collection<User> findAll(@NotNull String userId) {
        throw new RepositoryException();
    }

    @Override
    public void merge(@NotNull String id, @NotNull User entity, @NotNull String userId) {
    }

    @Override
    public void remove(@NotNull String id, @NotNull String userId) {
    }

    @Override
    public void removeAll(@NotNull String userId) {
    }
}