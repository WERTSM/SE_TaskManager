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
import ru.khmelev.tm.api.service.IProjectService;
import ru.khmelev.tm.api.service.ITaskService;
import ru.khmelev.tm.api.service.IUserService;
import ru.khmelev.tm.dto.ProjectDTO;
import ru.khmelev.tm.dto.SessionDTO;
import ru.khmelev.tm.dto.TaskDTO;
import ru.khmelev.tm.dto.UserDTO;
import ru.khmelev.tm.enumeration.Role;
import ru.khmelev.tm.enumeration.Status;
import ru.khmelev.tm.util.ConverterUtil;
import ru.khmelev.tm.util.PasswordHashUtil;
import ru.khmelev.tm.util.SpringJPATestingConfigUtil;

import javax.inject.Inject;
import java.util.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringJPATestingConfigUtil.class)
public class TaskServiceTest {

    @Inject
    private IUserService userService;

    @Inject
    private IProjectService projectService;

    @Inject
    private ITaskService taskService;

    @Inject
    private IUserEndpoint userEndpoint;

    private SessionDTO sessionDTO;

    private UserDTO testUserDTO;

    private ProjectDTO testProjectDTO;

    @Before
    public void createTestEntity() {
        @NotNull final UserDTO userDTO = new UserDTO();
        userDTO.setId(UUID.randomUUID().toString());
        @NotNull final String hashPassword = Objects.requireNonNull(PasswordHashUtil.md5("test"));
        userDTO.setHashPassword(hashPassword);
        userDTO.setLogin("test");
        userDTO.setRole(Role.USER);
        userEndpoint.createUser(userDTO.getId(), userDTO);
        sessionDTO = userEndpoint.userLogin("test", "test");
        testUserDTO = userDTO;

        @NotNull final ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setId(UUID.randomUUID().toString());
        projectDTO.setName("ProjectNameTest");
        projectDTO.setDescription("ProjectDescriptionTest");
        projectDTO.setDateCreate(new Date());
        projectDTO.setDateStart(ConverterUtil.convertFromStringToDate("01.01.2000"));
        projectDTO.setDateFinish(ConverterUtil.convertFromStringToDate("02.02.2002"));
        projectDTO.setStatus(Status.INPROGRESS);
        projectDTO.setUserId(testUserDTO.getId());

        testProjectDTO = projectDTO;
        projectService.createEntity(projectDTO.getId(), projectDTO);
    }

    @Test
    public void createAndFindEntityHibernateSecondTwoLevelCash() {
        @NotNull final TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(UUID.randomUUID().toString());
        taskDTO.setName("TaskNameTest");
        taskDTO.setDescription("TaskDescriptionTest");
        taskDTO.setDateCreate(new Date());
        taskDTO.setDateStart(ConverterUtil.convertFromStringToDate("01.01.2000"));
        taskDTO.setDateFinish(ConverterUtil.convertFromStringToDate("02.02.2002"));
        taskDTO.setStatus(Status.INPROGRESS);
        taskDTO.setProjectId(testProjectDTO.getId());
        taskDTO.setUserId(testUserDTO.getId());

        taskService.createEntity(taskDTO.getId(), taskDTO);

        for (int i = 0; i < 30; i++) {
            @NotNull final TaskDTO taskDTOFromServer = taskService.findEntity(taskDTO.getId(), sessionDTO.getUserId());
            Assert.assertEquals(taskDTOFromServer.getId(), taskDTO.getId());
            Assert.assertEquals(taskDTOFromServer.getName(), taskDTO.getName());
            Assert.assertEquals(taskDTOFromServer.getDescription(), taskDTO.getDescription());
            Assert.assertEquals(ConverterUtil.convertDateFormat(taskDTOFromServer.getDateStart()), ConverterUtil.convertDateFormat(taskDTO.getDateStart()));
            Assert.assertEquals(ConverterUtil.convertDateFormat(taskDTOFromServer.getDateFinish()), ConverterUtil.convertDateFormat(taskDTO.getDateFinish()));
            Assert.assertEquals(ConverterUtil.convertDateFormat(taskDTOFromServer.getDateCreate()), ConverterUtil.convertDateFormat(taskDTO.getDateCreate()));
            Assert.assertEquals(taskDTOFromServer.getStatus(), taskDTO.getStatus());
            Assert.assertEquals(taskDTOFromServer.getProjectId(), taskDTO.getProjectId());
            Assert.assertEquals(taskDTOFromServer.getUserId(), taskDTO.getUserId());
        }
    }

