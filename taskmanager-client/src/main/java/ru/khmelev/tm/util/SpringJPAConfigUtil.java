package ru.khmelev.tm.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.khmelev.tm.api.endpoint.IProjectEndpoint;
import ru.khmelev.tm.api.endpoint.ITaskEndpoint;
import ru.khmelev.tm.api.endpoint.IUserEndpoint;
import ru.khmelev.tm.endpoint.ProjectEndpointService;
import ru.khmelev.tm.endpoint.TaskEndpointService;
import ru.khmelev.tm.endpoint.UserEndpointService;

@Configuration
@ComponentScan("ru.khmelev")
public class SpringJPAConfigUtil {

    @Bean
    public IProjectEndpoint getProjectEndpointService() {
        return new ProjectEndpointService().getProjectEndpointPort();
    }

    @Bean
    public ITaskEndpoint getTaskEndpointService() {
        return new TaskEndpointService().getTaskEndpointPort();
    }

    @Bean
    public IUserEndpoint getUserEndpointService() {
        return new UserEndpointService().getUserEndpointPort();
    }
}