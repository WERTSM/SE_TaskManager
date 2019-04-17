package ru.khmelev.tm.api.endpoint;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.2.7
 * 2019-04-17T13:41:59.294+03:00
 * Generated source version: 3.2.7
 *
 */
@WebService(targetNamespace = "http://endpoint.api.tm.khmelev.ru/", name = "ITaskEndpoint")
@XmlSeeAlso({ObjectFactory.class})
public interface ITaskEndpoint {

    @WebMethod
    @Action(input = "http://endpoint.api.tm.khmelev.ru/ITaskEndpoint/removeTaskRequest", output = "http://endpoint.api.tm.khmelev.ru/ITaskEndpoint/removeTaskResponse")
    @RequestWrapper(localName = "removeTask", targetNamespace = "http://endpoint.api.tm.khmelev.ru/", className = "ru.khmelev.tm.api.endpoint.RemoveTask")
    @ResponseWrapper(localName = "removeTaskResponse", targetNamespace = "http://endpoint.api.tm.khmelev.ru/", className = "ru.khmelev.tm.api.endpoint.RemoveTaskResponse")
    public void removeTask(
        @WebParam(name = "arg0", targetNamespace = "")
        ru.khmelev.tm.api.endpoint.Session arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1
    );

    @WebMethod
    @Action(input = "http://endpoint.api.tm.khmelev.ru/ITaskEndpoint/removeAllTaskFromProjectRequest", output = "http://endpoint.api.tm.khmelev.ru/ITaskEndpoint/removeAllTaskFromProjectResponse")
    @RequestWrapper(localName = "removeAllTaskFromProject", targetNamespace = "http://endpoint.api.tm.khmelev.ru/", className = "ru.khmelev.tm.api.endpoint.RemoveAllTaskFromProject")
    @ResponseWrapper(localName = "removeAllTaskFromProjectResponse", targetNamespace = "http://endpoint.api.tm.khmelev.ru/", className = "ru.khmelev.tm.api.endpoint.RemoveAllTaskFromProjectResponse")
    public void removeAllTaskFromProject(
        @WebParam(name = "arg0", targetNamespace = "")
        ru.khmelev.tm.api.endpoint.Session arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1
    );

    @WebMethod
    @Action(input = "http://endpoint.api.tm.khmelev.ru/ITaskEndpoint/findAllDescriptionTaskRequest", output = "http://endpoint.api.tm.khmelev.ru/ITaskEndpoint/findAllDescriptionTaskResponse")
    @RequestWrapper(localName = "findAllDescriptionTask", targetNamespace = "http://endpoint.api.tm.khmelev.ru/", className = "ru.khmelev.tm.api.endpoint.FindAllDescriptionTask")
    @ResponseWrapper(localName = "findAllDescriptionTaskResponse", targetNamespace = "http://endpoint.api.tm.khmelev.ru/", className = "ru.khmelev.tm.api.endpoint.FindAllDescriptionTaskResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.util.List<ru.khmelev.tm.api.endpoint.Task> findAllDescriptionTask(
        @WebParam(name = "arg0", targetNamespace = "")
        ru.khmelev.tm.api.endpoint.Session arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1
    );

    @WebMethod
    @Action(input = "http://endpoint.api.tm.khmelev.ru/ITaskEndpoint/listTaskFromProjectRequest", output = "http://endpoint.api.tm.khmelev.ru/ITaskEndpoint/listTaskFromProjectResponse")
    @RequestWrapper(localName = "listTaskFromProject", targetNamespace = "http://endpoint.api.tm.khmelev.ru/", className = "ru.khmelev.tm.api.endpoint.ListTaskFromProject")
    @ResponseWrapper(localName = "listTaskFromProjectResponse", targetNamespace = "http://endpoint.api.tm.khmelev.ru/", className = "ru.khmelev.tm.api.endpoint.ListTaskFromProjectResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.util.List<ru.khmelev.tm.api.endpoint.Task> listTaskFromProject(
        @WebParam(name = "arg0", targetNamespace = "")
        ru.khmelev.tm.api.endpoint.Session arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1
    );

    @WebMethod
    @Action(input = "http://endpoint.api.tm.khmelev.ru/ITaskEndpoint/findAllTAskRequest", output = "http://endpoint.api.tm.khmelev.ru/ITaskEndpoint/findAllTAskResponse")
    @RequestWrapper(localName = "findAllTAsk", targetNamespace = "http://endpoint.api.tm.khmelev.ru/", className = "ru.khmelev.tm.api.endpoint.FindAllTAsk")
    @ResponseWrapper(localName = "findAllTAskResponse", targetNamespace = "http://endpoint.api.tm.khmelev.ru/", className = "ru.khmelev.tm.api.endpoint.FindAllTAskResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.util.List<ru.khmelev.tm.api.endpoint.Task> findAllTAsk(
        @WebParam(name = "arg0", targetNamespace = "")
        ru.khmelev.tm.api.endpoint.Session arg0
    );

