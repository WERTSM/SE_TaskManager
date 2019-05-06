package ru.khmelev.tm.service;

import org.apache.deltaspike.jpa.api.transaction.Transactional;
import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.api.repository.IUserRepository;
import ru.khmelev.tm.api.service.IUserService;
import ru.khmelev.tm.dto.UserDTO;
import ru.khmelev.tm.entity.User;
import ru.khmelev.tm.exception.ServiceException;
import ru.khmelev.tm.util.PasswordHashUtil;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@ApplicationScoped
public class UserService implements IUserService {

    @Inject
    private IUserRepository userRepository;

    @Override
    @Transactional
    public void createEntity(@NotNull final String id, @NotNull final UserDTO userDTO) {
        @NotNull final User user = new User();
        user.setId(id);
        fromDTOToUser(userDTO, user);

        userRepository.persist(user);
    }

    @NotNull
    @Override
    @Transactional
    public UserDTO findEntity(@NotNull final String id) {
        if (id.isEmpty()) throw new ServiceException();
        @NotNull final User user = userRepository.findById(id);
        return fromUserToDTO(user);
    }

    @NotNull
    @Override
    @Transactional
    public Collection<UserDTO> findAll() {
        @NotNull final Collection<User> list = userRepository.findAll();
        @NotNull final List<UserDTO> listDTO = new ArrayList<>();
        for (User user : list) {
            listDTO.add(fromUserToDTO(user));
        }
        return listDTO;
    }

    @Override
    @Transactional
    public void editEntity(@NotNull final String id, @NotNull UserDTO userDTO) {
        if (id.isEmpty()) throw new ServiceException();
        @NotNull final User user = userRepository.findById(id);
        userRepository.merge(fromDTOToUser(userDTO, user));
    }

    @Override
    @Transactional
    public void removeEntity(@NotNull final String id) {
        if (id.isEmpty()) throw new ServiceException();
        userRepository.remove(userRepository.findById(id));
    }

    @NotNull
    @Override
    public String getId(@NotNull final UserDTO userDTO) {
        return userDTO.getId();
    }

    @NotNull
    @Override
    public String getName(@NotNull final UserDTO userDTO) {
        return userDTO.getLogin();
    }

    @Override
    @Transactional
    public void userSetPassword(@NotNull final String login, @NotNull final String pass) {
        if (login.isEmpty() || pass.isEmpty()) throw new ServiceException();

        @NotNull final Collection<UserDTO> users = findAll();
        for (UserDTO userDTO : users) {
            if (userDTO.getLogin().equals(login)) {
                @NotNull final String password = Objects.requireNonNull(PasswordHashUtil.md5(pass));
                userDTO.setHashPassword(password);
                editEntity(userDTO.getId(), userDTO);
            }
        }
    }

    @NotNull
    @Override
    @Transactional
    public UserDTO getUserDTOFromSession(@NotNull final String userId) {
        return findEntity(userId);
    }

    @NotNull
    private User fromDTOToUser(@NotNull final UserDTO userDTO, @NotNull final User user) {
        user.setLogin(userDTO.getLogin());
        user.setHashPassword(userDTO.getHashPassword());
        user.setRole(userDTO.getRole());
        return user;
    }

    @NotNull
    private UserDTO fromUserToDTO(@NotNull final User user) {
        @NotNull final UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setLogin(user.getLogin());
        userDTO.setHashPassword(user.getHashPassword());
        userDTO.setRole(user.getRole());
        return userDTO;
    }
}