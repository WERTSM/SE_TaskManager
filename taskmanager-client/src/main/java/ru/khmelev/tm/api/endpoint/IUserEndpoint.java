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
 * 2019-04-20T23:52:57.458+03:00
 * Generated source version: 3.2.7
 *
 */
@WebService(targetNamespace = "http://endpoint.api.tm.khmelev.ru/", name = "IUserEndpoint")
@XmlSeeAlso({ObjectFactory.class})
public interface IUserEndpoint {

    @WebMethod
    @Action(input = "http://endpoint.api.tm.khmelev.ru/IUserEndpoint/getUserFromSessionRequest", output = "http://endpoint.api.tm.khmelev.ru/IUserEndpoint/getUserFromSessionResponse")
    @RequestWrapper(localName = "getUserFromSession", targetNamespace = "http://endpoint.api.tm.khmelev.ru/", className = "ru.khmelev.tm.api.endpoint.GetUserFromSession")
    @ResponseWrapper(localName = "getUserFromSessionResponse", targetNamespace = "http://endpoint.api.tm.khmelev.ru/", className = "ru.khmelev.tm.api.endpoint.GetUserFromSessionResponse")
    @WebResult(name = "return", targetNamespace = "")
    public ru.khmelev.tm.api.endpoint.UserDTO getUserFromSession(
        @WebParam(name = "arg0", targetNamespace = "")
        ru.khmelev.tm.api.endpoint.SessionDTO arg0
    );

    @WebMethod
    @Action(input = "http://endpoint.api.tm.khmelev.ru/IUserEndpoint/userLoginRequest", output = "http://endpoint.api.tm.khmelev.ru/IUserEndpoint/userLoginResponse")
    @RequestWrapper(localName = "userLogin", targetNamespace = "http://endpoint.api.tm.khmelev.ru/", className = "ru.khmelev.tm.api.endpoint.UserLogin")
    @ResponseWrapper(localName = "userLoginResponse", targetNamespace = "http://endpoint.api.tm.khmelev.ru/", className = "ru.khmelev.tm.api.endpoint.UserLoginResponse")
    @WebResult(name = "return", targetNamespace = "")
    public ru.khmelev.tm.api.endpoint.SessionDTO userLogin(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1
    );

    @WebMethod
    @Action(input = "http://endpoint.api.tm.khmelev.ru/IUserEndpoint/findUserRequest", output = "http://endpoint.api.tm.khmelev.ru/IUserEndpoint/findUserResponse")
    @RequestWrapper(localName = "findUser", targetNamespace = "http://endpoint.api.tm.khmelev.ru/", className = "ru.khmelev.tm.api.endpoint.FindUser")
    @ResponseWrapper(localName = "findUserResponse", targetNamespace = "http://endpoint.api.tm.khmelev.ru/", className = "ru.khmelev.tm.api.endpoint.FindUserResponse")
    @WebResult(name = "return", targetNamespace = "")
    public ru.khmelev.tm.api.endpoint.UserDTO findUser(
        @WebParam(name = "arg0", targetNamespace = "")
        ru.khmelev.tm.api.endpoint.SessionDTO arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1
    );

    @WebMethod
    @Action(input = "http://endpoint.api.tm.khmelev.ru/IUserEndpoint/getNameRequest", output = "http://endpoint.api.tm.khmelev.ru/IUserEndpoint/getNameResponse")
    @RequestWrapper(localName = "getName", targetNamespace = "http://endpoint.api.tm.khmelev.ru/", className = "ru.khmelev.tm.api.endpoint.GetName")
    @ResponseWrapper(localName = "getNameResponse", targetNamespace = "http://endpoint.api.tm.khmelev.ru/", className = "ru.khmelev.tm.api.endpoint.GetNameResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.lang.String getName(
        @WebParam(name = "arg0", targetNamespace = "")
        ru.khmelev.tm.api.endpoint.UserDTO arg0
    );

    @WebMethod
    @Action(input = "http://endpoint.api.tm.khmelev.ru/IUserEndpoint/findAllUserRequest", output = "http://endpoint.api.tm.khmelev.ru/IUserEndpoint/findAllUserResponse")
    @RequestWrapper(localName = "findAllUser", targetNamespace = "http://endpoint.api.tm.khmelev.ru/", className = "ru.khmelev.tm.api.endpoint.FindAllUser")
    @ResponseWrapper(localName = "findAllUserResponse", targetNamespace = "http://endpoint.api.tm.khmelev.ru/", className = "ru.khmelev.tm.api.endpoint.FindAllUserResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.util.List<ru.khmelev.tm.api.endpoint.UserDTO> findAllUser(
        @WebParam(name = "arg0", targetNamespace = "")
        ru.khmelev.tm.api.endpoint.SessionDTO arg0
    );

