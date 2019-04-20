package ru.khmelev.tm.endpoint;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.api.endpoint.IUserEndpoint;
import ru.khmelev.tm.api.service.ISessionService;
import ru.khmelev.tm.api.service.IUserService;
import ru.khmelev.tm.dto.SessionDTO;
import ru.khmelev.tm.dto.UserDTO;
import ru.khmelev.tm.util.PasswordHashUtil;

import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.Collection;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;

import static ru.khmelev.tm.util.SignatureUtil.sign;

@WebService(endpointInterface = "ru.khmelev.tm.api.endpoint.IUserEndpoint")
public final class UserEndpoint implements IUserEndpoint {

    @NotNull
    private ISessionService sessionService;

    @NotNull
    private IUserService userService;

    public UserEndpoint(@NotNull final ISessionService sessionService, @NotNull final IUserService userService) {
        this.sessionService = sessionService;
        this.userService = userService;
    }

    @Override
    public void createUser(
            @WebParam(name = "id") @NotNull final String id,
            @WebParam(name = "user") @NotNull final UserDTO user
    ) {
        userService.createEntity(id, user);
    }

    @Override
    public Collection<UserDTO> findAllUser(@WebParam(name = "session") @NotNull final SessionDTO sessionDTO) {
        sessionService.checkSession(sessionDTO);
        return userService.findAll();
    }

    @Override
    @NotNull
    public UserDTO findUser(
            @WebParam(name = "session") @NotNull final SessionDTO sessionDTO,
            @WebParam(name = "id") @NotNull final String id
    ) {
        sessionService.checkSession(sessionDTO);
        return userService.findEntity(id);
    }

    @Override
    public void editUser(
            @WebParam(name = "session") @NotNull final SessionDTO sessionDTO,
            @WebParam(name = "id") @NotNull final String id,
            @WebParam(name = "user") @NotNull UserDTO user
    ) {
        sessionService.checkSession(sessionDTO);
        userService.editEntity(id, user);
    }

    @Override
    public void removeUser(
            @WebParam(name = "session") @NotNull final SessionDTO sessionDTO,
            @WebParam(name = "id") @NotNull final String id
    ) {
        sessionService.checkSession(sessionDTO);
        userService.removeEntity(id);
    }

    @Override
    public SessionDTO userLogin(
            @WebParam(name = "login") @NotNull final String login,
            @WebParam(name = "password") @NotNull final String pass
    ) {
        if (!login.isEmpty() && !pass.isEmpty()) {
            for (UserDTO userDTO : userService.findAll()) {
                if (userDTO.getLogin().equals(login)) {
                    String password = PasswordHashUtil.md5(pass);
                    String passwordUserRepository = userDTO.getHashPassword();
                    if (passwordUserRepository.equals(password)) {
                        final SessionDTO sessionDTO = new SessionDTO();
                        sessionDTO.setId(UUID.randomUUID().toString());
                        sessionDTO.setUserId(userDTO.getId());
                        Random random = new Random();
                        sessionDTO.setSignature(Objects.requireNonNull(sign(userDTO, String.valueOf(random.nextInt(1000)), random.nextInt(1000))));
                        sessionService.setSession(sessionDTO);
                        return sessionDTO;
                    }
                }
            }
        }
        return null;
    }

    @Override
    public void userLogOut(@WebParam(name = "session") @NotNull final SessionDTO sessionDTO) {
        sessionService.checkSession(sessionDTO);
        sessionService.removeSession(sessionDTO);
    }

    @Override
    public void userSetPassword(@WebParam(name = "session") @NotNull final SessionDTO sessionDTO,
                                @WebParam(name = "login") @NotNull final String login,
                                @WebParam(name = "password") @NotNull final String pass
    ) {
        sessionService.checkSession(sessionDTO);
        userService.userSetPassword(login, pass);
    }

    @Override
    public UserDTO getUserFromSession(@WebParam(name = "session") @NotNull final SessionDTO sessionDTO) {
        sessionService.checkSession(sessionDTO);
        return userService.getUserFromSession(sessionDTO.getUserId());
    }

    @NotNull
    @Override
    public String getId(@WebParam(name = "user") @NotNull UserDTO userDTO) {
        return userDTO.getId();
    }

    @NotNull
    @Override
    public String getName(@WebParam(name = "user") @NotNull UserDTO userDTO) {
        return userDTO.getLogin();
    }
}