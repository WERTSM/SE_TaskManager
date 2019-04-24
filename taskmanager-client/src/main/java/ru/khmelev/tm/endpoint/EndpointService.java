package ru.khmelev.tm.endpoint;

import ru.khmelev.tm.api.endpoint.IProjectEndpoint;
import ru.khmelev.tm.api.endpoint.ITaskEndpoint;
import ru.khmelev.tm.api.endpoint.IUserEndpoint;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

@ApplicationScoped
public class EndpointService {

    @Inject
    private ProjectEndpointService projectEndpointService;

    @Inject
    private TaskEndpointService taskEndpointService;

    @Inject
    private UserEndpointService userEndpointService;

    @Produces
    public IProjectEndpoint getProjectEndpointService() {
        return new ProjectEndpointService().getProjectEndpointPort();
    }

    @Produces
    public ITaskEndpoint getTaskEndpointService() {
        return new TaskEndpointService().getTaskEndpointPort();
    }

    @Produces
    public IUserEndpoint getUserEndpointService() {
        return new UserEndpointService().getUserEndpointPort();
    }
}