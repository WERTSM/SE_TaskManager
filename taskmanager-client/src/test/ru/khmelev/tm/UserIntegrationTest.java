import org.jetbrains.annotations.NotNull;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.khmelev.tm.api.endpoint.IUserEndpoint;
import ru.khmelev.tm.api.endpoint.Role;
import ru.khmelev.tm.api.endpoint.SessionDTO;
import ru.khmelev.tm.api.endpoint.UserDTO;
import ru.khmelev.tm.util.PasswordHashUtil;
import ru.khmelev.tm.util.SpringJPAConfigUtil;

import java.util.Objects;
import java.util.UUID;

@Category(IUserIntegrationTest.class)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringJPAConfigUtil.class)
public class UserIntegrationTest {

    @Autowired
    private IUserEndpoint userEndpoint;

    private SessionDTO sessionDTO;

    private UserDTO testUserDTO;

    @Before
    public void createEntity() {
        @NotNull final UserDTO userDTO = new UserDTO();
        userDTO.setId(UUID.randomUUID().toString());
        @NotNull final String hashPassword = Objects.requireNonNull(PasswordHashUtil.md5("test"));
        userDTO.setHashPassword(hashPassword);
        userDTO.setLogin("test");
        userDTO.setRole(Role.ADMIN);
        userEndpoint.createUser(userDTO.getId(), userDTO);
        sessionDTO = userEndpoint.userLogin("test", "test");
        testUserDTO = userDTO;
    }

    @Test
    public void createAndFindUser() {
        @NotNull final UserDTO userDTOFromServer = userEndpoint.findUser(sessionDTO, testUserDTO.getId());
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
        userEndpoint.createUser(userDTO2.getId(), userDTO2);

        @NotNull final SessionDTO sessionDTO2 = userEndpoint.userLogin("test2", "test2");

        @NotNull int count = 0;
        for (@NotNull UserDTO userDTO3 : userEndpoint.findAllUser(sessionDTO)) {
            if (userDTO3.getLogin().equals("test") || userDTO3.getLogin().equals("test2")) {
                count++;
            }
        }
        Assert.assertEquals(count, 2);

        @NotNull final UserDTO userDTOFromServer = userEndpoint.findUser(sessionDTO, testUserDTO.getId());
        Assert.assertEquals(userDTOFromServer.getId(), testUserDTO.getId());
        Assert.assertEquals(userDTOFromServer.getHashPassword(), testUserDTO.getHashPassword());
        Assert.assertEquals(userDTOFromServer.getLogin(), testUserDTO.getLogin());
        Assert.assertEquals(userDTOFromServer.getRole(), testUserDTO.getRole());

        @NotNull final UserDTO userDTOFromServer2 = userEndpoint.findUser(sessionDTO2, userDTO2.getId());
        Assert.assertEquals(userDTOFromServer2.getId(), userDTO2.getId());
        Assert.assertEquals(userDTOFromServer2.getHashPassword(), userDTO2.getHashPassword());
        Assert.assertEquals(userDTOFromServer2.getLogin(), userDTO2.getLogin());
        Assert.assertEquals(userDTOFromServer2.getRole(), userDTO2.getRole());

        userEndpoint.removeUser(sessionDTO, userDTO2.getId());
    }

    @Test
    public void editUser() {
        @NotNull final UserDTO editUserDTO = new UserDTO();
        editUserDTO.setId(testUserDTO.getId());
        editUserDTO.setHashPassword(testUserDTO.getHashPassword());
        editUserDTO.setLogin("test123");
        editUserDTO.setRole(Role.USER);

        userEndpoint.editUser(sessionDTO, testUserDTO.getId(), editUserDTO);

        @NotNull final UserDTO userDTOFromServer = userEndpoint.findUser(sessionDTO, testUserDTO.getId());
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
        userEndpoint.createUser(userDTO2.getId(), userDTO2);

        userEndpoint.removeUser(sessionDTO, userDTO2.getId());

        @NotNull boolean resultRemoveUser = true;
        for (@NotNull UserDTO userDTO3 : userEndpoint.findAllUser(sessionDTO)) {
            if (userDTO3.getLogin().equals("test2")) {
                resultRemoveUser = false;
            }
        }
        Assert.assertTrue(resultRemoveUser);
    }

    @After
    public void deleteEntity() {
        userEndpoint.removeUser(sessionDTO, testUserDTO.getId());
    }
}