    @WebMethod
    @Action(input = "http://endpoint.api.tm.khmelev.ru/IUserEndpoint/removeUserRequest", output = "http://endpoint.api.tm.khmelev.ru/IUserEndpoint/removeUserResponse")
    @RequestWrapper(localName = "removeUser", targetNamespace = "http://endpoint.api.tm.khmelev.ru/", className = "ru.khmelev.tm.api.endpoint.RemoveUser")
    @ResponseWrapper(localName = "removeUserResponse", targetNamespace = "http://endpoint.api.tm.khmelev.ru/", className = "ru.khmelev.tm.api.endpoint.RemoveUserResponse")
    public void removeUser(
        @WebParam(name = "arg0", targetNamespace = "")
        ru.khmelev.tm.api.endpoint.SessionDTO arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1
    );

    @WebMethod
    @Action(input = "http://endpoint.api.tm.khmelev.ru/IUserEndpoint/editUserRequest", output = "http://endpoint.api.tm.khmelev.ru/IUserEndpoint/editUserResponse")
    @RequestWrapper(localName = "editUser", targetNamespace = "http://endpoint.api.tm.khmelev.ru/", className = "ru.khmelev.tm.api.endpoint.EditUser")
    @ResponseWrapper(localName = "editUserResponse", targetNamespace = "http://endpoint.api.tm.khmelev.ru/", className = "ru.khmelev.tm.api.endpoint.EditUserResponse")
    public void editUser(
        @WebParam(name = "arg0", targetNamespace = "")
        ru.khmelev.tm.api.endpoint.SessionDTO arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        ru.khmelev.tm.api.endpoint.UserDTO arg2
    );

    @WebMethod
    @Action(input = "http://endpoint.api.tm.khmelev.ru/IUserEndpoint/getIdRequest", output = "http://endpoint.api.tm.khmelev.ru/IUserEndpoint/getIdResponse")
    @RequestWrapper(localName = "getId", targetNamespace = "http://endpoint.api.tm.khmelev.ru/", className = "ru.khmelev.tm.api.endpoint.GetId")
    @ResponseWrapper(localName = "getIdResponse", targetNamespace = "http://endpoint.api.tm.khmelev.ru/", className = "ru.khmelev.tm.api.endpoint.GetIdResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.lang.String getId(
        @WebParam(name = "arg0", targetNamespace = "")
        ru.khmelev.tm.api.endpoint.UserDTO arg0
    );

    @WebMethod
    @Action(input = "http://endpoint.api.tm.khmelev.ru/IUserEndpoint/createUserRequest", output = "http://endpoint.api.tm.khmelev.ru/IUserEndpoint/createUserResponse")
    @RequestWrapper(localName = "createUser", targetNamespace = "http://endpoint.api.tm.khmelev.ru/", className = "ru.khmelev.tm.api.endpoint.CreateUser")
    @ResponseWrapper(localName = "createUserResponse", targetNamespace = "http://endpoint.api.tm.khmelev.ru/", className = "ru.khmelev.tm.api.endpoint.CreateUserResponse")
    public void createUser(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        ru.khmelev.tm.api.endpoint.UserDTO arg1
    );

    @WebMethod
    @Action(input = "http://endpoint.api.tm.khmelev.ru/IUserEndpoint/userLogOutRequest", output = "http://endpoint.api.tm.khmelev.ru/IUserEndpoint/userLogOutResponse")
    @RequestWrapper(localName = "userLogOut", targetNamespace = "http://endpoint.api.tm.khmelev.ru/", className = "ru.khmelev.tm.api.endpoint.UserLogOut")
    @ResponseWrapper(localName = "userLogOutResponse", targetNamespace = "http://endpoint.api.tm.khmelev.ru/", className = "ru.khmelev.tm.api.endpoint.UserLogOutResponse")
    public void userLogOut(
        @WebParam(name = "arg0", targetNamespace = "")
        ru.khmelev.tm.api.endpoint.SessionDTO arg0
    );

    @WebMethod
    @Action(input = "http://endpoint.api.tm.khmelev.ru/IUserEndpoint/userSetPasswordRequest", output = "http://endpoint.api.tm.khmelev.ru/IUserEndpoint/userSetPasswordResponse")
    @RequestWrapper(localName = "userSetPassword", targetNamespace = "http://endpoint.api.tm.khmelev.ru/", className = "ru.khmelev.tm.api.endpoint.UserSetPassword")
    @ResponseWrapper(localName = "userSetPasswordResponse", targetNamespace = "http://endpoint.api.tm.khmelev.ru/", className = "ru.khmelev.tm.api.endpoint.UserSetPasswordResponse")
    public void userSetPassword(
        @WebParam(name = "arg0", targetNamespace = "")
        ru.khmelev.tm.api.endpoint.SessionDTO arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        java.lang.String arg2
    );
}
