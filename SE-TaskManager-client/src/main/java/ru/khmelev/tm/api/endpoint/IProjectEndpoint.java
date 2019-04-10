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
 * 2019-04-10T12:14:04.980+03:00
 * Generated source version: 3.2.7
 *
 */
@WebService(targetNamespace = "http://endpoint.api.tm.khmelev.ru/", name = "IProjectEndpoint")
@XmlSeeAlso({ObjectFactory.class})
public interface IProjectEndpoint {

    @WebMethod
    @Action(input = "http://endpoint.api.tm.khmelev.ru/IProjectEndpoint/editProjectRequest", output = "http://endpoint.api.tm.khmelev.ru/IProjectEndpoint/editProjectResponse")
    @RequestWrapper(localName = "editProject", targetNamespace = "http://endpoint.api.tm.khmelev.ru/", className = "ru.khmelev.tm.api.endpoint.EditProject")
    @ResponseWrapper(localName = "editProjectResponse", targetNamespace = "http://endpoint.api.tm.khmelev.ru/", className = "ru.khmelev.tm.api.endpoint.EditProjectResponse")
    public void editProject(
        @WebParam(name = "arg0", targetNamespace = "")
        ru.khmelev.tm.api.endpoint.Session arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        ru.khmelev.tm.api.endpoint.Project arg2
    );

    @WebMethod
    @Action(input = "http://endpoint.api.tm.khmelev.ru/IProjectEndpoint/findAllProjectRequest", output = "http://endpoint.api.tm.khmelev.ru/IProjectEndpoint/findAllProjectResponse")
    @RequestWrapper(localName = "findAllProject", targetNamespace = "http://endpoint.api.tm.khmelev.ru/", className = "ru.khmelev.tm.api.endpoint.FindAllProject")
    @ResponseWrapper(localName = "findAllProjectResponse", targetNamespace = "http://endpoint.api.tm.khmelev.ru/", className = "ru.khmelev.tm.api.endpoint.FindAllProjectResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.util.List<ru.khmelev.tm.api.endpoint.Project> findAllProject(
        @WebParam(name = "arg0", targetNamespace = "")
        ru.khmelev.tm.api.endpoint.Session arg0
    );

    @WebMethod
    @Action(input = "http://endpoint.api.tm.khmelev.ru/IProjectEndpoint/findProjectRequest", output = "http://endpoint.api.tm.khmelev.ru/IProjectEndpoint/findProjectResponse")
    @RequestWrapper(localName = "findProject", targetNamespace = "http://endpoint.api.tm.khmelev.ru/", className = "ru.khmelev.tm.api.endpoint.FindProject")
    @ResponseWrapper(localName = "findProjectResponse", targetNamespace = "http://endpoint.api.tm.khmelev.ru/", className = "ru.khmelev.tm.api.endpoint.FindProjectResponse")
    @WebResult(name = "return", targetNamespace = "")
    public ru.khmelev.tm.api.endpoint.Project findProject(
        @WebParam(name = "arg0", targetNamespace = "")
        ru.khmelev.tm.api.endpoint.Session arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1
    );

    @WebMethod
    @Action(input = "http://endpoint.api.tm.khmelev.ru/IProjectEndpoint/findAllDescriptionProjectRequest", output = "http://endpoint.api.tm.khmelev.ru/IProjectEndpoint/findAllDescriptionProjectResponse")
    @RequestWrapper(localName = "findAllDescriptionProject", targetNamespace = "http://endpoint.api.tm.khmelev.ru/", className = "ru.khmelev.tm.api.endpoint.FindAllDescriptionProject")
    @ResponseWrapper(localName = "findAllDescriptionProjectResponse", targetNamespace = "http://endpoint.api.tm.khmelev.ru/", className = "ru.khmelev.tm.api.endpoint.FindAllDescriptionProjectResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.util.List<ru.khmelev.tm.api.endpoint.Project> findAllDescriptionProject(
        @WebParam(name = "arg0", targetNamespace = "")
        ru.khmelev.tm.api.endpoint.Session arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1
    );

    @WebMethod
    @Action(input = "http://endpoint.api.tm.khmelev.ru/IProjectEndpoint/removeProjectRequest", output = "http://endpoint.api.tm.khmelev.ru/IProjectEndpoint/removeProjectResponse")
    @RequestWrapper(localName = "removeProject", targetNamespace = "http://endpoint.api.tm.khmelev.ru/", className = "ru.khmelev.tm.api.endpoint.RemoveProject")
    @ResponseWrapper(localName = "removeProjectResponse", targetNamespace = "http://endpoint.api.tm.khmelev.ru/", className = "ru.khmelev.tm.api.endpoint.RemoveProjectResponse")
    public void removeProject(
        @WebParam(name = "arg0", targetNamespace = "")
        ru.khmelev.tm.api.endpoint.Session arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1
    );

    @WebMethod
    @Action(input = "http://endpoint.api.tm.khmelev.ru/IProjectEndpoint/clearProjectRequest", output = "http://endpoint.api.tm.khmelev.ru/IProjectEndpoint/clearProjectResponse")
    @RequestWrapper(localName = "clearProject", targetNamespace = "http://endpoint.api.tm.khmelev.ru/", className = "ru.khmelev.tm.api.endpoint.ClearProject")
    @ResponseWrapper(localName = "clearProjectResponse", targetNamespace = "http://endpoint.api.tm.khmelev.ru/", className = "ru.khmelev.tm.api.endpoint.ClearProjectResponse")
    public void clearProject(
        @WebParam(name = "arg0", targetNamespace = "")
        ru.khmelev.tm.api.endpoint.Session arg0
    );

    @WebMethod
    @Action(input = "http://endpoint.api.tm.khmelev.ru/IProjectEndpoint/findAllNameProjectRequest", output = "http://endpoint.api.tm.khmelev.ru/IProjectEndpoint/findAllNameProjectResponse")
    @RequestWrapper(localName = "findAllNameProject", targetNamespace = "http://endpoint.api.tm.khmelev.ru/", className = "ru.khmelev.tm.api.endpoint.FindAllNameProject")
    @ResponseWrapper(localName = "findAllNameProjectResponse", targetNamespace = "http://endpoint.api.tm.khmelev.ru/", className = "ru.khmelev.tm.api.endpoint.FindAllNameProjectResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.util.List<ru.khmelev.tm.api.endpoint.Project> findAllNameProject(
        @WebParam(name = "arg0", targetNamespace = "")
        ru.khmelev.tm.api.endpoint.Session arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1
    );

    @WebMethod
    @Action(input = "http://endpoint.api.tm.khmelev.ru/IProjectEndpoint/createProjectRequest", output = "http://endpoint.api.tm.khmelev.ru/IProjectEndpoint/createProjectResponse")
    @RequestWrapper(localName = "createProject", targetNamespace = "http://endpoint.api.tm.khmelev.ru/", className = "ru.khmelev.tm.api.endpoint.CreateProject")
    @ResponseWrapper(localName = "createProjectResponse", targetNamespace = "http://endpoint.api.tm.khmelev.ru/", className = "ru.khmelev.tm.api.endpoint.CreateProjectResponse")
    public void createProject(
        @WebParam(name = "arg0", targetNamespace = "")
        ru.khmelev.tm.api.endpoint.Session arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        ru.khmelev.tm.api.endpoint.Project arg2
    );
}
