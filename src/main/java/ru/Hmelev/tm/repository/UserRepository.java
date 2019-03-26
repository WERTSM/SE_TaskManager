package ru.Hmelev.tm.repository;

import org.jetbrains.annotations.NotNull;
import ru.Hmelev.tm.api.InterfaceUserRepository;
import ru.Hmelev.tm.entity.User;
import ru.Hmelev.tm.exception.RepositoryException;

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