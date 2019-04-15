package ru.khmelev.tm.repository;

import lombok.Setter;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.khmelev.tm.api.repository.IProjectRepository;
import ru.khmelev.tm.entity.FieldConst;
import ru.khmelev.tm.entity.Project;
import ru.khmelev.tm.entity.Status;
import ru.khmelev.tm.exception.RepositoryException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ProjectRepository implements IProjectRepository {

    @Nullable
    @Setter
    private Connection connection;

    @NotNull
    @SneakyThrows
    private Project fetch(@Nullable final ResultSet row) {
        @NotNull final Project project = new Project();
        assert row != null;
        project.setId(row.getString(FieldConst.ID));
        project.setName(row.getString(FieldConst.NAME));
        project.setDescription(row.getString(FieldConst.DESCRIPTION));
        project.setDateStart(row.getDate(FieldConst.DATE_START));
        project.setDateFinish(row.getDate(FieldConst.DATE_FINISH));
        project.setDateCreate(row.getDate(FieldConst.DATE_CREATE));
        project.setStatus(Status.valueOf(row.getString(FieldConst.STATUS)));
        project.setUserId(row.getString(FieldConst.USER_ID));
        return project;
    }

    @Override
    public void persist(@NotNull final String id, @NotNull final Project project) throws SQLException {
        @NotNull final String query = "INSERT INTO tm.project (" +
                "id, " +
                "name, " +
                "description, " +
                "dateStart, " +
                "dateFinish, " +
                "dateCreate ," +
                "status ," +
                "userId) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

        assert connection != null;
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, id);
        statement.setString(2, project.getName());
        statement.setString(3, project.getDescription());
        statement.setDate(4, new Date(project.getDateStart().getTime()));
        statement.setDate(5, new Date(project.getDateFinish().getTime()));
        statement.setDate(6, new Date(project.getDateCreate().getTime()));
        statement.setString(7, project.getStatus().getDisplayName());
        statement.setString(8, project.getUserId());
        statement.executeUpdate();
    }

    @Override
    @NotNull
    public Project findOne(@NotNull final String id, @NotNull final String userId) throws SQLException {
        @Nullable ResultSet resultSet = null;

        @NotNull final String query = "SELECT * FROM tm.project " +
                "WHERE id = ? " +
                "AND userId = ?;";

        assert connection != null;
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, id);
        statement.setString(2, userId);
        resultSet = statement.executeQuery();
        if (resultSet.next()) {
            Project project = fetch(resultSet);
            resultSet.close();
            return project;
        }
        resultSet.close();
        throw new RepositoryException();
    }

    @Override
    @NotNull
    @SneakyThrows
    public Collection<Project> findAll(@NotNull final String userId) {
        @Nullable ResultSet resultSet = null;
        @NotNull final List<Project> result = new ArrayList<>();

        @NotNull final String query = "SELECT * FROM tm.project WHERE userId = ?";

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
    public void merge(@NotNull final String id, @NotNull final Project project, @NotNull final String userId) throws SQLException {
        @Nullable ResultSet resultSet = null;

        @NotNull final String query = "UPDATE tm.project SET " +
                "name = ?, " +
                "description = ?, " +
                "dateStart = ?, " +
                "dateFinish = ?, " +
                "dateCreate = ?, " +
                "status = ? " +
                "WHERE id = ? " +
                "AND userId = ?;";

        assert connection != null;
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, project.getName());
        statement.setString(2, project.getDescription());
        statement.setDate(3, new Date(project.getDateStart().getTime()));
        statement.setDate(4, new Date(project.getDateFinish().getTime()));
        statement.setDate(5, new Date(project.getDateCreate().getTime()));
        statement.setString(6, project.getStatus().getDisplayName());
        statement.setString(7, id);
        statement.setString(8, userId);
        statement.executeUpdate();
    }


    @Override
    public void remove(@NotNull final String id, @NotNull final String userId) throws SQLException {
        @NotNull final String query = "DELETE FROM tm.project " +
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
        @NotNull final String query = "DELETE FROM tm.project WHERE userId = ?;";

        assert connection != null;
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, userId);
        statement.executeUpdate();
    }
}