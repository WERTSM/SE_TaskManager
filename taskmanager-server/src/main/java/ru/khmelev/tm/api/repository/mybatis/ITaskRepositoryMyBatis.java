package ru.khmelev.tm.api.repository.mybatis;

import org.apache.ibatis.annotations.*;
import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.entity.Task;

import java.util.Collection;

public interface ITaskRepositoryMyBatis {

    @Insert("INSERT INTO tm.task (" +

            "id, " +
            "name, " +
            "description, " +
            "dateStart, " +
            "dateFinish, " +
            "dateCreate ," +
            "status ," +
            "projectId," +
            "userId) " +

            "VALUES (" +

            "#{task.id}, " +
            "#{task.name}, " +
            "#{task.description}, " +
            "#{task.dateStart}, " +
            "#{task.dateFinish}, " +
            "#{task.dateCreate}, " +
            "#{task.status}, " +
            "#{task.projectId}, " +
            "#{task.userId});"
    )
    void persist(
            @Param("id") @NotNull final String id,
            @Param("task") @NotNull final Task task);

    @NotNull
    @Select("SELECT * FROM tm.task WHERE id = #{id} AND userId = #{userId}")
    Task findOne(
            @Param("id") @NotNull final String id,
            @Param("userId") @NotNull final String userId
    );


    @NotNull
    @Select("SELECT * FROM tm.task WHERE userId = #{userId}")
    Collection<Task> findAll(@Param("userId") @NotNull final String userId);

    @Update("UPDATE tm.task SET " +

            "name = #{project.name}, " +
            "description = #{project.description}, " +
            "dateStart = #{project.dateStart}, " +
            "dateFinish = #{project.dateFinish}, " +
            "dateCreate = #{project.dateCreate}, " +
            "status = #{project.status}, " +
            "projectId = #{project.projectId} " +

            "WHERE id = #{id} " +
            "AND userId = #{userId};")
    void merge(
            @Param("id") @NotNull final String id,
            @Param("project") @NotNull final Task task,
            @Param("userId") @NotNull final String userId
    );

    @Delete("DELETE FROM tm.task " +
            "WHERE id = #{id} " +
            "AND userId = #{userId};")
    void remove(
            @Param("id") @NotNull final String id,
            @Param("userId") @NotNull final String userId
    );

    @Delete("DELETE FROM tm.task WHERE userId = #{userId};")
    void removeAll(@Param("userId") @NotNull final String userId);

    @Delete("DELETE FROM tm.task WHERE projectId = #{projectId} AND userId = #{userId};")
    void removeAllTaskFromProject(
            @Param("projectId") @NotNull final String idProject,
            @Param("userId") @NotNull final String userId);

    @Select("SELECT * FROM tm.task WHERE projectId = #{projectId} AND userId = #{userId};")
    Collection<Task> listTaskFromProject(
            @Param("projectId") String idProject,
            @Param("userId") String userId);
}