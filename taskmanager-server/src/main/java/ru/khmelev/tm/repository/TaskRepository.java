package ru.khmelev.tm.repository;

import lombok.Setter;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.khmelev.tm.api.repository.ITaskRepository;
import ru.khmelev.tm.entity.FieldConst;
import ru.khmelev.tm.entity.Status;
import ru.khmelev.tm.entity.Task;
import ru.khmelev.tm.exception.RepositoryException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TaskRepository implements ITaskRepository {

    @Nullable
    @Setter
    private Connection connection;

    @NotNull
    @SneakyThrows
    private Task fetch(@Nullable final ResultSet row) {
        @NotNull final Task task = new Task();
        assert row != null;
        task.setId(row.getString(FieldConst.ID));
        task.setName(row.getString(FieldConst.NAME));
        task.setDescription(row.getString(FieldConst.DESCRIPTION));
        task.setDateStart(row.getDate(FieldConst.DATE_START));
        task.setDateFinish(row.getDate(FieldConst.DATE_FINISH));
        task.setDateCreate(row.getDate(FieldConst.DATE_CREATE));
        task.setStatus(Status.valueOf(row.getString(FieldConst.STATUS)));
        task.setProjectId(row.getString(FieldConst.PROJECT_ID));
        task.setUserId(row.getString(FieldConst.USER_ID));
        return task;
    }

    @Override
    public void persist(@NotNull final String id, @NotNull final Task task) throws SQLException {
        @NotNull final String query = "INSERT INTO tm.task (" +
                "id, " +
                "name, " +
                "description, " +
                "dateStart, " +
                "dateFinish, " +
                "dateCreate ," +
                "status ," +
                "projectId," +
                "userId) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";

        assert connection != null;
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, id);
        statement.setString(2, task.getName());
        statement.setString(3, task.getDescription());
        statement.setDate(4, new Date(task.getDateStart().getTime()));
        statement.setDate(5, new Date(task.getDateFinish().getTime()));
        statement.setDate(6, new Date(task.getDateCreate().getTime()));
        statement.setString(7, task.getStatus().getDisplayName());
        statement.setString(8, task.getProjectId());
        statement.setString(9, task.getUserId());
        statement.executeUpdate();
    }

    @Override
    @NotNull
    public Task findOne(@NotNull final String id, @NotNull final String userId) throws SQLException {
        @Nullable ResultSet resultSet = null;

        @NotNull final String query = "SELECT * FROM tm.task " +
                "WHERE id = ? " +
                "AND userId = ?;";

        assert connection != null;
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, id);
        statement.setString(2, userId);
        resultSet = statement.executeQuery();
        if (resultSet.next()) {
            Task task = fetch(resultSet);
            resultSet.close();
            return task;
        }
        resultSet.close();
        throw new RepositoryException();
    }

    @Override
    @NotNull
    @SneakyThrows
    public Collection<Task> findAll(@NotNull final String userId) {
        @Nullable ResultSet resultSet = null;
        @NotNull final List<Task> result = new ArrayList<>();

        @NotNull final String query = "SELECT * FROM tm.task WHERE userId = ?";

        assert connection != null;
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, userId);
        resultSet = statement.executeQuery();
        while (resultSet.next()) {
            result.add(fetch(resultSet));
        }
        resultSet.close();
        return result;
    }

    @Override
    public void merge(@NotNull final String id, @NotNull final Task task, @NotNull final String userId) throws SQLException {
        @NotNull final String query = "UPDATE tm.task SET " +
                "name = ?, " +
                "description = ?, " +
                "dateStart = ?, " +
                "dateFinish = ?, " +
                "dateCreate = ?, " +
                "status = ?, " +
                "projectId = ? " +
                "WHERE id = ? " +
                "AND userId = ?;";

        assert connection != null;
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, task.getName());
        statement.setString(2, task.getDescription());
        statement.setDate(3, new Date(task.getDateStart().getTime()));
        statement.setDate(4, new Date(task.getDateFinish().getTime()));
        statement.setDate(5, new Date(task.getDateCreate().getTime()));
        statement.setString(6, task.getStatus().getDisplayName());
        statement.setString(7, task.getProjectId());
        statement.setString(8, id);
        statement.setString(9, userId);
        statement.executeUpdate();
    }

    @Override
    public void remove(@NotNull final String id, @NotNull final String userId) throws SQLException {
        @NotNull final String query = "DELETE FROM tm.task " +
                "WHERE id = ? " +
                "AND userId = ?;";

        assert connection != null;
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, id);
        statement.setString(2, userId);
        statement.executeUpdate();
    }

    @Override
    public void removeAll(@NotNull final String userId) throws SQLException {
        @NotNull final String query = "DELETE FROM tm.task WHERE userId = ?;";

        assert connection != null;
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, userId);
        statement.executeUpdate();
    }

    @Override
    public void removeAllTaskFromProject(@NotNull String idProject, @NotNull String userId) throws SQLException {
        @NotNull final String query = "DELETE FROM tm.task WHERE projectId = ? AND userId = ?;";

        assert connection != null;
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, idProject);
        statement.setString(2, userId);
        statement.executeUpdate();
    }

    @NotNull
    @Override
    @SneakyThrows
    public Collection<Task> listTaskFromProject(@NotNull String idProject, @NotNull String userId) {
        @Nullable ResultSet resultSet = null;
        @NotNull final List<Task> result = new ArrayList<>();

        @NotNull final String query = "SELECT * FROM tm.task WHERE projectId = ?";

        assert connection != null;
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, idProject);
        resultSet = statement.executeQuery();
        while (resultSet.next()) {
            result.add(fetch(resultSet));
        }
        resultSet.close();
        connection.close();
        return result;
    }
}