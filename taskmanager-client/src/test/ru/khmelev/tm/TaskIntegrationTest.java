import org.jetbrains.annotations.NotNull;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import ru.khmelev.tm.api.endpoint.*;
import ru.khmelev.tm.endpoint.EndpointService;
import ru.khmelev.tm.util.PasswordHashUtil;
import ru.khmelev.tm.util.Printer;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Category(ITaskIntegrationTest.class)
public class TaskIntegrationTest {

    private IProjectEndpoint projectEndpoint = new EndpointService().getProjectEndpointService();

    private ITaskEndpoint taskEndpoint = new EndpointService().getTaskEndpointService();

    private IUserEndpoint userEndpoint = new EndpointService().getUserEndpointService();

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
        projectDTO.setDateCreate(Printer.printXMLDate(new Date()));
        projectDTO.setDateStart(Printer.printXMLDate(Printer.parse("01.01.2000")));
        projectDTO.setDateFinish(Printer.printXMLDate(Printer.parse("02.02.2002")));
        projectDTO.setStatus(Status.INPROGRESS);
        projectDTO.setUserId(testUserDTO.getId());

        testProjectDTO = projectDTO;
        projectEndpoint.createProject(sessionDTO, projectDTO.getId(), projectDTO);
    }

    @Test
    public void createAndFindTask() {
        @NotNull final TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(UUID.randomUUID().toString());
        taskDTO.setName("ProjectNameTest");
        taskDTO.setDescription("ProjectDescriptionTest");
        taskDTO.setDateCreate(Printer.printXMLDate(new Date()));
        taskDTO.setDateStart(Printer.printXMLDate(Printer.parse("01.01.2000")));
        taskDTO.setDateFinish(Printer.printXMLDate(Printer.parse("02.02.2002")));
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

    @After
    public void deleteEntity() {
        userEndpoint.removeUser(sessionDTO, testUserDTO.getId());
    }
}