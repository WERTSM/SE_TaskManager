package ru.khmelev.tm.service;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.api.IUserRepository;
import ru.khmelev.tm.api.IUserService;
import ru.khmelev.tm.entity.User;

public final class UserService extends AbstractIdentifiableService<User> implements IUserService {

    @NotNull
    private IUserRepository userRepository;

    private String password;

    public UserService(@NotNull final IUserRepository userRepository) {
        super(userRepository);
        this.userRepository = userRepository;
    }

    @NotNull
    @Override
    public String getId(@NotNull User user) {
        return user.getId();
    }

    @NotNull
    @Override
    public String getName(@NotNull User user) {
        return user.getLogin();
    }
}