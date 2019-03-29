package ru.khmelev.tm.api;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.entity.Entity;

import java.util.Collection;

public interface IEntityFindNameOrDescService<T extends Entity> extends IEntityService<T> {

    @NotNull Collection<T> findAllName(String findParameter, String userId);

    @NotNull Collection<T> findAllDescription(String findParameter, String userId);
}