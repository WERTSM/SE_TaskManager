package ru.khmelev.tm.api.repository.mybatis;

import org.apache.ibatis.annotations.*;
import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.entity.Session;

import java.util.Collection;

public interface ISessionRepositoryMyBatis {

    @Insert("INSERT INTO tm.session (" +
            "id, " +
            "signature, " +
            "dateCreate, " +
            "userId)" +
            "VALUES (#{id}, #{session.signature}, #{session.dateCreate}, #{session.userId});")
    void persist(
            @Param("id") @NotNull String id,
            @Param("session") @NotNull final Session session
    );

    @Select("SELECT * FROM tm.session WHERE id = #{id};")
    @NotNull Session findOne(@NotNull final String id);

    @Select("SELECT * FROM tm.session;")
    @NotNull Collection<Session> findAll();

    @Update("UPDATE tm.session SET " +
            "signature = #{session.signature}, " +
            "userId = #{session.userId} " +
            "WHERE id = #{id};")
    void merge(
            @Param("id") @NotNull final String id,
            @Param("session") @NotNull final Session session
    );

    @Delete("DELETE FROM tm.session WHERE id = #{id};")
    void remove(@NotNull final String id);

    @Delete("DELETE FROM tm.session;")
    void removeAll();
}