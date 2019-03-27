package ru.khmelev.tm.repository;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.api.InterfaceUserRepository;
import ru.khmelev.tm.entity.User;
import ru.khmelev.tm.exception.RepositoryException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public final class UserRepository implements InterfaceUserRepository {
    private final Map<String, User> mapUsers = new HashMap<>();

    @Override
    public void persist(@NotNull final User user) {
        mapUsers.put(user.getId(), user);
    }

    @Override
    public User findOne(@NotNull final String id) {
        if (!id.isEmpty()) {
            return this.mapUsers.get(id);
        }
        throw new RepositoryException();
    }

    @NotNull
    public Collection<User> findAll() {
        return mapUsers.values();
    }
}