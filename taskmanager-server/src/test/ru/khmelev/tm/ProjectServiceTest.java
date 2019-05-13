package ru.khmelev.tm;

import org.jetbrains.annotations.NotNull;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.khmelev.tm.api.endpoint.IUserEndpoint;
import ru.khmelev.tm.api.service.IProjectService;
import ru.khmelev.tm.api.service.IUserService;
import ru.khmelev.tm.dto.ProjectDTO;
import ru.khmelev.tm.dto.SessionDTO;
import ru.khmelev.tm.dto.UserDTO;
import ru.khmelev.tm.enumeration.Role;
import ru.khmelev.tm.enumeration.Status;
import ru.khmelev.tm.util.*;

import java.util.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringJPATestingConfigUtil.class)
public class ProjectServiceTest {

    @Autowired
    private IProjectService projectService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IUserEndpoint userEndpoint;

    private SessionDTO sessionDTO;

    private UserDTO testUserDTO;

    @Before
    public void createTestEntity() {
        @NotNull final UserDTO userDTO = new UserDTO();
        userDTO.setId(UUID.randomUUID().toString());
        @NotNull final String hashPassword = Objects.requireNonNull(PasswordHashUtil.md5("test"));
        userDTO.setHashPassword(hashPassword);
        userDTO.setLogin("test");
        userDTO.setRole(Role.USER);
        userService.createEntity(userDTO.getId(), userDTO);
        sessionDTO = userEndpoint.userLogin("test", "test");
        testUserDTO = userDTO;
    }

    @Test
    public void createAndFindEntityHibernateSecondTwoLevelCash() {
        @NotNull final ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setId(UUID.randomUUID().toString());
        projectDTO.setName("ProjectNameTest");
        projectDTO.setDescription("ProjectDescriptionTest");
        projectDTO.setDateCreate(new Date());
        projectDTO.setDateStart(ConverterUtil.convertFromStringToDate("01.01.2000"));
        projectDTO.setDateFinish(ConverterUtil.convertFromStringToDate("02.02.2002"));
        projectDTO.setStatus(Status.INPROGRESS);
        projectDTO.setUserId(testUserDTO.getId());

        projectService.createEntity(projectDTO.getId(), projectDTO);

        for (int i = 0; i < 100; i++) {
            @NotNull ProjectDTO projectDTOfromServer = projectService.findEntity(projectDTO.getId(), sessionDTO.getUserId());
            Assert.assertEquals(projectDTOfromServer.getId(), projectDTO.getId());
            Assert.assertEquals(projectDTOfromServer.getName(), projectDTO.getName());
            Assert.assertEquals(projectDTOfromServer.getDescription(), projectDTO.getDescription());
            Assert.assertEquals(ConverterUtil.convertDateFormat(projectDTOfromServer.getDateStart()), ConverterUtil.convertDateFormat(projectDTO.getDateStart()));
            Assert.assertEquals(ConverterUtil.convertDateFormat(projectDTOfromServer.getDateFinish()), ConverterUtil.convertDateFormat(projectDTO.getDateFinish()));
            Assert.assertEquals(ConverterUtil.convertDateFormat(projectDTOfromServer.getDateCreate()), ConverterUtil.convertDateFormat(projectDTO.getDateCreate()));
            Assert.assertEquals(projectDTOfromServer.getStatus(), projectDTO.getStatus());
            Assert.assertEquals(projectDTOfromServer.getUserId(), projectDTO.getUserId());
        }
    }

