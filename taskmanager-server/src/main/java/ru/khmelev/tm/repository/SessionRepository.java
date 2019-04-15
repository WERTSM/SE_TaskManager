package ru.khmelev.tm.repository;

import lombok.Setter;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.khmelev.tm.api.repository.ISessionRepository;
import ru.khmelev.tm.entity.FieldConst;
import ru.khmelev.tm.entity.Session;
import ru.khmelev.tm.exception.RepositoryException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public final class SessionRepository implements ISessionRepository {

    @Nullable
    @Setter
    private Connection connection;

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
    public void persist(@NotNull final String id, @NotNull final Session session) throws SQLException {
        @NotNull final String query = "INSERT INTO tm.session (" +
                "id, " +
                "signature, " +
                "dateCreate, " +
                "userId)" +
                "VALUES (?, ?, ?, ?);";

        assert connection != null;
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, id);
        statement.setString(2, session.getSignature());
        statement.setDate(3, new Date(session.getCreateDate().getTime()));
        statement.setString(4, session.getUserId());
        statement.executeUpdate();
    }

    @Override
    @NotNull
    public Session findOne(@NotNull final String id) throws SQLException {
        @Nullable ResultSet resultSet = null;

        @NotNull final String query = "SELECT * FROM tm.session WHERE id = ?;";

        assert connection != null;
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, id);
        resultSet = statement.executeQuery();
        if (resultSet.next()) {
            Session session = fetch(resultSet);
            resultSet.close();
            return session;
        }
        resultSet.close();
        throw new RepositoryException();
    }

    @Override
    @NotNull
    @SneakyThrows
    public Collection<Session> findAll() {
        @Nullable ResultSet resultSet = null;
        @NotNull final List<Session> result = new ArrayList<>();

        @NotNull final String query = "SELECT * FROM tm.session;";

        assert connection != null;
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        resultSet = statement.executeQuery();
        while (resultSet.next()) {
            result.add(fetch(resultSet));
        }
        resultSet.close();
        return result;
    }

    @Override
    public void merge(@NotNull final String id, @NotNull final Session session) throws SQLException {

        @NotNull final String query = "UPDATE tm.session SET " +
                "signature = ?, " +
                "userId = ? " +
                "WHERE id = ?;";

        assert connection != null;
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, session.getSignature());
        statement.setString(2, session.getUserId());
        statement.setString(3, id);
        statement.executeUpdate();
    }

    @Override
    public void remove(@NotNull final String id) throws SQLException {

        @NotNull final String query = "DELETE FROM tm.session WHERE id = ?;";

        assert connection != null;
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, id);
        statement.executeUpdate();
    }

    @Override
    public void removeAll() throws SQLException {
        @NotNull final String query = "DELETE FROM tm.session;";

        assert connection != null;
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.executeUpdate();
    }
}