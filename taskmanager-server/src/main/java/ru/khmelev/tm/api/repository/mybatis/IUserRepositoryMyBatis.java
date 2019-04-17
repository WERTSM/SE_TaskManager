package ru.khmelev.tm.api.repository.mybatis;

import org.apache.ibatis.annotations.*;
import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.entity.User;

import java.util.Collection;

public interface IUserRepositoryMyBatis {

    @Insert("INSERT INTO tm.user (" +
            "id, " +
            "login, " +
            "hashPassword, " +
            "role) " +
            "VALUES (#{id}, #{user.login}, #{user.hashPassword}, #{user.role});")
    void persist(
            @Param("id") @NotNull String id,
            @Param("user") @NotNull final User user
    );

    @Select("SELECT * FROM tm.user WHERE id = #{id};")
    @NotNull User findOne(@Param("id") @NotNull final String id);

    @Select("SELECT * FROM tm.user;")
    @NotNull Collection<User> findAll();

    @Update("UPDATE tm.user SET " +
            "login = #{user.login}, " +
            "hashPassword = #{user.hashPassword}, " +
            "role = #{user.role} " +
            "WHERE id = #{id};")
    void merge(
            @Param("id") @NotNull final String id,
            @Param("user") @NotNull final User user
    );

    @Delete("DELETE FROM tm.user WHERE id = #{id};")
    void remove(@NotNull final String id);
}