    @Test
    public void createAndFindEntity() {
        @NotNull final TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(UUID.randomUUID().toString());
        taskDTO.setName("TaskNameTest");
        taskDTO.setDescription("TaskDescriptionTest");
        taskDTO.setDateCreate(new Date());
        taskDTO.setDateStart(ConverterUtil.convertFromStringToDate("01.01.2000"));
        taskDTO.setDateFinish(ConverterUtil.convertFromStringToDate("02.02.2002"));
        taskDTO.setStatus(Status.INPROGRESS);
        taskDTO.setProjectId(testProjectDTO.getId());
        taskDTO.setUserId(testUserDTO.getId());

        taskService.createEntity(taskDTO.getId(), taskDTO);

        @NotNull final TaskDTO taskDTOFromServer = taskService.findEntity(taskDTO.getId(), sessionDTO.getUserId());
        Assert.assertEquals(taskDTOFromServer.getId(), taskDTO.getId());
        Assert.assertEquals(taskDTOFromServer.getName(), taskDTO.getName());
        Assert.assertEquals(taskDTOFromServer.getDescription(), taskDTO.getDescription());
        Assert.assertEquals(ConverterUtil.convertDateFormat(taskDTOFromServer.getDateStart()), ConverterUtil.convertDateFormat(taskDTO.getDateStart()));
        Assert.assertEquals(ConverterUtil.convertDateFormat(taskDTOFromServer.getDateFinish()), ConverterUtil.convertDateFormat(taskDTO.getDateFinish()));
        Assert.assertEquals(ConverterUtil.convertDateFormat(taskDTOFromServer.getDateCreate()), ConverterUtil.convertDateFormat(taskDTO.getDateCreate()));
        Assert.assertEquals(taskDTOFromServer.getStatus(), taskDTO.getStatus());
        Assert.assertEquals(taskDTOFromServer.getProjectId(), taskDTO.getProjectId());
        Assert.assertEquals(taskDTOFromServer.getUserId(), taskDTO.getUserId());
    }

