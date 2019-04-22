package ru.khmelev.tm.bootstrap;

import lombok.Getter;
import ru.khmelev.tm.api.endpoint.IProjectEndpoint;
import ru.khmelev.tm.api.endpoint.ITaskEndpoint;
import ru.khmelev.tm.api.endpoint.IUserEndpoint;

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

    public void init() {

        Endpoint.publish("http://localhost:2019/ProjectEndpoint", projectEndpoint);
        Endpoint.publish("http://localhost:2019/TaskEndpoint", taskEndpoint);
        Endpoint.publish("http://localhost:2019/UserEndpoint", userEndpoint);
    }
}