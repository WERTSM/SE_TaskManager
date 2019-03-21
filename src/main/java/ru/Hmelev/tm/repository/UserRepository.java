package ru.Hmelev.tm.repository;

import ru.Hmelev.tm.entity.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class UserRepository {
    private final Map<String, User> mapUsers = new HashMap<>();

    public Collection<User> findAll() {
        return mapUsers.values();
    }

    // public User findOne(String id) {
    //   return this.mapProject.get(id);
    //}

    public void persist(User user) {
        if (user != null) {
            mapUsers.put(user.getId(), user);
        }
    }
//
//    public void merge() {
//    }
//
//    public void removeAll() {
//        mapProject.clear();
//    }
//
//    public void remove(String id) {
//        mapProject.remove(id);
}