    @Test
    public void findAllEntity() {
        @NotNull final TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(UUID.randomUUID().toString());
        taskDTO.setName("TaskNameTest");
        taskDTO.setDescription("TaskDescriptionTest");
        taskDTO.setDateCreate(new Date());
        taskDTO.setDateStart(ConverterUtil.convertFromStringToDate("01.01.2000"));
        taskDTO.setDateFinish(ConverterUtil.convertFromStringToDate("02.02.2002"));
        taskDTO.setStatus(Status.INPROGRESS);
        taskDTO.setProjectId(testProjectDTO.getId());
        taskDTO.setUserId(testUserDTO.getId());

        taskService.createEntity(taskDTO.getId(), taskDTO);

        @NotNull final TaskDTO taskDTO2 = new TaskDTO();
        taskDTO2.setId(UUID.randomUUID().toString());
        taskDTO2.setName("TaskNameTest2");
        taskDTO2.setDescription("TaskDescriptionTest2");
        taskDTO2.setDateCreate(new Date());
        taskDTO2.setDateStart(ConverterUtil.convertFromStringToDate("02.02.2002"));
        taskDTO2.setDateFinish(ConverterUtil.convertFromStringToDate("03.03.2003"));
        taskDTO2.setStatus(Status.INPROGRESS);
        taskDTO2.setProjectId(testProjectDTO.getId());
        taskDTO2.setUserId(testUserDTO.getId());

        taskService.createEntity(taskDTO2.getId(), taskDTO2);

        Assert.assertEquals(taskService.findAll(sessionDTO.getUserId()).size(), 2);

        @NotNull final TaskDTO taskDTOFromServer = taskService.findEntity(taskDTO.getId(), sessionDTO.getUserId());
        Assert.assertEquals(taskDTOFromServer.getId(), taskDTO.getId());
        Assert.assertEquals(taskDTOFromServer.getName(), taskDTO.getName());
        Assert.assertEquals(taskDTOFromServer.getDescription(), taskDTO.getDescription());
        Assert.assertEquals(ConverterUtil.convertDateFormat(taskDTOFromServer.getDateStart()), ConverterUtil.convertDateFormat(taskDTO.getDateStart()));
        Assert.assertEquals(ConverterUtil.convertDateFormat(taskDTOFromServer.getDateFinish()), ConverterUtil.convertDateFormat(taskDTO.getDateFinish()));
        Assert.assertEquals(ConverterUtil.convertDateFormat(taskDTOFromServer.getDateCreate()), ConverterUtil.convertDateFormat(taskDTO.getDateCreate()));
        Assert.assertEquals(taskDTOFromServer.getStatus(), taskDTO.getStatus());
        Assert.assertEquals(taskDTOFromServer.getProjectId(), taskDTO.getProjectId());
        Assert.assertEquals(taskDTOFromServer.getUserId(), taskDTO.getUserId());

        @NotNull final TaskDTO taskDTOFromServer2 = taskService.findEntity(taskDTO2.getId(), sessionDTO.getUserId());
        Assert.assertEquals(taskDTOFromServer2.getId(), taskDTO2.getId());
        Assert.assertEquals(taskDTOFromServer2.getName(), taskDTO2.getName());
        Assert.assertEquals(taskDTOFromServer2.getDescription(), taskDTO2.getDescription());
        Assert.assertEquals(ConverterUtil.convertDateFormat(taskDTOFromServer2.getDateStart()), ConverterUtil.convertDateFormat(taskDTO2.getDateStart()));
        Assert.assertEquals(ConverterUtil.convertDateFormat(taskDTOFromServer2.getDateFinish()), ConverterUtil.convertDateFormat(taskDTO2.getDateFinish()));
        Assert.assertEquals(ConverterUtil.convertDateFormat(taskDTOFromServer2.getDateCreate()), ConverterUtil.convertDateFormat(taskDTO2.getDateCreate()));
        Assert.assertEquals(taskDTOFromServer2.getStatus(), taskDTO2.getStatus());
        Assert.assertEquals(taskDTOFromServer2.getProjectId(), taskDTO.getProjectId());
        Assert.assertEquals(taskDTOFromServer2.getUserId(), taskDTO2.getUserId());
    }

