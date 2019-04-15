package ru.khmelev.tm.service;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.api.repository.IRepository;
import ru.khmelev.tm.entity.Identifiable;
import ru.khmelev.tm.exception.ServiceException;
import ru.khmelev.tm.service.util.ConnectionJDBC;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;

public abstract class AbstractIdentifiableService<T extends Identifiable> {

    @NotNull
    private final IRepository<T> repository;

    AbstractIdentifiableService(@NotNull final IRepository<T> repository) {
        this.repository = repository;
    }

    public void createEntity(@NotNull final String id, @NotNull final T entity) {
        try (Connection connection = ConnectionJDBC.getConnection()) {
            repository.setConnection(connection);
            repository.persist(id, entity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @NotNull
    public T findEntity(@NotNull final String id) throws ServiceException {
        if (!id.isEmpty()) {
            try (Connection connection = ConnectionJDBC.getConnection()) {
                repository.setConnection(connection);
                return repository.findOne(id);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        throw new ServiceException();
    }

    @NotNull
    public Collection<T> findAll() {
        try (Connection connection = ConnectionJDBC.getConnection()) {
            repository.setConnection(connection);
            return repository.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new ServiceException();
    }

    public void editEntity(@NotNull final String id, @NotNull final T entity) {
        if (!id.isEmpty()) {
            try (Connection connection = ConnectionJDBC.getConnection()) {
                repository.setConnection(connection);
                repository.merge(id, entity);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void removeEntity(@NotNull final String id) {
        if (!id.isEmpty()) {
            try (Connection connection = ConnectionJDBC.getConnection()) {
                repository.setConnection(connection);
                repository.remove(id);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void clearEntity() {
        try (Connection connection = ConnectionJDBC.getConnection()) {
            repository.setConnection(connection);
            repository.removeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}