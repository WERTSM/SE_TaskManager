package ru.khmelev.tm.api.repository;

import org.apache.deltaspike.data.api.Modifying;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.QueryParam;
import org.apache.deltaspike.data.api.Repository;
import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.entity.Session;

import java.util.Collection;

@Repository(forEntity = Session.class)
public interface ISessionRepository {

    void persist(@NotNull final Session session);

    @NotNull
    @Query(value = "SELECT session FROM Session session WHERE id = :id")
    Session findById(@NotNull @QueryParam("id") final String id);

    @NotNull
    @Query(value = "Select session from Session session")
    Collection<Session> findAll();

    void merge(@NotNull final Session session);

    void remove(@NotNull final Session session);

    @Modifying
    @Query(value = "DELETE FROM Session")
    void removeAll();
}