    @Test
    public void createAndFindEntity() {
        @NotNull final ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setId(UUID.randomUUID().toString());
        projectDTO.setName("ProjectNameTest");
        projectDTO.setDescription("ProjectDescriptionTest");
        projectDTO.setDateCreate(new Date());
        projectDTO.setDateStart(ConverterUtil.convertFromStringToDate("01.01.2000"));
        projectDTO.setDateFinish(ConverterUtil.convertFromStringToDate("02.02.2002"));
        projectDTO.setStatus(Status.INPROGRESS);
        projectDTO.setUserId(testUserDTO.getId());

        projectService.createEntity(projectDTO.getId(), projectDTO);

        @NotNull final ProjectDTO projectDTOfromServer = projectService.findEntity(projectDTO.getId(), sessionDTO.getUserId());
        Assert.assertEquals(projectDTOfromServer.getId(), projectDTO.getId());
        Assert.assertEquals(projectDTOfromServer.getName(), projectDTO.getName());
        Assert.assertEquals(projectDTOfromServer.getDescription(), projectDTO.getDescription());
        Assert.assertEquals(ConverterUtil.convertDateFormat(projectDTOfromServer.getDateStart()), ConverterUtil.convertDateFormat(projectDTO.getDateStart()));
        Assert.assertEquals(ConverterUtil.convertDateFormat(projectDTOfromServer.getDateFinish()), ConverterUtil.convertDateFormat(projectDTO.getDateFinish()));
        Assert.assertEquals(ConverterUtil.convertDateFormat(projectDTOfromServer.getDateCreate()), ConverterUtil.convertDateFormat(projectDTO.getDateCreate()));
        Assert.assertEquals(projectDTOfromServer.getStatus(), projectDTO.getStatus());
        Assert.assertEquals(projectDTOfromServer.getUserId(), projectDTO.getUserId());
    }

    @Test
    public void findAll() {
        @NotNull final ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setId(UUID.randomUUID().toString());
        projectDTO.setName("ProjectNameTest");
        projectDTO.setDescription("ProjectDescriptionTest");
        projectDTO.setDateCreate(new Date());
        projectDTO.setDateStart(ConverterUtil.convertFromStringToDate("01.01.2000"));
        projectDTO.setDateFinish(ConverterUtil.convertFromStringToDate("02.02.2002"));
        projectDTO.setStatus(Status.INPROGRESS);
        projectDTO.setUserId(testUserDTO.getId());

        projectService.createEntity(projectDTO.getId(), projectDTO);

        @NotNull final ProjectDTO projectDTO2 = new ProjectDTO();
        projectDTO2.setId(UUID.randomUUID().toString());
        projectDTO2.setName("ProjectNameTest2");
        projectDTO2.setDescription("ProjectDescriptionTest2");
        projectDTO2.setDateCreate(new Date());
        projectDTO2.setDateStart(ConverterUtil.convertFromStringToDate("02.02.2002"));
        projectDTO2.setDateFinish(ConverterUtil.convertFromStringToDate("03.03.2003"));
        projectDTO2.setStatus(Status.INPROGRESS);
        projectDTO2.setUserId(testUserDTO.getId());

        projectService.createEntity(projectDTO2.getId(), projectDTO2);

        Assert.assertEquals(projectService.findAll(sessionDTO.getUserId()).size(), 2);

        @NotNull final ProjectDTO projectDTOfromServer = projectService.findEntity(projectDTO.getId(), sessionDTO.getUserId());
        Assert.assertEquals(projectDTOfromServer.getId(), projectDTO.getId());
        Assert.assertEquals(projectDTOfromServer.getName(), projectDTO.getName());
        Assert.assertEquals(projectDTOfromServer.getDescription(), projectDTO.getDescription());
        Assert.assertEquals(ConverterUtil.convertDateFormat(projectDTOfromServer.getDateStart()), ConverterUtil.convertDateFormat(projectDTO.getDateStart()));
        Assert.assertEquals(ConverterUtil.convertDateFormat(projectDTOfromServer.getDateFinish()), ConverterUtil.convertDateFormat(projectDTO.getDateFinish()));
        Assert.assertEquals(ConverterUtil.convertDateFormat(projectDTOfromServer.getDateCreate()), ConverterUtil.convertDateFormat(projectDTO.getDateCreate()));
        Assert.assertEquals(projectDTOfromServer.getStatus(), projectDTO.getStatus());
        Assert.assertEquals(projectDTOfromServer.getUserId(), projectDTO.getUserId());

        @NotNull final ProjectDTO projectDTOfromServer2 = projectService.findEntity(projectDTO2.getId(), sessionDTO.getUserId());
        Assert.assertEquals(projectDTOfromServer2.getId(), projectDTO2.getId());
        Assert.assertEquals(projectDTOfromServer2.getName(), projectDTO2.getName());
        Assert.assertEquals(projectDTOfromServer2.getDescription(), projectDTO2.getDescription());
        Assert.assertEquals(ConverterUtil.convertDateFormat(projectDTOfromServer2.getDateStart()), ConverterUtil.convertDateFormat(projectDTO2.getDateStart()));
        Assert.assertEquals(ConverterUtil.convertDateFormat(projectDTOfromServer2.getDateFinish()), ConverterUtil.convertDateFormat(projectDTO2.getDateFinish()));
        Assert.assertEquals(ConverterUtil.convertDateFormat(projectDTOfromServer2.getDateCreate()), ConverterUtil.convertDateFormat(projectDTO2.getDateCreate()));
        Assert.assertEquals(projectDTOfromServer2.getStatus(), projectDTO2.getStatus());
        Assert.assertEquals(projectDTOfromServer2.getUserId(), projectDTO2.getUserId());
    }

