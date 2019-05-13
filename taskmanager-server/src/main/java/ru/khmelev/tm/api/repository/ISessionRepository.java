package ru.khmelev.tm.api.repository;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.khmelev.tm.entity.Session;

import javax.persistence.QueryHint;
import java.util.List;

@Repository
public interface ISessionRepository extends JpaRepository<Session, String> {

    @NotNull
    @QueryHints(@QueryHint(name = org.hibernate.jpa.QueryHints.HINT_CACHEABLE, value = "true"))
    @Query(value = "Select session from Session session where session.id = :id")
    Session findOne(@NotNull @Param("id") final String id);

    @NotNull
    @QueryHints(@QueryHint(name = org.hibernate.jpa.QueryHints.HINT_CACHEABLE, value = "true"))
    @Query(value = "Select session from Session session")
    List<Session> findAll();

    @Modifying
    @Query(value = "DELETE FROM Session")
    void removeAll();
}