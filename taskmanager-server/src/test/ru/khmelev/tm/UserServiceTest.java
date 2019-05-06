package ru.khmelev.tm;

import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner;
import org.jetbrains.annotations.NotNull;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import ru.khmelev.tm.api.endpoint.IUserEndpoint;
import ru.khmelev.tm.api.service.IUserService;
import ru.khmelev.tm.dto.UserDTO;
import ru.khmelev.tm.enumeration.Role;
import ru.khmelev.tm.util.PasswordHashUtil;

import javax.inject.Inject;
import java.util.Objects;
import java.util.UUID;

@RunWith(CdiTestRunner.class)
public class UserServiceTest {

    @Inject
    private IUserService userService;

    @Inject
    private IUserEndpoint userEndpoint;

    private UserDTO testUserDTO;

    @Before
    public void createEntity() {
        @NotNull final UserDTO userDTO = new UserDTO();
        userDTO.setId(UUID.randomUUID().toString());
        @NotNull final String hashPassword = Objects.requireNonNull(PasswordHashUtil.md5("test"));
        userDTO.setHashPassword(hashPassword);
        userDTO.setLogin("test");
        userDTO.setRole(Role.ADMIN);
        userService.createEntity(userDTO.getId(), userDTO);
        testUserDTO = userDTO;
    }

    @Test
    public void createAndFindUser() {
        @NotNull final UserDTO userDTOFromServer = userService.findEntity(testUserDTO.getId());
        Assert.assertEquals(userDTOFromServer.getId(), testUserDTO.getId());
        Assert.assertEquals(userDTOFromServer.getHashPassword(), testUserDTO.getHashPassword());
        Assert.assertEquals(userDTOFromServer.getLogin(), testUserDTO.getLogin());
        Assert.assertEquals(userDTOFromServer.getRole(), testUserDTO.getRole());
    }

    @Test
    public void findAllUser() {
        @NotNull final UserDTO userDTO2 = new UserDTO();
        userDTO2.setId(UUID.randomUUID().toString());
        @NotNull final String hashPassword2 = Objects.requireNonNull(PasswordHashUtil.md5("test2"));
        userDTO2.setHashPassword(hashPassword2);
        userDTO2.setLogin("test2");
        userDTO2.setRole(Role.USER);
        userService.createEntity(userDTO2.getId(), userDTO2);

        @NotNull int count = 0;
        for (@NotNull UserDTO userDTO3 : userService.findAll()) {
            if (userDTO3.getLogin().equals("test") || userDTO3.getLogin().equals("test2")) {
                count++;
            }
        }
        Assert.assertEquals(count, 2);

        @NotNull final UserDTO userDTOFromServer = userService.findEntity(testUserDTO.getId());
        Assert.assertEquals(userDTOFromServer.getId(), testUserDTO.getId());
        Assert.assertEquals(userDTOFromServer.getHashPassword(), testUserDTO.getHashPassword());
        Assert.assertEquals(userDTOFromServer.getLogin(), testUserDTO.getLogin());
        Assert.assertEquals(userDTOFromServer.getRole(), testUserDTO.getRole());

        @NotNull final UserDTO userDTOFromServer2 = userService.findEntity(userDTO2.getId());
        Assert.assertEquals(userDTOFromServer2.getId(), userDTO2.getId());
        Assert.assertEquals(userDTOFromServer2.getHashPassword(), userDTO2.getHashPassword());
        Assert.assertEquals(userDTOFromServer2.getLogin(), userDTO2.getLogin());
        Assert.assertEquals(userDTOFromServer2.getRole(), userDTO2.getRole());

        userService.removeEntity(userDTO2.getId());
    }

    @Test
    public void editUser() {
        @NotNull final UserDTO editUserDTO = new UserDTO();
        editUserDTO.setId(testUserDTO.getId());
        editUserDTO.setHashPassword(testUserDTO.getHashPassword());
        editUserDTO.setLogin("test123");
        editUserDTO.setRole(Role.USER);

        userService.editEntity(testUserDTO.getId(), editUserDTO);

        @NotNull final UserDTO userDTOFromServer = userService.findEntity(testUserDTO.getId());
        Assert.assertEquals(userDTOFromServer.getId(), editUserDTO.getId());
        Assert.assertEquals(userDTOFromServer.getHashPassword(), editUserDTO.getHashPassword());
        Assert.assertEquals(userDTOFromServer.getLogin(), editUserDTO.getLogin());
        Assert.assertEquals(userDTOFromServer.getRole(), editUserDTO.getRole());
    }

    @Test
    public void removeUser() {
        @NotNull final UserDTO userDTO2 = new UserDTO();
        userDTO2.setId(UUID.randomUUID().toString());
        @NotNull final String hashPassword2 = Objects.requireNonNull(PasswordHashUtil.md5("test2"));
        userDTO2.setHashPassword(hashPassword2);
        userDTO2.setLogin("test2");
        userDTO2.setRole(Role.USER);
        userService.createEntity(userDTO2.getId(), userDTO2);

        userService.removeEntity(userDTO2.getId());

        @NotNull boolean resultRemoveUser = true;
        for (@NotNull UserDTO userDTO3 : userService.findAll()) {
            if (userDTO3.getLogin().equals("test2")) {
                resultRemoveUser = false;
            }
        }
        Assert.assertTrue(resultRemoveUser);
    }

    @Test
    public void getId() {
        Assert.assertEquals(testUserDTO.getId(), userService.getId(testUserDTO));
    }

    @Test
    public void getName() {
        Assert.assertEquals(testUserDTO.getLogin(), userService.getName(testUserDTO));
    }

    @Test
    public void userSetPassword() {
        userService.userSetPassword(testUserDTO.getLogin(), "123");
        @NotNull final String hashPassword = Objects.requireNonNull(PasswordHashUtil.md5("123"));
        Assert.assertEquals(hashPassword, userService.findEntity(testUserDTO.getId()).getHashPassword());
    }

    @Test
    public void getUserFromSession() {
        @NotNull final UserDTO userDTO = userService.getUserDTOFromSession(testUserDTO.getId());
        Assert.assertEquals(userDTO.getId(), testUserDTO.getId());
        Assert.assertEquals(userDTO.getHashPassword(), testUserDTO.getHashPassword());
        Assert.assertEquals(userDTO.getLogin(), testUserDTO.getLogin());
        Assert.assertEquals(userDTO.getRole(), testUserDTO.getRole());
    }

    @After
    public void deleteEntity() {
        userService.removeEntity(testUserDTO.getId());
    }
}