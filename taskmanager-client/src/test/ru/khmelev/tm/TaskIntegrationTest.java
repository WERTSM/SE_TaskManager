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
import ru.khmelev.tm.api.endpoint.*;
import ru.khmelev.tm.util.ConverterUtil;
import ru.khmelev.tm.util.PasswordHashUtil;
import ru.khmelev.tm.util.SpringJPAConfigUtil;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Category(ITaskIntegrationTest.class)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringJPAConfigUtil.class)
public class TaskIntegrationTest {

    @Autowired
    private IProjectEndpoint projectEndpoint;

    @Autowired
    private ITaskEndpoint taskEndpoint;

    @Autowired
    private IUserEndpoint userEndpoint;

    private SessionDTO sessionDTO;

    private UserDTO testUserDTO;

    private ProjectDTO testProjectDTO;

    @Before
    public void createEntity() {
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
        projectDTO.setDateCreate(ConverterUtil.convertFromDateToXMLDate(new Date()));
        projectDTO.setDateStart(ConverterUtil.convertFromDateToXMLDate(Objects.requireNonNull(ConverterUtil.convertFromStringToDate("01.01.2000"))));
        projectDTO.setDateFinish(ConverterUtil.convertFromDateToXMLDate(Objects.requireNonNull(ConverterUtil.convertFromStringToDate("02.02.2002"))));
        projectDTO.setStatus(Status.INPROGRESS);
        projectDTO.setUserId(testUserDTO.getId());

        testProjectDTO = projectDTO;
        projectEndpoint.createProject(sessionDTO, projectDTO.getId(), projectDTO);
    }

    @Test
    public void createAndFindTask() {
        @NotNull final TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(UUID.randomUUID().toString());
        taskDTO.setName("TaskNameTest");
        taskDTO.setDescription("TaskDescriptionTest");
        taskDTO.setDateCreate(ConverterUtil.convertFromDateToXMLDate(new Date()));
        taskDTO.setDateStart(ConverterUtil.convertFromDateToXMLDate(Objects.requireNonNull(ConverterUtil.convertFromStringToDate("01.01.2000"))));
        taskDTO.setDateFinish(ConverterUtil.convertFromDateToXMLDate(Objects.requireNonNull(ConverterUtil.convertFromStringToDate("02.02.2002"))));
        taskDTO.setStatus(Status.INPROGRESS);
        taskDTO.setProjectId(testProjectDTO.getId());
        taskDTO.setUserId(testUserDTO.getId());

        taskEndpoint.createTask(sessionDTO, taskDTO.getId(), taskDTO);

        @NotNull final TaskDTO taskDTOFromServer = taskEndpoint.findTask(sessionDTO, taskDTO.getId());
        Assert.assertEquals(taskDTOFromServer.getId(), taskDTO.getId());
        Assert.assertEquals(taskDTOFromServer.getName(), taskDTO.getName());
        Assert.assertEquals(taskDTOFromServer.getDescription(), taskDTO.getDescription());
        Assert.assertEquals(taskDTOFromServer.getDateStart(), taskDTO.getDateStart());
        Assert.assertEquals(taskDTOFromServer.getDateFinish(), taskDTO.getDateFinish());
        Assert.assertEquals(taskDTOFromServer.getDateCreate(), taskDTO.getDateCreate());
        Assert.assertEquals(taskDTOFromServer.getStatus(), taskDTO.getStatus());
        Assert.assertEquals(taskDTOFromServer.getProjectId(), taskDTO.getProjectId());
        Assert.assertEquals(taskDTOFromServer.getUserId(), taskDTO.getUserId());
    }

