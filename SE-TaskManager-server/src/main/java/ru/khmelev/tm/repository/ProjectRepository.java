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

    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tm", "root", "root");


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
    @NotNull
    public Project findOne(@NotNull final String id, @NotNull final String userId) {
        @Nullable ResultSet resultSet = null;
        @Nullable Project project;

        @NotNull final String query = "" +
                "SELECT * FROM tm.project " +
                "WHERE id = ? " +
                "AND user_id = ?;";
        try {
            @NotNull final PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, id);
            statement.setString(2, userId);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return fetch(resultSet);
            }
        } catch (SQLException e) {
            throw new RepositoryException();
        } finally {
            try {
                if (resultSet != null) {
                    connection.close();
                    resultSet.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throw new RepositoryException();
        }
    }

    @Override
    @NotNull
    @SneakyThrows
    public Collection<Project> findAll(@NotNull final String userId) {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tm", "root", "root");
        @NotNull final String query = "SELECT * FROM tm.project WHERE userId = ?";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, userId);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        @NotNull final List<Project> result = new ArrayList<>();
        while (resultSet.next()) result.add(fetch(resultSet));
        statement.close();
        return result;
    }
}