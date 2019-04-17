
package ru.khmelev.tm.api.endpoint;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ru.khmelev.tm.api.endpoint package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _CreateUser_QNAME = new QName("http://endpoint.api.tm.khmelev.ru/", "createUser");
    private final static QName _CreateUserResponse_QNAME = new QName("http://endpoint.api.tm.khmelev.ru/", "createUserResponse");
    private final static QName _EditUser_QNAME = new QName("http://endpoint.api.tm.khmelev.ru/", "editUser");
    private final static QName _EditUserResponse_QNAME = new QName("http://endpoint.api.tm.khmelev.ru/", "editUserResponse");
    private final static QName _FindAllUser_QNAME = new QName("http://endpoint.api.tm.khmelev.ru/", "findAllUser");
    private final static QName _FindAllUserResponse_QNAME = new QName("http://endpoint.api.tm.khmelev.ru/", "findAllUserResponse");
    private final static QName _FindUser_QNAME = new QName("http://endpoint.api.tm.khmelev.ru/", "findUser");
    private final static QName _FindUserResponse_QNAME = new QName("http://endpoint.api.tm.khmelev.ru/", "findUserResponse");
    private final static QName _GetId_QNAME = new QName("http://endpoint.api.tm.khmelev.ru/", "getId");
    private final static QName _GetIdResponse_QNAME = new QName("http://endpoint.api.tm.khmelev.ru/", "getIdResponse");
    private final static QName _GetName_QNAME = new QName("http://endpoint.api.tm.khmelev.ru/", "getName");
    private final static QName _GetNameResponse_QNAME = new QName("http://endpoint.api.tm.khmelev.ru/", "getNameResponse");
    private final static QName _GetUserFromSession_QNAME = new QName("http://endpoint.api.tm.khmelev.ru/", "getUserFromSession");
    private final static QName _GetUserFromSessionResponse_QNAME = new QName("http://endpoint.api.tm.khmelev.ru/", "getUserFromSessionResponse");
    private final static QName _RemoveUser_QNAME = new QName("http://endpoint.api.tm.khmelev.ru/", "removeUser");
    private final static QName _RemoveUserResponse_QNAME = new QName("http://endpoint.api.tm.khmelev.ru/", "removeUserResponse");
    private final static QName _UserLogOut_QNAME = new QName("http://endpoint.api.tm.khmelev.ru/", "userLogOut");
    private final static QName _UserLogOutResponse_QNAME = new QName("http://endpoint.api.tm.khmelev.ru/", "userLogOutResponse");
    private final static QName _UserLogin_QNAME = new QName("http://endpoint.api.tm.khmelev.ru/", "userLogin");
    private final static QName _UserLoginResponse_QNAME = new QName("http://endpoint.api.tm.khmelev.ru/", "userLoginResponse");
    private final static QName _UserSetPassword_QNAME = new QName("http://endpoint.api.tm.khmelev.ru/", "userSetPassword");
    private final static QName _UserSetPasswordResponse_QNAME = new QName("http://endpoint.api.tm.khmelev.ru/", "userSetPasswordResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ru.khmelev.tm.api.endpoint
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CreateUser }
     * 
     */
    public CreateUser createCreateUser() {
        return new CreateUser();
    }

    /**
     * Create an instance of {@link CreateUserResponse }
     * 
     */
    public CreateUserResponse createCreateUserResponse() {
        return new CreateUserResponse();
    }

    /**
     * Create an instance of {@link EditUser }
     * 
     */
    public EditUser createEditUser() {
        return new EditUser();
    }

    /**
     * Create an instance of {@link EditUserResponse }
     * 
     */
    public EditUserResponse createEditUserResponse() {
        return new EditUserResponse();
    }

    /**
     * Create an instance of {@link FindAllUser }
     * 
     */
    public FindAllUser createFindAllUser() {
        return new FindAllUser();
    }

    /**
     * Create an instance of {@link FindAllUserResponse }
     * 
     */
    public FindAllUserResponse createFindAllUserResponse() {
        return new FindAllUserResponse();
    }

    /**
     * Create an instance of {@link FindUser }
     * 
     */
    public FindUser createFindUser() {
        return new FindUser();
    }

    /**
     * Create an instance of {@link FindUserResponse }
     * 
     */
    public FindUserResponse createFindUserResponse() {
        return new FindUserResponse();
    }

    /**
     * Create an instance of {@link GetId }
     * 
     */
    public GetId createGetId() {
        return new GetId();
    }

    /**
     * Create an instance of {@link GetIdResponse }
     * 
     */
    public GetIdResponse createGetIdResponse() {
        return new GetIdResponse();
    }

    /**
     * Create an instance of {@link GetName }
     * 
     */
    public GetName createGetName() {
        return new GetName();
    }

    /**
     * Create an instance of {@link GetNameResponse }
     * 
     */
    public GetNameResponse createGetNameResponse() {
        return new GetNameResponse();
    }

    /**
     * Create an instance of {@link GetUserFromSession }
     * 
     */
    public GetUserFromSession createGetUserFromSession() {
        return new GetUserFromSession();
    }

    /**
     * Create an instance of {@link GetUserFromSessionResponse }
     * 
     */
    public GetUserFromSessionResponse createGetUserFromSessionResponse() {
        return new GetUserFromSessionResponse();
    }

    /**
     * Create an instance of {@link RemoveUser }
     * 
     */
    public RemoveUser createRemoveUser() {
        return new RemoveUser();
    }

    /**
     * Create an instance of {@link RemoveUserResponse }
     * 
     */
    public RemoveUserResponse createRemoveUserResponse() {
        return new RemoveUserResponse();
    }

    /**
     * Create an instance of {@link UserLogOut }
     * 
     */
    public UserLogOut createUserLogOut() {
        return new UserLogOut();
    }

    /**
     * Create an instance of {@link UserLogOutResponse }
     * 
     */
    public UserLogOutResponse createUserLogOutResponse() {
        return new UserLogOutResponse();
    }

    /**
     * Create an instance of {@link UserLogin }
     * 
     */
    public UserLogin createUserLogin() {
        return new UserLogin();
    }

    /**
     * Create an instance of {@link UserLoginResponse }
     * 
     */
    public UserLoginResponse createUserLoginResponse() {
        return new UserLoginResponse();
    }

    /**
     * Create an instance of {@link UserSetPassword }
     * 
     */
    public UserSetPassword createUserSetPassword() {
        return new UserSetPassword();
    }

    /**
     * Create an instance of {@link UserSetPasswordResponse }
     * 
     */
    public UserSetPasswordResponse createUserSetPasswordResponse() {
        return new UserSetPasswordResponse();
    }

    /**
     * Create an instance of {@link Session }
     * 
     */
    public Session createSession() {
        return new Session();
    }

    /**
     * Create an instance of {@link User }
     * 
     */
    public User createUser() {
        return new User();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.api.tm.khmelev.ru/", name = "createUser")
    public JAXBElement<CreateUser> createCreateUser(CreateUser value) {
        return new JAXBElement<CreateUser>(_CreateUser_QNAME, CreateUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.api.tm.khmelev.ru/", name = "createUserResponse")
    public JAXBElement<CreateUserResponse> createCreateUserResponse(CreateUserResponse value) {
        return new JAXBElement<CreateUserResponse>(_CreateUserResponse_QNAME, CreateUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EditUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.api.tm.khmelev.ru/", name = "editUser")
    public JAXBElement<EditUser> createEditUser(EditUser value) {
        return new JAXBElement<EditUser>(_EditUser_QNAME, EditUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EditUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.api.tm.khmelev.ru/", name = "editUserResponse")
    public JAXBElement<EditUserResponse> createEditUserResponse(EditUserResponse value) {
        return new JAXBElement<EditUserResponse>(_EditUserResponse_QNAME, EditUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindAllUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.api.tm.khmelev.ru/", name = "findAllUser")
    public JAXBElement<FindAllUser> createFindAllUser(FindAllUser value) {
        return new JAXBElement<FindAllUser>(_FindAllUser_QNAME, FindAllUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindAllUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.api.tm.khmelev.ru/", name = "findAllUserResponse")
    public JAXBElement<FindAllUserResponse> createFindAllUserResponse(FindAllUserResponse value) {
        return new JAXBElement<FindAllUserResponse>(_FindAllUserResponse_QNAME, FindAllUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.api.tm.khmelev.ru/", name = "findUser")
    public JAXBElement<FindUser> createFindUser(FindUser value) {
        return new JAXBElement<FindUser>(_FindUser_QNAME, FindUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.api.tm.khmelev.ru/", name = "findUserResponse")
    public JAXBElement<FindUserResponse> createFindUserResponse(FindUserResponse value) {
        return new JAXBElement<FindUserResponse>(_FindUserResponse_QNAME, FindUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetId }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.api.tm.khmelev.ru/", name = "getId")
    public JAXBElement<GetId> createGetId(GetId value) {
        return new JAXBElement<GetId>(_GetId_QNAME, GetId.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.api.tm.khmelev.ru/", name = "getIdResponse")
    public JAXBElement<GetIdResponse> createGetIdResponse(GetIdResponse value) {
        return new JAXBElement<GetIdResponse>(_GetIdResponse_QNAME, GetIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetName }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.api.tm.khmelev.ru/", name = "getName")
    public JAXBElement<GetName> createGetName(GetName value) {
        return new JAXBElement<GetName>(_GetName_QNAME, GetName.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetNameResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.api.tm.khmelev.ru/", name = "getNameResponse")
    public JAXBElement<GetNameResponse> createGetNameResponse(GetNameResponse value) {
        return new JAXBElement<GetNameResponse>(_GetNameResponse_QNAME, GetNameResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserFromSession }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.api.tm.khmelev.ru/", name = "getUserFromSession")
    public JAXBElement<GetUserFromSession> createGetUserFromSession(GetUserFromSession value) {
        return new JAXBElement<GetUserFromSession>(_GetUserFromSession_QNAME, GetUserFromSession.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserFromSessionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.api.tm.khmelev.ru/", name = "getUserFromSessionResponse")
    public JAXBElement<GetUserFromSessionResponse> createGetUserFromSessionResponse(GetUserFromSessionResponse value) {
        return new JAXBElement<GetUserFromSessionResponse>(_GetUserFromSessionResponse_QNAME, GetUserFromSessionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.api.tm.khmelev.ru/", name = "removeUser")
    public JAXBElement<RemoveUser> createRemoveUser(RemoveUser value) {
        return new JAXBElement<RemoveUser>(_RemoveUser_QNAME, RemoveUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.api.tm.khmelev.ru/", name = "removeUserResponse")
    public JAXBElement<RemoveUserResponse> createRemoveUserResponse(RemoveUserResponse value) {
        return new JAXBElement<RemoveUserResponse>(_RemoveUserResponse_QNAME, RemoveUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UserLogOut }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.api.tm.khmelev.ru/", name = "userLogOut")
    public JAXBElement<UserLogOut> createUserLogOut(UserLogOut value) {
        return new JAXBElement<UserLogOut>(_UserLogOut_QNAME, UserLogOut.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UserLogOutResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.api.tm.khmelev.ru/", name = "userLogOutResponse")
    public JAXBElement<UserLogOutResponse> createUserLogOutResponse(UserLogOutResponse value) {
        return new JAXBElement<UserLogOutResponse>(_UserLogOutResponse_QNAME, UserLogOutResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UserLogin }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.api.tm.khmelev.ru/", name = "userLogin")
    public JAXBElement<UserLogin> createUserLogin(UserLogin value) {
        return new JAXBElement<UserLogin>(_UserLogin_QNAME, UserLogin.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UserLoginResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.api.tm.khmelev.ru/", name = "userLoginResponse")
    public JAXBElement<UserLoginResponse> createUserLoginResponse(UserLoginResponse value) {
        return new JAXBElement<UserLoginResponse>(_UserLoginResponse_QNAME, UserLoginResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UserSetPassword }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.api.tm.khmelev.ru/", name = "userSetPassword")
    public JAXBElement<UserSetPassword> createUserSetPassword(UserSetPassword value) {
        return new JAXBElement<UserSetPassword>(_UserSetPassword_QNAME, UserSetPassword.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UserSetPasswordResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.api.tm.khmelev.ru/", name = "userSetPasswordResponse")
    public JAXBElement<UserSetPasswordResponse> createUserSetPasswordResponse(UserSetPasswordResponse value) {
        return new JAXBElement<UserSetPasswordResponse>(_UserSetPasswordResponse_QNAME, UserSetPasswordResponse.class, null, value);
    }

}