    @Test
    public void findAllTask() {
        @NotNull final TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(UUID.randomUUID().toString());
        taskDTO.setName("TaskNameTest");
        taskDTO.setDescription("TaskDescriptionTest");
        taskDTO.setDateCreate(ConverterUtil.convertFromDateToXMLDate(new Date()));
        taskDTO.setDateStart(ConverterUtil.convertFromDateToXMLDate(Objects.requireNonNull(ConverterUtil.convertFromStringToDate("01.01.2000"))));
        taskDTO.setDateFinish(ConverterUtil.convertFromDateToXMLDate(Objects.requireNonNull(ConverterUtil.convertFromStringToDate("02.02.2002"))));
        taskDTO.setStatus(Status.INPROGRESS);
        taskDTO.setProjectId(testProjectDTO.getId());
        taskDTO.setUserId(testUserDTO.getId());

        taskEndpoint.createTask(sessionDTO, taskDTO.getId(), taskDTO);

        @NotNull final TaskDTO taskDTO2 = new TaskDTO();
        taskDTO2.setId(UUID.randomUUID().toString());
        taskDTO2.setName("TaskNameTest2");
        taskDTO2.setDescription("TaskDescriptionTest2");
        taskDTO2.setDateCreate(ConverterUtil.convertFromDateToXMLDate(new Date()));
        taskDTO2.setDateStart(ConverterUtil.convertFromDateToXMLDate(Objects.requireNonNull(ConverterUtil.convertFromStringToDate("02.02.2002"))));
        taskDTO2.setDateFinish(ConverterUtil.convertFromDateToXMLDate(Objects.requireNonNull(ConverterUtil.convertFromStringToDate("03.03.2003"))));
        taskDTO2.setStatus(Status.INPROGRESS);
        taskDTO2.setProjectId(testProjectDTO.getId());
        taskDTO2.setUserId(testUserDTO.getId());

        taskEndpoint.createTask(sessionDTO, taskDTO2.getId(), taskDTO2);

        Assert.assertEquals(taskEndpoint.findAllTAsk(sessionDTO).size(), 2);

        @NotNull final TaskDTO taskDTOFromServer = taskEndpoint.findTask(sessionDTO, taskDTO.getId());
        Assert.assertEquals(taskDTOFromServer.getId(), taskDTO.getId());
        Assert.assertEquals(taskDTOFromServer.getName(), taskDTO.getName());
        Assert.assertEquals(taskDTOFromServer.getDescription(), taskDTO.getDescription());
        Assert.assertEquals(taskDTOFromServer.getDateStart(), taskDTO.getDateStart());
        Assert.assertEquals(taskDTOFromServer.getDateFinish(), taskDTO.getDateFinish());
        Assert.assertEquals(taskDTOFromServer.getDateCreate(), taskDTO.getDateCreate());
        Assert.assertEquals(taskDTOFromServer.getStatus(), taskDTO.getStatus());
        Assert.assertEquals(taskDTOFromServer.getProjectId(), taskDTO.getProjectId());
        Assert.assertEquals(taskDTOFromServer.getUserId(), taskDTO.getUserId());

        @NotNull final TaskDTO taskDTOFromServer2 = taskEndpoint.findTask(sessionDTO, taskDTO2.getId());
        Assert.assertEquals(taskDTOFromServer2.getId(), taskDTO2.getId());
        Assert.assertEquals(taskDTOFromServer2.getName(), taskDTO2.getName());
        Assert.assertEquals(taskDTOFromServer2.getDescription(), taskDTO2.getDescription());
        Assert.assertEquals(taskDTOFromServer2.getDateStart(), taskDTO2.getDateStart());
        Assert.assertEquals(taskDTOFromServer2.getDateFinish(), taskDTO2.getDateFinish());
        Assert.assertEquals(taskDTOFromServer2.getDateCreate(), taskDTO2.getDateCreate());
        Assert.assertEquals(taskDTOFromServer2.getStatus(), taskDTO2.getStatus());
        Assert.assertEquals(taskDTOFromServer2.getProjectId(), taskDTO.getProjectId());
        Assert.assertEquals(taskDTOFromServer2.getUserId(), taskDTO2.getUserId());
    }

    @Test
    public void editTask() {
        @NotNull final TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(UUID.randomUUID().toString());
        taskDTO.setName("TaskNameTest");
        taskDTO.setDescription("TaskDescriptionTest");
        taskDTO.setDateCreate(ConverterUtil.convertFromDateToXMLDate(new Date()));
        taskDTO.setDateStart(ConverterUtil.convertFromDateToXMLDate(Objects.requireNonNull(ConverterUtil.convertFromStringToDate("01.01.2000"))));
        taskDTO.setDateFinish(ConverterUtil.convertFromDateToXMLDate(Objects.requireNonNull(ConverterUtil.convertFromStringToDate("02.02.2002"))));
        taskDTO.setStatus(Status.INPROGRESS);
        taskDTO.setProjectId(testProjectDTO.getId());
        taskDTO.setUserId(testUserDTO.getId());

        taskEndpoint.createTask(sessionDTO, taskDTO.getId(), taskDTO);

        @NotNull final TaskDTO editTaskDTO = taskDTO;
        editTaskDTO.setName("ProjectEditNameTest");
        editTaskDTO.setDescription("ProjectEditDescriptionTest");
        editTaskDTO.setDateStart(ConverterUtil.convertFromDateToXMLDate(Objects.requireNonNull(ConverterUtil.convertFromStringToDate("11.11.2100"))));
        editTaskDTO.setDateFinish(ConverterUtil.convertFromDateToXMLDate(Objects.requireNonNull(ConverterUtil.convertFromStringToDate("12.12.2102"))));
        editTaskDTO.setStatus(Status.DONE);

        taskEndpoint.editTask(sessionDTO, taskDTO.getId(), editTaskDTO);

        @NotNull final TaskDTO taskDTOFromServer = taskEndpoint.findTask(sessionDTO, taskDTO.getId());

        Assert.assertEquals(taskDTOFromServer.getId(), editTaskDTO.getId());
        Assert.assertEquals(taskDTOFromServer.getName(), editTaskDTO.getName());
        Assert.assertEquals(taskDTOFromServer.getDescription(), editTaskDTO.getDescription());
        Assert.assertEquals(taskDTOFromServer.getDateStart(), editTaskDTO.getDateStart());
        Assert.assertEquals(taskDTOFromServer.getDateFinish(), editTaskDTO.getDateFinish());
        Assert.assertEquals(taskDTOFromServer.getDateCreate(), editTaskDTO.getDateCreate());
        Assert.assertEquals(taskDTOFromServer.getStatus(), editTaskDTO.getStatus());
        Assert.assertEquals(taskDTOFromServer.getUserId(), editTaskDTO.getUserId());
    }

