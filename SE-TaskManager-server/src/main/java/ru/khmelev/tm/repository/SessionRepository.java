package ru.khmelev.tm.repository;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.khmelev.tm.api.repository.ISessionRepository;
import ru.khmelev.tm.entity.FieldConst;
import ru.khmelev.tm.entity.Session;
import ru.khmelev.tm.exception.RepositoryException;
import ru.khmelev.tm.service.util.ConnectionJDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public final class SessionRepository implements ISessionRepository {

    private Connection connection;

    private Connection getConnection() {
        return ConnectionJDBC.getConnection();
    }

    @NotNull
    @SneakyThrows
    private Session fetch(@Nullable final ResultSet row) {
        @NotNull final Session session = new Session();
        assert row != null;
        session.setId(row.getString(FieldConst.ID));
        session.setUserId(row.getString(FieldConst.ID));
        session.setSignature(row.getString(FieldConst.SIGNATURE));
        return session;
    }

    @Override
    public void persist(@NotNull final String id, @NotNull final Session session) {
        connection = getConnection();

        @NotNull final String query = "INSERT INTO tm.session (" +
                "id, " +
                "signature, " +
                "dateCreate, " +
                "userId)" +
                "VALUES (?, ?, ?, ?);";
        try {
            @NotNull final PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, id);
            statement.setString(2, session.getSignature());
            statement.setDate(3, new Date(session.getCreateDate().getTime()));
            statement.setString(4, session.getUserId());
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
    public Session findOne(@NotNull final String id) {
        connection = getConnection();

        @Nullable ResultSet resultSet = null;

        @NotNull final String query = "SELECT * FROM tm.session WHERE id = ?;";
        try {
            @NotNull final PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, id);
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
    public Collection<Session> findAll() {
        connection = getConnection();
        @Nullable ResultSet resultSet = null;
        @NotNull final List<Session> result = new ArrayList<>();

        @NotNull final String query = "SELECT * FROM tm.session;";
        try {
            @NotNull final PreparedStatement statement = connection.prepareStatement(query);
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
    public void merge(@NotNull final String id, @NotNull final Session session) {
        connection = getConnection();

        @NotNull final String query = "UPDATE tm.session SET " +
                "signature = ?, " +
                "userId = ? " +
                "WHERE id = ?;";
        try {
            @NotNull final PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, session.getSignature());
            statement.setString(2, session.getUserId());
            statement.setString(3, id);
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
    public void remove(@NotNull final String id) {
        connection = getConnection();

        @NotNull final String query = "DELETE FROM tm.session WHERE id = ?;";
        try {
            @NotNull final PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, id);
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
    public void removeAll() {
        connection = getConnection();

        @NotNull final String query = "DELETE FROM tm.session;";

        try {
            @NotNull final PreparedStatement statement = connection.prepareStatement(query);
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