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

@Category(IProjectIntegrationTest.class)
public class ProjectIntegrationTest {

    private IProjectEndpoint projectEndpoint = new EndpointService().getProjectEndpointService();

    private IUserEndpoint userEndpoint = new EndpointService().getUserEndpointService();

    private SessionDTO sessionDTO;

    private UserDTO testUserDTO;

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
    }

    @Test
    public void createAndFindProject() {
        @NotNull final ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setId(UUID.randomUUID().toString());
        projectDTO.setName("ProjectNameTest");
        projectDTO.setDescription("ProjectDescriptionTest");
        projectDTO.setDateCreate(Printer.printXMLDate(new Date()));
        projectDTO.setDateStart(Printer.printXMLDate(Printer.parse("01.01.2000")));
        projectDTO.setDateFinish(Printer.printXMLDate(Printer.parse("02.02.2002")));
        projectDTO.setStatus(Status.INPROGRESS);
        projectDTO.setUserId(testUserDTO.getId());

        projectEndpoint.createProject(sessionDTO, projectDTO.getId(), projectDTO);

        @NotNull final ProjectDTO projectDTOfromServer = projectEndpoint.findProject(sessionDTO, projectDTO.getId());
        Assert.assertEquals(projectDTOfromServer.getId(), projectDTO.getId());
        Assert.assertEquals(projectDTOfromServer.getName(), projectDTO.getName());
        Assert.assertEquals(projectDTOfromServer.getDescription(), projectDTO.getDescription());
        Assert.assertEquals(projectDTOfromServer.getDateStart(), projectDTO.getDateStart());
        Assert.assertEquals(projectDTOfromServer.getDateFinish(), projectDTO.getDateFinish());
        Assert.assertEquals(projectDTOfromServer.getDateCreate(), projectDTO.getDateCreate());
        Assert.assertEquals(projectDTOfromServer.getStatus(), projectDTO.getStatus());
        Assert.assertEquals(projectDTOfromServer.getUserId(), projectDTO.getUserId());
    }

    @Test
    public void findAllProject() {
        @NotNull final ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setId(UUID.randomUUID().toString());
        projectDTO.setName("ProjectNameTest");
        projectDTO.setDescription("ProjectDescriptionTest");
        projectDTO.setDateCreate(Printer.printXMLDate(new Date()));
        projectDTO.setDateStart(Printer.printXMLDate(Printer.parse("01.01.2000")));
        projectDTO.setDateFinish(Printer.printXMLDate(Printer.parse("02.02.2002")));
        projectDTO.setStatus(Status.INPROGRESS);
        projectDTO.setUserId(testUserDTO.getId());

        projectEndpoint.createProject(sessionDTO, projectDTO.getId(), projectDTO);

        @NotNull final ProjectDTO projectDTO2 = new ProjectDTO();
        projectDTO2.setId(UUID.randomUUID().toString());
        projectDTO2.setName("ProjectNameTest2");
        projectDTO2.setDescription("ProjectDescriptionTest2");
        projectDTO2.setDateCreate(Printer.printXMLDate(new Date()));
        projectDTO2.setDateStart(Printer.printXMLDate(Printer.parse("02.02.2002")));
        projectDTO2.setDateFinish(Printer.printXMLDate(Printer.parse("03.03.2003")));
        projectDTO2.setStatus(Status.INPROGRESS);
        projectDTO2.setUserId(testUserDTO.getId());

        projectEndpoint.createProject(sessionDTO, projectDTO2.getId(), projectDTO2);

        Assert.assertEquals(projectEndpoint.findAllProject(sessionDTO).size(), 2);

        @NotNull final ProjectDTO projectDTOfromServer = projectEndpoint.findProject(sessionDTO, projectDTO.getId());
        Assert.assertEquals(projectDTOfromServer.getId(), projectDTO.getId());
        Assert.assertEquals(projectDTOfromServer.getName(), projectDTO.getName());
        Assert.assertEquals(projectDTOfromServer.getDescription(), projectDTO.getDescription());
        Assert.assertEquals(projectDTOfromServer.getDateStart(), projectDTO.getDateStart());
        Assert.assertEquals(projectDTOfromServer.getDateFinish(), projectDTO.getDateFinish());
        Assert.assertEquals(projectDTOfromServer.getDateCreate(), projectDTO.getDateCreate());
        Assert.assertEquals(projectDTOfromServer.getStatus(), projectDTO.getStatus());
        Assert.assertEquals(projectDTOfromServer.getUserId(), projectDTO.getUserId());

        @NotNull final ProjectDTO projectDTOfromServer2 = projectEndpoint.findProject(sessionDTO, projectDTO2.getId());
        Assert.assertEquals(projectDTOfromServer2.getId(), projectDTO2.getId());
        Assert.assertEquals(projectDTOfromServer2.getName(), projectDTO2.getName());
        Assert.assertEquals(projectDTOfromServer2.getDescription(), projectDTO2.getDescription());
        Assert.assertEquals(projectDTOfromServer2.getDateStart(), projectDTO2.getDateStart());
        Assert.assertEquals(projectDTOfromServer2.getDateFinish(), projectDTO2.getDateFinish());
        Assert.assertEquals(projectDTOfromServer2.getDateCreate(), projectDTO2.getDateCreate());
        Assert.assertEquals(projectDTOfromServer2.getStatus(), projectDTO2.getStatus());
        Assert.assertEquals(projectDTOfromServer2.getUserId(), projectDTO2.getUserId());
    }

    @Test
    public void editProject() {
        @NotNull final ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setId(UUID.randomUUID().toString());
        projectDTO.setName("ProjectNameTest");
        projectDTO.setDescription("ProjectDescriptionTest");
        projectDTO.setDateCreate(Printer.printXMLDate(new Date()));
        projectDTO.setDateStart(Printer.printXMLDate(Printer.parse("01.01.2000")));
        projectDTO.setDateFinish(Printer.printXMLDate(Printer.parse("02.02.2002")));
        projectDTO.setStatus(Status.INPROGRESS);
        projectDTO.setUserId(testUserDTO.getId());

        projectEndpoint.createProject(sessionDTO, projectDTO.getId(), projectDTO);

        @NotNull final ProjectDTO editProjectDTO = projectDTO;
        projectDTO.setName("ProjectEditNameTest");
        projectDTO.setDescription("ProjectEditDescriptionTest");
        projectDTO.setDateStart(Printer.printXMLDate(Printer.parse("11.11.2100")));
        projectDTO.setDateFinish(Printer.printXMLDate(Printer.parse("12.12.2102")));
        projectDTO.setStatus(Status.DONE);

        projectEndpoint.editProject(sessionDTO, projectDTO.getId(), editProjectDTO);

        @NotNull final ProjectDTO projectDTOfromServer = projectEndpoint.findProject(sessionDTO, editProjectDTO.getId());

        Assert.assertEquals(projectDTOfromServer.getId(), editProjectDTO.getId());
        Assert.assertEquals(projectDTOfromServer.getName(), editProjectDTO.getName());
        Assert.assertEquals(projectDTOfromServer.getDescription(), editProjectDTO.getDescription());
        Assert.assertEquals(projectDTOfromServer.getDateStart(), editProjectDTO.getDateStart());
        Assert.assertEquals(projectDTOfromServer.getDateFinish(), editProjectDTO.getDateFinish());
        Assert.assertEquals(projectDTOfromServer.getDateCreate(), editProjectDTO.getDateCreate());
        Assert.assertEquals(projectDTOfromServer.getStatus(), editProjectDTO.getStatus());
        Assert.assertEquals(projectDTOfromServer.getUserId(), editProjectDTO.getUserId());
    }

    @Test
    public void removeProject() {
        @NotNull final ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setId(UUID.randomUUID().toString());
        projectDTO.setName("ProjectNameTest");
        projectDTO.setDescription("ProjectDescriptionTest");
        projectDTO.setDateCreate(Printer.printXMLDate(new Date()));
        projectDTO.setDateStart(Printer.printXMLDate(Printer.parse("01.01.2000")));
        projectDTO.setDateFinish(Printer.printXMLDate(Printer.parse("02.02.2002")));
        projectDTO.setStatus(Status.INPROGRESS);
        projectDTO.setUserId(testUserDTO.getId());

        projectEndpoint.createProject(sessionDTO, projectDTO.getId(), projectDTO);

        projectEndpoint.removeProject(sessionDTO, projectDTO.getId());

        Assert.assertEquals(projectEndpoint.findAllProject(sessionDTO).size(), 0);
    }

    @Test
    public void clearProject() {
        @NotNull final ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setId(UUID.randomUUID().toString());
        projectDTO.setName("ProjectNameTest");
        projectDTO.setDescription("ProjectDescriptionTest");
        projectDTO.setDateCreate(Printer.printXMLDate(new Date()));
        projectDTO.setDateStart(Printer.printXMLDate(Printer.parse("01.01.2000")));
        projectDTO.setDateFinish(Printer.printXMLDate(Printer.parse("02.02.2002")));
        projectDTO.setStatus(Status.INPROGRESS);
        projectDTO.setUserId(testUserDTO.getId());

        projectEndpoint.createProject(sessionDTO, projectDTO.getId(), projectDTO);

        @NotNull final ProjectDTO projectDTO2 = new ProjectDTO();
        projectDTO2.setId(UUID.randomUUID().toString());
        projectDTO2.setName("ProjectNameTest2");
        projectDTO2.setDescription("ProjectDescriptionTest2");
        projectDTO2.setDateCreate(Printer.printXMLDate(new Date()));
        projectDTO2.setDateStart(Printer.printXMLDate(Printer.parse("02.02.2002")));
        projectDTO2.setDateFinish(Printer.printXMLDate(Printer.parse("03.03.2003")));
        projectDTO2.setStatus(Status.INPROGRESS);
        projectDTO2.setUserId(testUserDTO.getId());

        projectEndpoint.createProject(sessionDTO, projectDTO2.getId(), projectDTO2);

        projectEndpoint.clearProject(sessionDTO);

        Assert.assertEquals(projectEndpoint.findAllProject(sessionDTO).size(), 0);
    }

    @After
    public void deleteEntity() {
        userEndpoint.removeUser(sessionDTO, testUserDTO.getId());
    }
}