package ru.khmelev.tm.repository;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.khmelev.tm.api.repository.IUserRepository;
import ru.khmelev.tm.entity.FieldConst;
import ru.khmelev.tm.entity.User;
import ru.khmelev.tm.exception.RepositoryException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public final class UserRepository extends IdentifiableRepository<User> implements IUserRepository {

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
    private User fetch(@Nullable final ResultSet row) {
        @NotNull final User user = new User();
        assert row != null;
        user.setId(row.getString(FieldConst.ID));
        user.setLogin(row.getString(FieldConst.LOGIN));
        user.setHashPassword(row.getString(FieldConst.HASH_PASSWORD));
        return user;
    }

    @Override
    public void persist(@NotNull final String id, @NotNull final User user) {
        connection = getConnection();

        @NotNull final String query = "INSERT INTO tm.user (" +
                "id, " +
                "login, " +
                "hashPassword, " +
                "role, " +
                "VALUES (?, ?, ?, ?, ?);";
        try {
            @NotNull final PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, id);
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getHashPassword());
            statement.setString(4, user.getRole().displayName());
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
    public User findOne(@NotNull final String id) {
        connection = getConnection();

        @Nullable ResultSet resultSet = null;

        @NotNull final String query = "SELECT * FROM tm.user WHERE id = ?;";
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
    public Collection<User> findAll() {
        connection = getConnection();
        @Nullable ResultSet resultSet = null;
        @NotNull final List<User> result = new ArrayList<>();

        @NotNull final String query = "SELECT * FROM tm.user;";
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
    public void merge(@NotNull final String id, @NotNull final User user) {
        connection = getConnection();

        @Nullable ResultSet resultSet = null;

        @NotNull final String query = "UPDATE tm.project SET " +
                "login, " +
                "hashPassword, " +
                "role, " +
                "WHERE id = ?;";
        try {
            @NotNull final PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getHashPassword());
            statement.setString(3, user.getRole().displayName());
            statement.setString(4, id);
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

        @NotNull final String query = "DELETE FROM tm.project WHERE id = ?;";
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

        @NotNull final String query = "DELETE FROM tm.user;";

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