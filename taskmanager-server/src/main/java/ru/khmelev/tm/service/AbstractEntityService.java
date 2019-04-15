package ru.khmelev.tm.service;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.api.repository.IEntityRepository;
import ru.khmelev.tm.entity.Entity;
import ru.khmelev.tm.exception.ServiceException;
import ru.khmelev.tm.service.util.ConnectionJDBC;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public abstract class AbstractEntityService<T extends Entity> {

    @NotNull
    private final IEntityRepository<T> entityRepository;

    AbstractEntityService(@NotNull final IEntityRepository<T> entityRepository) {
        this.entityRepository = entityRepository;
    }

    public void createEntity(@NotNull final String id, @NotNull final T entity) {
        try (Connection connection = ConnectionJDBC.getConnection()) {
            entityRepository.setConnection(connection);
            entityRepository.persist(id, entity);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    @NotNull
    public T findEntity(@NotNull final String id, @NotNull final String userId) {
        if (!id.isEmpty() && !userId.isEmpty()) {
            try (Connection connection = ConnectionJDBC.getConnection()) {
                entityRepository.setConnection(connection);
                return entityRepository.findOne(id, userId);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        throw new ServiceException();
    }

    @NotNull
    public Collection<T> findAll(@NotNull final String userId) {
        try (Connection connection = ConnectionJDBC.getConnection()) {
            entityRepository.setConnection(connection);
            return entityRepository.findAll(userId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new ServiceException();
    }

    @NotNull
    public Collection<T> findAllName(@NotNull final String findParameter, @NotNull final String userId) {
        try (Connection connection = ConnectionJDBC.getConnection()) {
            entityRepository.setConnection(connection);
            @NotNull final List<T> list = new ArrayList<>(entityRepository.findAll(userId));
            final Iterator<T> iterator = list.iterator();
            while (iterator.hasNext()) {
                if (!iterator.next().getName().contains(findParameter)) {
                    iterator.remove();
                }
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new ServiceException();
    }

    @NotNull
    public Collection<T> findAllDescription(@NotNull final String findParameter, @NotNull final String userId) {
        try (Connection connection = ConnectionJDBC.getConnection()) {
            entityRepository.setConnection(connection);
            @NotNull final List<T> list = new ArrayList<>(entityRepository.findAll(userId));
            final Iterator<T> iterator = list.iterator();
            while (iterator.hasNext()) {
                if (!iterator.next().getDescription().contains(findParameter)) {
                    iterator.remove();
                }
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new ServiceException();
    }

    public void editEntity(@NotNull final String id, @NotNull T entity, @NotNull final String userId) {
        if (!id.isEmpty() && !userId.isEmpty()) {
            try (Connection connection = ConnectionJDBC.getConnection()) {
                entityRepository.setConnection(connection);
                entityRepository.merge(id, entity, userId);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void removeEntity(@NotNull final String id, @NotNull final String userId) {
        if (!id.isEmpty() && !userId.isEmpty()) {
            try (Connection connection = ConnectionJDBC.getConnection()) {
                entityRepository.setConnection(connection);
                entityRepository.remove(id, userId);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void clearEntity(@NotNull final String userId) {
        if (!userId.isEmpty()) {
            try (Connection connection = ConnectionJDBC.getConnection()) {
                entityRepository.setConnection(connection);
                entityRepository.removeAll(userId);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}