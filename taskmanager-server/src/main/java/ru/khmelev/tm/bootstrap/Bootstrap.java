package ru.khmelev.tm.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.khmelev.tm.api.endpoint.IProjectEndpoint;
import ru.khmelev.tm.api.endpoint.ITaskEndpoint;
import ru.khmelev.tm.api.endpoint.IUserEndpoint;
import ru.khmelev.tm.api.service.ISessionService;
import ru.khmelev.tm.endpoint.ProjectEndpoint;
import ru.khmelev.tm.endpoint.TaskEndpoint;
import ru.khmelev.tm.endpoint.UserEndpoint;
import ru.khmelev.tm.service.SessionService;
import ru.khmelev.tm.util.PropertyServerUtil;

import javax.xml.ws.Endpoint;

@Component
public class Bootstrap implements ServiceLocator {

    @Autowired
    private IProjectEndpoint projectEndpoint;

    @Autowired
    private ITaskEndpoint taskEndpoint;

    @Autowired
    private IUserEndpoint userEndpoint;

    @Autowired
    private PropertyServerUtil propertiesUtil;

    @Autowired
    private ISessionService sessionService;

    public void init() {

        sessionService.clearEntity();

        Endpoint.publish("http://" + propertiesUtil.getHost() + ":" + propertiesUtil.getPort() + "/ProjectEndpoint", projectEndpoint);
        Endpoint.publish("http://" + propertiesUtil.getHost() + ":" + propertiesUtil.getPort() + "/TaskEndpoint", taskEndpoint);
        Endpoint.publish("http://" + propertiesUtil.getHost() + ":" + propertiesUtil.getPort() + "/UserEndpoint", userEndpoint);

        System.out.println("Server port:" + propertiesUtil.getPort() + " started!");
    }
}