    @WebMethod
    @Action(input = "http://endpoint.api.tm.khmelev.ru/ITaskEndpoint/findAllNameTaskRequest", output = "http://endpoint.api.tm.khmelev.ru/ITaskEndpoint/findAllNameTaskResponse")
    @RequestWrapper(localName = "findAllNameTask", targetNamespace = "http://endpoint.api.tm.khmelev.ru/", className = "ru.khmelev.tm.api.endpoint.FindAllNameTask")
    @ResponseWrapper(localName = "findAllNameTaskResponse", targetNamespace = "http://endpoint.api.tm.khmelev.ru/", className = "ru.khmelev.tm.api.endpoint.FindAllNameTaskResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.util.List<ru.khmelev.tm.api.endpoint.Task> findAllNameTask(
        @WebParam(name = "arg0", targetNamespace = "")
        ru.khmelev.tm.api.endpoint.Session arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1
    );

    @WebMethod
    @Action(input = "http://endpoint.api.tm.khmelev.ru/ITaskEndpoint/editEntityTaskRequest", output = "http://endpoint.api.tm.khmelev.ru/ITaskEndpoint/editEntityTaskResponse")
    @RequestWrapper(localName = "editEntityTask", targetNamespace = "http://endpoint.api.tm.khmelev.ru/", className = "ru.khmelev.tm.api.endpoint.EditEntityTask")
    @ResponseWrapper(localName = "editEntityTaskResponse", targetNamespace = "http://endpoint.api.tm.khmelev.ru/", className = "ru.khmelev.tm.api.endpoint.EditEntityTaskResponse")
    public void editEntityTask(
        @WebParam(name = "arg0", targetNamespace = "")
        ru.khmelev.tm.api.endpoint.Session arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        ru.khmelev.tm.api.endpoint.Task arg2
    );

    @WebMethod
    @Action(input = "http://endpoint.api.tm.khmelev.ru/ITaskEndpoint/findTaskRequest", output = "http://endpoint.api.tm.khmelev.ru/ITaskEndpoint/findTaskResponse")
    @RequestWrapper(localName = "findTask", targetNamespace = "http://endpoint.api.tm.khmelev.ru/", className = "ru.khmelev.tm.api.endpoint.FindTask")
    @ResponseWrapper(localName = "findTaskResponse", targetNamespace = "http://endpoint.api.tm.khmelev.ru/", className = "ru.khmelev.tm.api.endpoint.FindTaskResponse")
    @WebResult(name = "return", targetNamespace = "")
    public ru.khmelev.tm.api.endpoint.Task findTask(
        @WebParam(name = "arg0", targetNamespace = "")
        ru.khmelev.tm.api.endpoint.Session arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1
    );

    @WebMethod
    @Action(input = "http://endpoint.api.tm.khmelev.ru/ITaskEndpoint/clearTaskRequest", output = "http://endpoint.api.tm.khmelev.ru/ITaskEndpoint/clearTaskResponse")
    @RequestWrapper(localName = "clearTask", targetNamespace = "http://endpoint.api.tm.khmelev.ru/", className = "ru.khmelev.tm.api.endpoint.ClearTask")
    @ResponseWrapper(localName = "clearTaskResponse", targetNamespace = "http://endpoint.api.tm.khmelev.ru/", className = "ru.khmelev.tm.api.endpoint.ClearTaskResponse")
    public void clearTask(
        @WebParam(name = "arg0", targetNamespace = "")
        ru.khmelev.tm.api.endpoint.Session arg0
    );

    @WebMethod
    @Action(input = "http://endpoint.api.tm.khmelev.ru/ITaskEndpoint/createTaskRequest", output = "http://endpoint.api.tm.khmelev.ru/ITaskEndpoint/createTaskResponse")
    @RequestWrapper(localName = "createTask", targetNamespace = "http://endpoint.api.tm.khmelev.ru/", className = "ru.khmelev.tm.api.endpoint.CreateTask")
    @ResponseWrapper(localName = "createTaskResponse", targetNamespace = "http://endpoint.api.tm.khmelev.ru/", className = "ru.khmelev.tm.api.endpoint.CreateTaskResponse")
    public void createTask(
        @WebParam(name = "arg0", targetNamespace = "")
        ru.khmelev.tm.api.endpoint.Session arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        ru.khmelev.tm.api.endpoint.Task arg2
    );
}