    @Test
    public void editEntity() {
        @NotNull final ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setId(UUID.randomUUID().toString());
        projectDTO.setName("ProjectNameTest");
        projectDTO.setDescription("ProjectDescriptionTest");
        projectDTO.setDateCreate(new Date());
        projectDTO.setDateStart(ConverterUtil.convertFromStringToDate("01.01.2000"));
        projectDTO.setDateFinish(ConverterUtil.convertFromStringToDate("02.02.2002"));
        projectDTO.setStatus(Status.INPROGRESS);
        projectDTO.setUserId(testUserDTO.getId());

        projectService.createEntity(projectDTO.getId(), projectDTO);

        @NotNull final ProjectDTO editProjectDTO = projectDTO;
        editProjectDTO.setName("ProjectEditNameTest");
        editProjectDTO.setDescription("ProjectEditDescriptionTest");
        editProjectDTO.setDateStart(ConverterUtil.convertFromStringToDate("11.11.2100"));
        editProjectDTO.setDateFinish(ConverterUtil.convertFromStringToDate("12.12.2102"));
        editProjectDTO.setStatus(Status.DONE);

        projectService.editEntity(projectDTO.getId(), editProjectDTO, sessionDTO.getUserId());

        @NotNull final ProjectDTO projectDTOfromServer = projectService.findEntity(projectDTO.getId(), sessionDTO.getUserId());

        Assert.assertEquals(projectDTOfromServer.getId(), editProjectDTO.getId());
        Assert.assertEquals(projectDTOfromServer.getName(), editProjectDTO.getName());
        Assert.assertEquals(projectDTOfromServer.getDescription(), editProjectDTO.getDescription());
        Assert.assertEquals(ConverterUtil.convertDateFormat(projectDTOfromServer.getDateStart()), ConverterUtil.convertDateFormat(editProjectDTO.getDateStart()));
        Assert.assertEquals(ConverterUtil.convertDateFormat(projectDTOfromServer.getDateFinish()), ConverterUtil.convertDateFormat(editProjectDTO.getDateFinish()));
        Assert.assertEquals(ConverterUtil.convertDateFormat(projectDTOfromServer.getDateCreate()), ConverterUtil.convertDateFormat(editProjectDTO.getDateCreate()));
        Assert.assertEquals(projectDTOfromServer.getStatus(), editProjectDTO.getStatus());
        Assert.assertEquals(projectDTOfromServer.getUserId(), editProjectDTO.getUserId());
    }

    @Test
    public void findAllName() {
        for (int i = 0; i < 30; i++) {
            @NotNull final ProjectDTO projectDTO = new ProjectDTO();
            projectDTO.setId(UUID.randomUUID().toString());
            projectDTO.setName("ProjectNameTest" + i);
            projectDTO.setDescription("ProjectDescriptionTest");
            projectDTO.setDateCreate(new Date());
            projectDTO.setDateStart(ConverterUtil.convertFromStringToDate("01.01.2000"));
            projectDTO.setDateFinish(ConverterUtil.convertFromStringToDate("02.02.2002"));
            projectDTO.setStatus(Status.INPROGRESS);
            projectDTO.setUserId(testUserDTO.getId());
            projectService.createEntity(projectDTO.getId(), projectDTO);
        }

        @NotNull final List<ProjectDTO> listProjectDTOContainsName = new ArrayList<>(projectService.findAllName("ProjectNameTest1", sessionDTO.getUserId()));
        Assert.assertEquals(listProjectDTOContainsName.size(), 11);
    }

