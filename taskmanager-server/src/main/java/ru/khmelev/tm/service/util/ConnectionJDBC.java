package ru.khmelev.tm.service.util;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.khmelev.tm.exception.RepositoryException;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionJDBC {

    @NotNull
    private static final String URL;

    @NotNull
    private static final String USER;

    @NotNull
    private static final String PASSWORD;

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RepositoryException();
        }

        @NotNull final ClassLoader loader = Thread.currentThread().getContextClassLoader();

        @NotNull final Properties property = new Properties();
        try {
            final InputStream inputStream = loader.getResourceAsStream("database/database.properties");
            property.load(inputStream);
        } catch (IOException e) {
            throw new RepositoryException();
        }
        URL = property.getProperty("database.url");
        USER = property.getProperty("database.user");
        PASSWORD = property.getProperty("database.password");
    }

    @NotNull
    public static Connection getConnection() {
        @Nullable Connection connection;

        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RepositoryException();
        }
        return connection;
    }
}