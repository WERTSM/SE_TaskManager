package ru.khmelev.tm.api.repository;

import org.apache.deltaspike.data.api.Repository;
import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.entity.User;

import java.util.Collection;

@Repository(forEntity = User.class)
public interface IUserRepository {
    void persist(@NotNull User user);

    @NotNull User findOne(@NotNull String id);

    @NotNull Collection<User> findAll();

    void merge(@NotNull User user);

    void remove(@NotNull User user);

    void removeAll(@NotNull String userId);
}
