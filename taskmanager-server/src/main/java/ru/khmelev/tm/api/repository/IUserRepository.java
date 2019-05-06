package ru.khmelev.tm.api.repository;

import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.QueryParam;
import org.apache.deltaspike.data.api.Repository;
import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.entity.User;

import java.util.Collection;

@Repository(forEntity = User.class)
public interface IUserRepository {

    void persist(@NotNull User user);

    @Query(value = "SELECT user FROM User user WHERE id = :id")
    @NotNull User findById(@NotNull @QueryParam("id") final String id);

    @NotNull
    @Query(value = "Select user from User user")
    Collection<User> findAll();

    void merge(@NotNull User user);

    void remove(@NotNull User user);
}