    @Test
    public void editEntity() {
        @NotNull final TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(UUID.randomUUID().toString());
        taskDTO.setName("TaskNameTest");
        taskDTO.setDescription("TaskDescriptionTest");
        taskDTO.setDateCreate(new Date());
        taskDTO.setDateStart(ConverterUtil.convertFromStringToDate("01.01.2000"));
        taskDTO.setDateFinish(ConverterUtil.convertFromStringToDate("02.02.2002"));
        taskDTO.setStatus(Status.INPROGRESS);
        taskDTO.setProjectId(testProjectDTO.getId());
        taskDTO.setUserId(testUserDTO.getId());

        taskService.createEntity(taskDTO.getId(), taskDTO);

        @NotNull final TaskDTO editTaskDTO = taskDTO;
        editTaskDTO.setName("ProjectEditNameTest");
        editTaskDTO.setDescription("ProjectEditDescriptionTest");
        editTaskDTO.setDateStart(ConverterUtil.convertFromStringToDate("11.11.2100"));
        editTaskDTO.setDateFinish(ConverterUtil.convertFromStringToDate("12.12.2102"));
        editTaskDTO.setStatus(Status.DONE);

        taskService.editEntity(taskDTO.getId(), editTaskDTO, sessionDTO.getUserId());

        @NotNull final TaskDTO taskDTOFromServer = taskService.findEntity(taskDTO.getId(), sessionDTO.getUserId());

        Assert.assertEquals(taskDTOFromServer.getId(), editTaskDTO.getId());
        Assert.assertEquals(taskDTOFromServer.getName(), editTaskDTO.getName());
        Assert.assertEquals(taskDTOFromServer.getDescription(), editTaskDTO.getDescription());
        Assert.assertEquals(ConverterUtil.convertDateFormat(taskDTOFromServer.getDateStart()), ConverterUtil.convertDateFormat(editTaskDTO.getDateStart()));
        Assert.assertEquals(ConverterUtil.convertDateFormat(taskDTOFromServer.getDateFinish()), ConverterUtil.convertDateFormat(editTaskDTO.getDateFinish()));
        Assert.assertEquals(ConverterUtil.convertDateFormat(taskDTOFromServer.getDateCreate()), ConverterUtil.convertDateFormat(editTaskDTO.getDateCreate()));
        Assert.assertEquals(taskDTOFromServer.getStatus(), editTaskDTO.getStatus());
        Assert.assertEquals(taskDTOFromServer.getUserId(), editTaskDTO.getUserId());
    }

    @Test
    public void findAllName() {
        for (int i = 0; i < 30; i++) {
            @NotNull final TaskDTO taskDTO = new TaskDTO();
            taskDTO.setId(UUID.randomUUID().toString());
            taskDTO.setName("TaskNameTest" + i);
            taskDTO.setDescription("TaskDescriptionTest");
            taskDTO.setDateCreate(new Date());
            taskDTO.setDateStart(ConverterUtil.convertFromStringToDate("01.01.2000"));
            taskDTO.setDateFinish(ConverterUtil.convertFromStringToDate("02.02.2002"));
            taskDTO.setStatus(Status.INPROGRESS);
            taskDTO.setProjectId(testProjectDTO.getId());
            taskDTO.setUserId(testUserDTO.getId());
            taskService.createEntity(taskDTO.getId(), taskDTO);
        }

        @NotNull final List<TaskDTO> listTaskDTOContainsName = new ArrayList<>(taskService.findAllName("TaskNameTest1", sessionDTO.getUserId()));
        Assert.assertEquals(listTaskDTOContainsName.size(), 11);
    }

    @Test
    public void findAllDescription() {
        for (int i = 0; i < 31; i++) {
            @NotNull final TaskDTO taskDTO = new TaskDTO();
            taskDTO.setId(UUID.randomUUID().toString());
            taskDTO.setName("TaskNameTest");
            taskDTO.setDescription("TaskDescriptionTest" + i);
            taskDTO.setDateCreate(new Date());
            taskDTO.setDateStart(ConverterUtil.convertFromStringToDate("01.01.2000"));
            taskDTO.setDateFinish(ConverterUtil.convertFromStringToDate("02.02.2002"));
            taskDTO.setStatus(Status.INPROGRESS);
            taskDTO.setProjectId(testProjectDTO.getId());
            taskDTO.setUserId(testUserDTO.getId());
            taskService.createEntity(taskDTO.getId(), taskDTO);
        }

        @NotNull final List<TaskDTO> listTaskDTOContainsDescription = new ArrayList<>(taskService.findAllDescription("TaskDescriptionTest3", sessionDTO.getUserId()));
        Assert.assertEquals(listTaskDTOContainsDescription.size(), 2);
    }

