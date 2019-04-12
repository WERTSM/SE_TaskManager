package ru.khmelev.tm.repository;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.khmelev.tm.api.repository.IProjectRepository;
import ru.khmelev.tm.entity.FieldConst;
import ru.khmelev.tm.entity.Project;
import ru.khmelev.tm.exception.RepositoryException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ProjectRepository extends EntityRepository<Project> implements IProjectRepository {

    private Connection connection;

    private Connection getConnection() {
        {
            try {
                return DriverManager.getConnection("jdbc:mysql://localhost:3306/tm", "root", "root");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        throw new RepositoryException();
    }

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
        return project;
    }

    @Override
    public void persist(@NotNull final String id, @NotNull final Project project) {
        connection = getConnection();

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
        try {
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
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    @NotNull
    public Project findOne(@NotNull final String id, @NotNull final String userId) {
        connection = getConnection();

        @Nullable ResultSet resultSet = null;

        @NotNull final String query = "SELECT * FROM tm.project " +
                "WHERE id = ? " +
                "AND userId = ?;";
        try {
            @NotNull final PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, id);
            statement.setString(2, userId);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return fetch(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RepositoryException();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        throw new RepositoryException();
    }

    @Override
    @NotNull
    @SneakyThrows
    public Collection<Project> findAll(@NotNull final String userId) {
        connection = getConnection();
        @Nullable ResultSet resultSet = null;
        @NotNull final List<Project> result = new ArrayList<>();

        @NotNull final String query = "SELECT * FROM tm.project WHERE userId = ?";
        try {
            @NotNull final PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, userId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result.add(fetch(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RepositoryException();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public void merge(@NotNull final String id, @NotNull final Project project, @NotNull final String userId) {
        connection = getConnection();

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
        try {
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
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RepositoryException();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void remove(@NotNull final String id, @NotNull final String userId) {
        connection = getConnection();

        @NotNull final String query = "DELETE FROM tm.project " +
                "WHERE id = ? " +
                "AND userId = ?;";
        try {
            @NotNull final PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, id);
            statement.setString(2, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RepositoryException();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void removeAll(@NotNull final String userId) {
        connection = getConnection();

        @NotNull final String query = "DELETE FROM tm.project WHERE userId = ?;";

        try {
            @NotNull final PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RepositoryException();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}