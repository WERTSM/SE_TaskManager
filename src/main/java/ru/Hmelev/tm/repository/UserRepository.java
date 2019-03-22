package ru.Hmelev.tm.repository;

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
        return this.mapUsers.get(id);
    }

    public Collection<User> findAll() {
        return mapUsers.values();
    }


}
