package ru.khmelev.tm.api.endpoint;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.entity.Session;
import ru.khmelev.tm.entity.Sort;
import ru.khmelev.tm.entity.Task;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@WebService
public interface ITaskEndpoint extends IEntityEndpoint<Task> {

    @WebMethod
    List<Task> listTaskFromProject(@NotNull final Session session, @NotNull final String idProject);

    @WebMethod
    void removeAllTaskFromProject(@NotNull final Session session, @NotNull final String idProject);
}