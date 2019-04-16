package ru.khmelev.tm.api.repository.mybatis;

import org.apache.ibatis.annotations.*;
import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.entity.Project;

import java.util.Collection;

public interface IProjectRepositoryMyBatis {

    @Insert("INSERT INTO tm.project (" +
            "id, " +
            "name, " +
            "description, " +
            "dateStart, " +
            "dateFinish, " +
            "dateCreate ," +
            "status ," +
            "userId) " +
            "VALUES (#{project.id}, #{project.name}, #{project.description}, #{project.dateStart}, #{project.dateFinish}, #{project.dateCreate}, #{project.status}, #{project.userId});")
    void persist(
            @Param("id") @NotNull final String id,
            @Param("project") @NotNull final Project project
    );

    @NotNull
    @Select("SELECT * FROM tm.project WHERE id = #{id} AND userId = #{userId}")
    Project findOne(
            @Param("id") @NotNull final String id,
            @Param("userId") @NotNull final String userId
    );

    @NotNull
    @Select("SELECT * FROM tm.project WHERE userId = #{userId}")
    Collection<Project> findAll(@Param("userId") @NotNull final String userId);

    @Update("UPDATE tm.project SET " +
            "name = #{project.name}, " +
            "description = #{project.description}, " +
            "dateStart = #{project.dateStart}, " +
            "dateFinish = #{project.dateFinish}, " +
            "dateCreate = #{project.dateCreate}, " +
            "status = #{project.status} " +
            "WHERE id = #{id} " +
            "AND userId = #{userId};")
    void merge(
            @Param("id") @NotNull final String id,
            @Param("project") @NotNull final Project project,
            @Param("userId") @NotNull final String userId
    );

    @Delete("DELETE FROM tm.project " +
            "WHERE id = #{id} " +
            "AND userId = #{userId};")
    void remove(
            @Param("id") @NotNull final String id,
            @Param("userId") @NotNull final String userId
    );

    @Delete("DELETE FROM tm.project WHERE userId = #{userId};")
    void removeAll(@Param("userId") @NotNull final String userId);
}