package ru.khmelev.tm.api;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.entity.Identifiable;

import javax.sql.rowset.serial.SerialException;
import java.util.Collection;

public interface IService<T extends Identifiable> extends ISerializationService<T> {

    void createEntity(@NotNull final String id, @NotNull final T entity);

    @NotNull T findEntity(@NotNull final String id) throws SerialException;

    @NotNull Collection<T> findAll();

    void editEntity(@NotNull final String id, @NotNull final T entity);

    void removeEntity(@NotNull final String id);

    void clearEntity();
}