    @Test
    public void removeEntity() {
        @NotNull final TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(UUID.randomUUID().toString());
        taskDTO.setName("TaskNameTest");
        taskDTO.setDescription("TaskDescriptionTest");
        taskDTO.setDateCreate(new Date());
        taskDTO.setDateStart(ConverterUtil.convertFromStringToDate("01.01.2000"));
        taskDTO.setDateFinish(ConverterUtil.convertFromStringToDate("02.02.2002"));
        taskDTO.setStatus(Status.INPROGRESS);
        taskDTO.setProjectId(testProjectDTO.getId());
        taskDTO.setUserId(testUserDTO.getId());

        taskService.createEntity(taskDTO.getId(), taskDTO);

        taskService.removeEntity(taskDTO.getId(), sessionDTO.getUserId());

        Assert.assertEquals(taskService.findAll(sessionDTO.getUserId()).size(), 0);
    }

    @Test
    public void clearEntity() {
        @NotNull final TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(UUID.randomUUID().toString());
        taskDTO.setName("TaskNameTest");
        taskDTO.setDescription("TaskDescriptionTest");
        taskDTO.setDateCreate(new Date());
        taskDTO.setDateStart(ConverterUtil.convertFromStringToDate("01.01.2000"));
        taskDTO.setDateFinish(ConverterUtil.convertFromStringToDate("02.02.2002"));
        taskDTO.setStatus(Status.INPROGRESS);
        taskDTO.setProjectId(testProjectDTO.getId());
        taskDTO.setUserId(testUserDTO.getId());

        taskService.createEntity(taskDTO.getId(), taskDTO);

        @NotNull final TaskDTO taskDTO2 = new TaskDTO();
        taskDTO2.setId(UUID.randomUUID().toString());
        taskDTO2.setName("TaskNameTest2");
        taskDTO2.setDescription("TaskDescriptionTest2");
        taskDTO2.setDateCreate(new Date());
        taskDTO2.setDateStart(ConverterUtil.convertFromStringToDate("02.02.2002"));
        taskDTO2.setDateFinish(ConverterUtil.convertFromStringToDate("03.03.2003"));
        taskDTO2.setStatus(Status.INPROGRESS);
        taskDTO2.setProjectId(testProjectDTO.getId());
        taskDTO2.setUserId(testUserDTO.getId());

        taskService.createEntity(taskDTO2.getId(), taskDTO2);

        taskService.clearEntity(sessionDTO.getUserId());

        Assert.assertEquals(taskService.findAll(sessionDTO.getUserId()).size(), 0);
    }

    @Test
    public void listTaskFromProject() {
        @NotNull final TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(UUID.randomUUID().toString());
        taskDTO.setName("TaskNameTest");
        taskDTO.setDescription("TaskDescriptionTest");
        taskDTO.setDateCreate(new Date());
        taskDTO.setDateStart(ConverterUtil.convertFromStringToDate("01.01.2000"));
        taskDTO.setDateFinish(ConverterUtil.convertFromStringToDate("02.02.2002"));
        taskDTO.setStatus(Status.INPROGRESS);
        taskDTO.setProjectId(testProjectDTO.getId());
        taskDTO.setUserId(testUserDTO.getId());

        taskService.createEntity(taskDTO.getId(), taskDTO);

        @NotNull final TaskDTO taskDTO2 = new TaskDTO();
        taskDTO2.setId(UUID.randomUUID().toString());
        taskDTO2.setName("TaskNameTest2");
        taskDTO2.setDescription("TaskDescriptionTest2");
        taskDTO2.setDateCreate(new Date());
        taskDTO2.setDateStart(ConverterUtil.convertFromStringToDate("02.02.2002"));
        taskDTO2.setDateFinish(ConverterUtil.convertFromStringToDate("03.03.2003"));
        taskDTO2.setStatus(Status.INPROGRESS);
        taskDTO2.setProjectId(testProjectDTO.getId());
        taskDTO2.setUserId(testUserDTO.getId());

        taskService.createEntity(taskDTO2.getId(), taskDTO2);

        Assert.assertEquals(taskService.listTaskFromProject(taskDTO.getProjectId(), sessionDTO.getUserId()).size(), 2);
    }

    @After
    public void deleteTestEntity() {
        userService.removeEntity(testUserDTO.getId());
    }
}