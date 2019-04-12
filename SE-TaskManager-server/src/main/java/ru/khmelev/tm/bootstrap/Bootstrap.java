package ru.khmelev.tm.bootstrap;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.api.endpoint.ISaveAndLoadEndpoint;
import ru.khmelev.tm.api.endpoint.IProjectEndpoint;
import ru.khmelev.tm.api.endpoint.ITaskEndpoint;
import ru.khmelev.tm.api.endpoint.IUserEndpoint;
import ru.khmelev.tm.api.repository.IProjectRepository;
import ru.khmelev.tm.api.repository.ISessionRepository;
import ru.khmelev.tm.api.repository.ITaskRepository;
import ru.khmelev.tm.api.repository.IUserRepository;
import ru.khmelev.tm.api.service.*;
import ru.khmelev.tm.endpoint.*;
import ru.khmelev.tm.entity.*;
import ru.khmelev.tm.repository.ProjectRepository;
import ru.khmelev.tm.repository.SessionRepository;
import ru.khmelev.tm.repository.TaskRepository;
import ru.khmelev.tm.repository.UserRepository;
import ru.khmelev.tm.service.ProjectService;
import ru.khmelev.tm.service.SessionService;
import ru.khmelev.tm.service.TaskService;
import ru.khmelev.tm.service.UserService;

import javax.xml.ws.Endpoint;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public final class Bootstrap implements ServiceLocator {

    @NotNull
    private final IProjectRepository projectRepository = new ProjectRepository();

    @NotNull
    private final ITaskRepository taskRepository = new TaskRepository();

    @NotNull
    private final IUserRepository userRepository = new UserRepository();

    @Getter
    @NotNull
    private final ISessionRepository sessionRepository = new SessionRepository();

    @Getter
    @NotNull
    private final ITaskService taskService = new TaskService(taskRepository);

    @Getter
    @NotNull
    private final IProjectService projectService = new ProjectService(projectRepository, taskService);

    @Getter
    @NotNull
    private final IUserService userService = new UserService(userRepository);

    @Getter
    @NotNull
    private final ISessionService sessionService = new SessionService(sessionRepository);

    @Getter
    @NotNull
    private final IProjectEndpoint projectEndpoint = new ProjectEndpoint(sessionService, projectService);

    @Getter
    @NotNull
    private final ITaskEndpoint taskEndpoint = new TaskEndpoint(sessionService, taskService);

    @Getter
    @NotNull
    private final IUserEndpoint userEndpoint = new UserEndpoint(sessionService, userService, this);

    @Getter
    @NotNull
    private final ISaveAndLoadEndpoint saveAndLoadEndpoint = new SaveAndLoadEndpoint(projectService, taskService, userService, sessionService);


    @Getter
    @NotNull
    private final ITerminalService terminalService = new TerminalEndpoint();

    public void init() {
/*
        for (Project pr : projectRepository.findAll("f1c46dbd-d06b-4784-893c-3b67b0e9da90")) {
            System.out.println(pr.getName());
        }

        System.out.println(projectRepository.findOne("28cfa359-521d-4141-aa57-f81d5c7c0d39", "f1c46dbd-d06b-4784-893c-3b67b0e9da90").getName());
*/
        User user = new User();
        user.setId("28cfa359-521d-4141-aa57-f81d5c7c0d25");
        user.setLogin("edfefefef");
        user.setHashPassword("Slveol");
        user.setRole("wdwdw");
        user.setDateFinish(new Date());
        user.setDateCreate(new Date());
        user.setStatus(Status.PLANNED);
        user.setUserId("f1c46dbd-d06b-4784-893c-3b67b0e9da90");

       // projectRepository.persist("28cfa359-521d-4141-aa57-f81d5c7c0d25", project);

        //System.out.println(projectRepository.findOne("28cfa359-521d-4141-aa57-f81d5c7c0d25", "f1c46dbd-d06b-4784-893c-3b67b0e9da90").getName());


        Project project2 = new Project();
        project2.setId("28cfa359-521d-4141-aa57-f81d5c7c0d25");
        project2.setName("33333");
        project2.setDescription("3333333");
        project2.setDateStart(new Date());
        project2.setDateFinish(new Date());
        project2.setDateCreate(new Date());
        project2.setStatus(Status.DONE);
        project2.setUserId("f1c46dbd-d06b-4784-893c-3b67b0e9da90");

        projectRepository.merge("28cfa359-521d-4141-aa57-f81d5c7c0d25", project2, "f1c46dbd-d06b-4784-893c-3b67b0e9da90");

        System.out.println(projectRepository.findOne("28cfa359-521d-4141-aa57-f81d5c7c0d25", "f1c46dbd-d06b-4784-893c-3b67b0e9da90").getName());
/*
     //   projectService.removeEntity("4322e9aa-5660-4f8c-b43f-f75e2f7f9a72", "11111111-1111-1111-1111-111111111111");

/*
        projectRepository.removeAll("ad8439bd-0a1c-41e5-98bc-db9bf5a2d4f0");

*//*
        Endpoint.publish("http://localhost:2019/ProjectEndpoint", projectEndpoint);
        Endpoint.publish("http://localhost:2019/TaskEndpoint", taskEndpoint);
        Endpoint.publish("http://localhost:2019/UserEndpoint", userEndpoint);
        Endpoint.publish("http://localhost:2019/SaveAndLoadEndpoint", saveAndLoadEndpoint);

        Task task = new Task();
        task.setId("28cfa359-521d-4141-aa57-f81d5c7c0d25");
        task.setName("qdqdqd111111111111111");
        task.setDescription("qdqdqdqdd");
        task.setDateStart(new Date());
        task.setDateFinish(new Date());
        task.setDateCreate(new Date());
        task.setStatus(Status.PLANNED);
        task.setIdProject("175c3503-8802-4002-b593-56b282135fa3");
        task.setUserId("f1c46dbd-user-4784-893c-3b67b0e9da90");
*/
         //taskRepository.merge(task.getId(), task, task.getUserId());

        //    System.out.println(taskRepository.findOne(task.getId(),  task.getUserId()).getName());

        //for (Task pr : taskRepository.findAll("f1c46dbd-user-4784-893c-3b67b0e9da90")) {
        //    System.out.println(pr.getName());
        //}

        //taskRepository.remove("28cfa359-521d-4141-aa57-f81d5c7c0d25", "f1c46dbd-user-4784-893c-3b67b0e9da90");


        //taskRepository.removeAll("f1c46dbd-user-4784-893c-3b67b0e9da90");*/

        //for (Task pr : taskService.listTaskFromProject("28cfa359-521d-4141-aa57-f81d5c7c0d39", "11111111-1111-1111-1111-111111111111")) {
           // System.out.println(pr.getId());
        }
    }

}