    @Test
    public void removeTask() {
        @NotNull final TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(UUID.randomUUID().toString());
        taskDTO.setName("TaskNameTest");
        taskDTO.setDescription("TaskDescriptionTest");
        taskDTO.setDateCreate(ConverterUtil.convertFromDateToXMLDate(new Date()));
        taskDTO.setDateStart(ConverterUtil.convertFromDateToXMLDate(Objects.requireNonNull(ConverterUtil.convertFromStringToDate("01.01.2000"))));
        taskDTO.setDateFinish(ConverterUtil.convertFromDateToXMLDate(Objects.requireNonNull(ConverterUtil.convertFromStringToDate("02.02.2002"))));
        taskDTO.setStatus(Status.INPROGRESS);
        taskDTO.setProjectId(testProjectDTO.getId());
        taskDTO.setUserId(testUserDTO.getId());

        taskEndpoint.createTask(sessionDTO, taskDTO.getId(), taskDTO);

        taskEndpoint.removeTask(sessionDTO, taskDTO.getId());

        Assert.assertEquals(taskEndpoint.findAllTAsk(sessionDTO).size(), 0);
    }

    @Test
    public void clearTask() {
        @NotNull final TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(UUID.randomUUID().toString());
        taskDTO.setName("TaskNameTest");
        taskDTO.setDescription("TaskDescriptionTest");
        taskDTO.setDateCreate(ConverterUtil.convertFromDateToXMLDate(new Date()));
        taskDTO.setDateStart(ConverterUtil.convertFromDateToXMLDate(Objects.requireNonNull(ConverterUtil.convertFromStringToDate("01.01.2000"))));
        taskDTO.setDateFinish(ConverterUtil.convertFromDateToXMLDate(Objects.requireNonNull(ConverterUtil.convertFromStringToDate("02.02.2002"))));
        taskDTO.setStatus(Status.INPROGRESS);
        taskDTO.setProjectId(testProjectDTO.getId());
        taskDTO.setUserId(testUserDTO.getId());

        taskEndpoint.createTask(sessionDTO, taskDTO.getId(), taskDTO);

        @NotNull final TaskDTO taskDTO2 = new TaskDTO();
        taskDTO2.setId(UUID.randomUUID().toString());
        taskDTO2.setName("TaskNameTest2");
        taskDTO2.setDescription("TaskDescriptionTest2");
        taskDTO2.setDateCreate(ConverterUtil.convertFromDateToXMLDate(new Date()));
        taskDTO2.setDateStart(ConverterUtil.convertFromDateToXMLDate(Objects.requireNonNull(ConverterUtil.convertFromStringToDate("02.02.2002"))));
        taskDTO2.setDateFinish(ConverterUtil.convertFromDateToXMLDate(Objects.requireNonNull(ConverterUtil.convertFromStringToDate("03.03.2003"))));
        taskDTO2.setStatus(Status.INPROGRESS);
        taskDTO2.setProjectId(testProjectDTO.getId());
        taskDTO2.setUserId(testUserDTO.getId());

        taskEndpoint.createTask(sessionDTO, taskDTO2.getId(), taskDTO2);

        taskEndpoint.clearTask(sessionDTO);

        Assert.assertEquals(taskEndpoint.findAllTAsk(sessionDTO).size(), 0);
    }

    @After
    public void deleteEntity() {
        userEndpoint.removeUser(sessionDTO, testUserDTO.getId());
    }
}