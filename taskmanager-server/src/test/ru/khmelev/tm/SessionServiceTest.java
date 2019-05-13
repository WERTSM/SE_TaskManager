package ru.khmelev.tm;

import org.jetbrains.annotations.NotNull;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.khmelev.tm.api.endpoint.IUserEndpoint;
import ru.khmelev.tm.api.service.ISessionService;
import ru.khmelev.tm.api.service.IUserService;
import ru.khmelev.tm.dto.SessionDTO;
import ru.khmelev.tm.dto.UserDTO;
import ru.khmelev.tm.enumeration.Role;
import ru.khmelev.tm.exception.EndpointException;
import ru.khmelev.tm.util.ConverterUtil;
import ru.khmelev.tm.util.PasswordHashUtil;
import ru.khmelev.tm.util.SpringJPATestingConfigUtil;

import javax.inject.Inject;
import java.util.Date;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;

import static ru.khmelev.tm.util.SignatureUtil.sign;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringJPATestingConfigUtil.class)
public class SessionServiceTest {

    @Inject
    private ISessionService sessionService;

    @Inject
    private IUserService userService;

    @Inject
    private IUserEndpoint userEndpoint;

    private UserDTO testUserDTO;

    private SessionDTO testSessionDTO;

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

        @NotNull final SessionDTO sessionDTO = new SessionDTO();
        sessionDTO.setId(UUID.randomUUID().toString());
        sessionDTO.setDateCreate(new Date());
        Random random = new Random();
        sessionDTO.setSignature(Objects.requireNonNull(sign(testUserDTO, String.valueOf(random.nextInt(1000)), random.nextInt(1000))));
        sessionDTO.setUserId(testUserDTO.getId());
        sessionService.createEntity(sessionDTO.getId(), sessionDTO);
        testSessionDTO = sessionDTO;
    }

    @Test
    public void createAndFindEntity() {
        @NotNull final SessionDTO sessionDTOFromServer = sessionService.findEntity(testSessionDTO.getId());
        Assert.assertEquals(sessionDTOFromServer.getId(), testSessionDTO.getId());
        Assert.assertEquals(ConverterUtil.convertDateFormat(sessionDTOFromServer.getDateCreate()), ConverterUtil.convertDateFormat(testSessionDTO.getDateCreate()));
        Assert.assertEquals(sessionDTOFromServer.getSignature(), testSessionDTO.getSignature());
        Assert.assertEquals(sessionDTOFromServer.getUserId(), testSessionDTO.getUserId());
    }

    @Test
    public void setUser() {
        sessionService.setUser(testSessionDTO.getId(), null);
        Assert.assertNull(sessionService.findEntity(testSessionDTO.getId()).getUserId());
        sessionService.removeEntity(testSessionDTO.getId());
    }

    @Test
    public void findAll() {
        @NotNull final UserDTO userDTO2 = new UserDTO();
        userDTO2.setId(UUID.randomUUID().toString());
        @NotNull final String hashPassword = Objects.requireNonNull(PasswordHashUtil.md5("test2"));
        userDTO2.setHashPassword(hashPassword);
        userDTO2.setLogin("test2");
        userDTO2.setRole(Role.ADMIN);
        userService.createEntity(userDTO2.getId(), userDTO2);

        @NotNull final SessionDTO sessionDTO2 = new SessionDTO();
        sessionDTO2.setId(UUID.randomUUID().toString());
        sessionDTO2.setDateCreate(new Date());
        Random random = new Random();
        sessionDTO2.setSignature(Objects.requireNonNull(sign(userDTO2, String.valueOf(random.nextInt(1000)), random.nextInt(1000))));
        sessionDTO2.setUserId(userDTO2.getId());
        sessionService.createEntity(sessionDTO2.getId(), sessionDTO2);

        @NotNull int count = 0;
        for (@NotNull SessionDTO sessionDTO3 : sessionService.findAll()) {
            if (sessionDTO3.getUserId().equals(testUserDTO.getId()) || (sessionDTO3.getUserId().equals(userDTO2.getId()))) {
                count++;
            }
        }
        Assert.assertEquals(count, 2);

        @NotNull final SessionDTO sessionDTOFromServer = sessionService.findEntity(testSessionDTO.getId());
        Assert.assertEquals(sessionDTOFromServer.getId(), testSessionDTO.getId());
        Assert.assertEquals(ConverterUtil.convertDateFormat(sessionDTOFromServer.getDateCreate()), ConverterUtil.convertDateFormat(testSessionDTO.getDateCreate()));
        Assert.assertEquals(sessionDTOFromServer.getSignature(), testSessionDTO.getSignature());
        Assert.assertEquals(sessionDTOFromServer.getUserId(), testSessionDTO.getUserId());

        @NotNull final SessionDTO sessionDTO2FromServer = sessionService.findEntity(sessionDTO2.getId());
        Assert.assertEquals(sessionDTO2FromServer.getId(), sessionDTO2.getId());
        Assert.assertEquals(ConverterUtil.convertDateFormat(sessionDTO2FromServer.getDateCreate()), ConverterUtil.convertDateFormat(sessionDTO2.getDateCreate()));
        Assert.assertEquals(sessionDTO2FromServer.getSignature(), sessionDTO2.getSignature());
        Assert.assertEquals(sessionDTO2FromServer.getUserId(), sessionDTO2.getUserId());

        userService.removeEntity(userDTO2.getId());
    }

    @Test
    public void editEntity() {
        @NotNull final SessionDTO editSessionDTO = new SessionDTO();
        editSessionDTO.setId(UUID.randomUUID().toString());
        editSessionDTO.setDateCreate(new Date());
        Random random = new Random();
        editSessionDTO.setSignature(Objects.requireNonNull(sign(testUserDTO, String.valueOf(random.nextInt(1000)), random.nextInt(1000))));
        editSessionDTO.setUserId(testUserDTO.getId());

        sessionService.editEntity(testSessionDTO.getId(), editSessionDTO);

        @NotNull final SessionDTO sessionDTOFromServer = sessionService.findEntity(testSessionDTO.getId());
        Assert.assertEquals(sessionDTOFromServer.getId(), testSessionDTO.getId());
        Assert.assertEquals(ConverterUtil.convertDateFormat(sessionDTOFromServer.getDateCreate()), ConverterUtil.convertDateFormat(editSessionDTO.getDateCreate()));
        Assert.assertEquals(sessionDTOFromServer.getSignature(), editSessionDTO.getSignature());
        Assert.assertEquals(sessionDTOFromServer.getUserId(), editSessionDTO.getUserId());
    }

    @Test
    public void removeEntity() {
        @NotNull UserDTO userDTO2 = new UserDTO();
        userDTO2.setId(UUID.randomUUID().toString());
        @NotNull final String hashPassword = Objects.requireNonNull(PasswordHashUtil.md5("test3"));
        userDTO2.setHashPassword(hashPassword);
        userDTO2.setLogin("test3");
        userDTO2.setRole(Role.ADMIN);
        userService.createEntity(userDTO2.getId(), userDTO2);

        @NotNull final SessionDTO sessionDTO2 = new SessionDTO();
        sessionDTO2.setId(UUID.randomUUID().toString());
        sessionDTO2.setDateCreate(new Date());
        Random random = new Random();
        sessionDTO2.setSignature(Objects.requireNonNull(sign(testUserDTO, String.valueOf(random.nextInt(1000)), random.nextInt(1000))));
        sessionDTO2.setUserId(userDTO2.getId());
        sessionService.createEntity(sessionDTO2.getId(), sessionDTO2);

        sessionService.setUser(sessionDTO2.getId(), null);
        sessionService.removeEntity(sessionDTO2.getId());

        @NotNull boolean resultRemoveSession = true;
        for (@NotNull SessionDTO sessionDTO3 : sessionService.findAll()) {
            if (sessionDTO3.getId().equals(sessionDTO2.getId())) {
                resultRemoveSession = false;
            }
        }
        Assert.assertTrue(resultRemoveSession);

        userService.removeEntity(userDTO2.getId());
    }

    @Test
    public void clearEntity() {
        @NotNull final SessionDTO sessionDTO2 = new SessionDTO();
        sessionDTO2.setId(UUID.randomUUID().toString());
        sessionDTO2.setDateCreate(new Date());
        Random random = new Random();
        sessionDTO2.setSignature(Objects.requireNonNull(sign(testUserDTO, String.valueOf(random.nextInt(1000)), random.nextInt(1000))));
        sessionDTO2.setUserId(testUserDTO.getId());
        sessionService.createEntity(sessionDTO2.getId(), sessionDTO2);

        sessionService.clearEntity();

        Assert.assertEquals(sessionService.findAll().size(), 0);
    }

    @Test(expected = EndpointException.class)
    public void checkSession() {
        testSessionDTO.setSignature("hacker");
        sessionService.checkSession(testSessionDTO);
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
    public void deleteTestEntity() {
        userService.removeEntity(testUserDTO.getId());
    }
}