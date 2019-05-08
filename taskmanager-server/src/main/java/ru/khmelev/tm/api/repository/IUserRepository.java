package ru.khmelev.tm.api.repository;

import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.QueryParam;
import org.apache.deltaspike.data.api.Repository;
import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.entity.User;

import javax.persistence.QueryHint;
import java.util.Collection;

@Repository(forEntity = User.class)
public interface IUserRepository {

    void persist(@NotNull User user);

    @Query(value = "SELECT user FROM User user WHERE id = :id", hints = {@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    @NotNull User findById(@NotNull @QueryParam("id") final String id);

    @NotNull
    @Query(value = "Select user from User user", hints = {@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    Collection<User> findAll();

    void merge(@NotNull User user);

    void remove(@NotNull User user);
}