    @Test
    public void findAllDescription() {
        for (int i = 0; i < 31; i++) {
            @NotNull final ProjectDTO projectDTO = new ProjectDTO();
            projectDTO.setId(UUID.randomUUID().toString());
            projectDTO.setName("ProjectNameTest");
            projectDTO.setDescription("ProjectDescriptionTest" + i);
            projectDTO.setDateCreate(new Date());
            projectDTO.setDateStart(ConverterUtil.convertFromStringToDate("01.01.2000"));
            projectDTO.setDateFinish(ConverterUtil.convertFromStringToDate("02.02.2002"));
            projectDTO.setStatus(Status.INPROGRESS);
            projectDTO.setUserId(testUserDTO.getId());
            projectService.createEntity(projectDTO.getId(), projectDTO);
        }

        @NotNull final List<ProjectDTO> listProjectDTOContainsDescription = new ArrayList<>(projectService.findAllDescription("ProjectDescriptionTest3", sessionDTO.getUserId()));
        Assert.assertEquals(listProjectDTOContainsDescription.size(), 2);
    }

    @Test
    public void removeEntity() {
        @NotNull final ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setId(UUID.randomUUID().toString());
        projectDTO.setName("ProjectNameTest");
        projectDTO.setDescription("ProjectDescriptionTest");
        projectDTO.setDateCreate(new Date());
        projectDTO.setDateStart(ConverterUtil.convertFromStringToDate("01.01.2000"));
        projectDTO.setDateFinish(ConverterUtil.convertFromStringToDate("02.02.2002"));
        projectDTO.setStatus(Status.INPROGRESS);
        projectDTO.setUserId(testUserDTO.getId());

        projectService.createEntity(projectDTO.getId(), projectDTO);

        projectService.removeEntity(projectDTO.getId(), sessionDTO.getUserId());

        Assert.assertEquals(projectService.findAll(sessionDTO.getUserId()).size(), 0);
    }

    @Test
    public void clearEntity() {
        @NotNull final ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setId(UUID.randomUUID().toString());
        projectDTO.setName("ProjectNameTest");
        projectDTO.setDescription("ProjectDescriptionTest");
        projectDTO.setDateCreate(new Date());
        projectDTO.setDateStart(Objects.requireNonNull(ConverterUtil.convertFromStringToDate("01.01.2000")));
        projectDTO.setDateFinish(ConverterUtil.convertFromStringToDate("02.02.2002"));
        projectDTO.setStatus(Status.INPROGRESS);
        projectDTO.setUserId(testUserDTO.getId());

        projectService.createEntity(projectDTO.getId(), projectDTO);

        @NotNull final ProjectDTO projectDTO2 = new ProjectDTO();
        projectDTO2.setId(UUID.randomUUID().toString());
        projectDTO2.setName("ProjectNameTest2");
        projectDTO2.setDescription("ProjectDescriptionTest2");
        projectDTO2.setDateCreate(new Date());
        projectDTO2.setDateStart(ConverterUtil.convertFromStringToDate("02.02.2002"));
        projectDTO2.setDateFinish(ConverterUtil.convertFromStringToDate("03.03.2003"));
        projectDTO2.setStatus(Status.INPROGRESS);
        projectDTO2.setUserId(testUserDTO.getId());

        projectService.createEntity(projectDTO2.getId(), projectDTO2);

        projectService.clearEntity(sessionDTO.getUserId());

        Assert.assertEquals(projectService.findAll(sessionDTO.getUserId()).size(), 0);
    }

    @After
    public void deleteTestEntity() {
        userService.removeEntity(testUserDTO.getId());
    }
}