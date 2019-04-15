package ru.khmelev.tm.service;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.api.repository.IUserRepository;
import ru.khmelev.tm.api.service.IService;
import ru.khmelev.tm.api.service.IUserService;
import ru.khmelev.tm.endpoint.util.PasswordHashUtil;
import ru.khmelev.tm.entity.User;

import java.util.Objects;

public final class UserService extends AbstractIdentifiableService<User> implements IService<User>, IUserService {

    @NotNull
    private final IUserRepository userRepository;

    public UserService(@NotNull final IUserRepository userRepository) {
        super(userRepository);
        this.userRepository = userRepository;
    }

    @NotNull
    @Override
    public String getId(@NotNull final User user) {
        return user.getId();
    }

    @NotNull
    @Override
    public String getName(@NotNull final User user) {
        return user.getLogin();
    }

    @Override
    public void userSetPassword(@NotNull final String login, @NotNull final String pass) {
        if (!login.isEmpty() && !pass.isEmpty()) {
            for (User user : userRepository.findAll()) {
                if (user.getLogin().equals(login)) {
                    @NotNull final String password = Objects.requireNonNull(PasswordHashUtil.md5(pass));
                    user.setHashPassword(password);
                    userRepository.merge(user.getId(), user);
                }
            }
        }
    }

    @Override
    public User getUserFromSession(@NotNull final String userId) {
        return userRepository.findOne(userId);
    }
}