package ru.khmelev.tm.bootstrap;

import lombok.Getter;
import ru.khmelev.tm.api.endpoint.IProjectEndpoint;
import ru.khmelev.tm.api.endpoint.ITaskEndpoint;
import ru.khmelev.tm.api.endpoint.IUserEndpoint;
import ru.khmelev.tm.api.service.ISessionService;
import ru.khmelev.tm.util.PropertyServerUtil;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.xml.ws.Endpoint;

@Getter
@ApplicationScoped
public class Bootstrap implements ServiceLocator {

    @Inject
    private IProjectEndpoint projectEndpoint;

    @Inject
    private ITaskEndpoint taskEndpoint;

    @Inject
    private IUserEndpoint userEndpoint;

    @Inject
    private PropertyServerUtil propertiesUtil;

    @Inject
    private ISessionService sessionService;

    public void init() {

        sessionService.clearEntity();

        Endpoint.publish("http://" + propertiesUtil.getHost() +":" + propertiesUtil.getPort() + "/ProjectEndpoint", projectEndpoint);
        Endpoint.publish("http://" + propertiesUtil.getHost() +":" + propertiesUtil.getPort() + "/TaskEndpoint", taskEndpoint);
        Endpoint.publish("http://" + propertiesUtil.getHost() +":" + propertiesUtil.getPort() + "/UserEndpoint", userEndpoint);

        System.out.println("Server port:" + propertiesUtil.getPort() + " started!");
    }
}