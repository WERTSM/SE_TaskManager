package ru.khmelev.tm.repository;

import lombok.Setter;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.khmelev.tm.api.repository.IUserRepository;
import ru.khmelev.tm.entity.FieldConst;
import ru.khmelev.tm.entity.Role;
import ru.khmelev.tm.entity.User;
import ru.khmelev.tm.exception.RepositoryException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public final class UserRepository implements IUserRepository {

    @Nullable
    @Setter
    private Connection connection;

    @NotNull
    @SneakyThrows
    private User fetch(@Nullable final ResultSet row) {
        @NotNull final User user = new User();
        assert row != null;
        user.setId(row.getString(FieldConst.ID));
        user.setLogin(row.getString(FieldConst.LOGIN));
        user.setHashPassword(row.getString(FieldConst.HASH_PASSWORD));
        user.setRole(Role.valueOf(row.getString(FieldConst.ROLE)));
        return user;
    }

    @Override
    public void persist(@NotNull final String id, @NotNull final User user) throws SQLException {
        @NotNull final String query = "INSERT INTO tm.user (" +
                "id, " +
                "login, " +
                "hashPassword, " +
                "role) " +
                "VALUES (?, ?, ?, ?);";

        assert connection != null;
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, id);
        statement.setString(2, user.getLogin());
        statement.setString(3, user.getHashPassword());
        statement.setString(4, user.getRole().displayName());
        statement.executeUpdate();
    }

    @Override
    @NotNull
    public User findOne(@NotNull final String id) throws SQLException {
        @Nullable ResultSet resultSet = null;

        @NotNull final String query = "SELECT * FROM tm.user WHERE id = ?;";

        assert connection != null;
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, id);
        resultSet = statement.executeQuery();

        if (resultSet.next()) {
            User user = fetch(resultSet);
            resultSet.close();
            return user;
        }
        resultSet.close();
        throw new RepositoryException();
    }

    @Override
    @NotNull
    @SneakyThrows
    public Collection<User> findAll() {
        @Nullable ResultSet resultSet = null;
        @NotNull final List<User> result = new ArrayList<>();

        @NotNull final String query = "SELECT * FROM tm.user;";

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
    public void merge(@NotNull final String id, @NotNull final User user) throws SQLException {
        @NotNull final String query = "UPDATE tm.user SET " +
                "login = ?, " +
                "hashPassword = ?, " +
                "role = ? " +
                "WHERE id = ?;";

        assert connection != null;
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, user.getLogin());
        statement.setString(2, user.getHashPassword());
        statement.setString(3, user.getRole().displayName());
        statement.setString(4, id);
        statement.executeUpdate();
    }

    @Override
    public void remove(@NotNull final String id) throws SQLException {
        @NotNull final String query = "DELETE FROM tm.user WHERE id = ?;";

        assert connection != null;
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, id);
        statement.executeUpdate();
    }

    @Override
    public void removeAll() {
        throw new RepositoryException();
    }
}