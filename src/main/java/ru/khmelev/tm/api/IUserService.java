package ru.khmelev.tm.api;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.entity.User;

import java.util.Collection;

public interface IUserService extends IEntityService<User> {

    boolean userLogin(@NotNull final String login, @NotNull final String pass);

    @NotNull User findEntity(@NotNull final String id);

    @NotNull Collection<User> findAll();

    void userSetPassword(@NotNull final String login, @NotNull final String pass);

    @NotNull String getId(@NotNull final User user);

    @NotNull String getName(@NotNull final User user);
}