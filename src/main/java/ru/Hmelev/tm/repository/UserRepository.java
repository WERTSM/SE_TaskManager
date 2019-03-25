package ru.Hmelev.tm.repository;

import ru.Hmelev.tm.api.IUserRepository;
import ru.Hmelev.tm.entity.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public final class UserRepository implements IUserRepository {
    private final Map<String, User> mapUsers = new HashMap<>();

    @Override
    public void persist(User user) {
        if (user != null) {
            mapUsers.put(user.getId(), user);
        }
    }

    @Override
    public User findOne(String id) {
        if (id != null && !id.isEmpty()) {
            return this.mapUsers.get(id);
        }
        return null;
    }

    public Collection<User> findAll() {
        return mapUsers.values();
    }
}