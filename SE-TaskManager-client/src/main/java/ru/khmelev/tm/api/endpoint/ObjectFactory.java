
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

    private final static QName _ClassNotFoundException_QNAME = new QName("http://endpoint.api.tm.khmelev.ru/", "ClassNotFoundException");
    private final static QName _IOException_QNAME = new QName("http://endpoint.api.tm.khmelev.ru/", "IOException");
    private final static QName _JAXBException_QNAME = new QName("http://endpoint.api.tm.khmelev.ru/", "JAXBException");
    private final static QName _ClearEntity_QNAME = new QName("http://endpoint.api.tm.khmelev.ru/", "clearEntity");
    private final static QName _ClearEntityResponse_QNAME = new QName("http://endpoint.api.tm.khmelev.ru/", "clearEntityResponse");
    private final static QName _CreateEntity_QNAME = new QName("http://endpoint.api.tm.khmelev.ru/", "createEntity");
    private final static QName _CreateEntityResponse_QNAME = new QName("http://endpoint.api.tm.khmelev.ru/", "createEntityResponse");
    private final static QName _EditEntity_QNAME = new QName("http://endpoint.api.tm.khmelev.ru/", "editEntity");
    private final static QName _EditEntityResponse_QNAME = new QName("http://endpoint.api.tm.khmelev.ru/", "editEntityResponse");
    private final static QName _FasterXmlLoadJSON_QNAME = new QName("http://endpoint.api.tm.khmelev.ru/", "fasterXmlLoadJSON");
    private final static QName _FasterXmlLoadJSONResponse_QNAME = new QName("http://endpoint.api.tm.khmelev.ru/", "fasterXmlLoadJSONResponse");
    private final static QName _FasterXmlLoadXML_QNAME = new QName("http://endpoint.api.tm.khmelev.ru/", "fasterXmlLoadXML");
    private final static QName _FasterXmlLoadXMLResponse_QNAME = new QName("http://endpoint.api.tm.khmelev.ru/", "fasterXmlLoadXMLResponse");
    private final static QName _FasterXmlSaveJSON_QNAME = new QName("http://endpoint.api.tm.khmelev.ru/", "fasterXmlSaveJSON");
    private final static QName _FasterXmlSaveJSONResponse_QNAME = new QName("http://endpoint.api.tm.khmelev.ru/", "fasterXmlSaveJSONResponse");
    private final static QName _FasterXmlSaveXML_QNAME = new QName("http://endpoint.api.tm.khmelev.ru/", "fasterXmlSaveXML");
    private final static QName _FasterXmlSaveXMLResponse_QNAME = new QName("http://endpoint.api.tm.khmelev.ru/", "fasterXmlSaveXMLResponse");
    private final static QName _FindAll_QNAME = new QName("http://endpoint.api.tm.khmelev.ru/", "findAll");
    private final static QName _FindAllDescription_QNAME = new QName("http://endpoint.api.tm.khmelev.ru/", "findAllDescription");
    private final static QName _FindAllDescriptionResponse_QNAME = new QName("http://endpoint.api.tm.khmelev.ru/", "findAllDescriptionResponse");
    private final static QName _FindAllName_QNAME = new QName("http://endpoint.api.tm.khmelev.ru/", "findAllName");
    private final static QName _FindAllNameResponse_QNAME = new QName("http://endpoint.api.tm.khmelev.ru/", "findAllNameResponse");
    private final static QName _FindAllResponse_QNAME = new QName("http://endpoint.api.tm.khmelev.ru/", "findAllResponse");
    private final static QName _FindEntity_QNAME = new QName("http://endpoint.api.tm.khmelev.ru/", "findEntity");
    private final static QName _FindEntityResponse_QNAME = new QName("http://endpoint.api.tm.khmelev.ru/", "findEntityResponse");
    private final static QName _JaxbJSONLoad_QNAME = new QName("http://endpoint.api.tm.khmelev.ru/", "jaxbJSONLoad");
    private final static QName _JaxbJSONLoadResponse_QNAME = new QName("http://endpoint.api.tm.khmelev.ru/", "jaxbJSONLoadResponse");
    private final static QName _JaxbJSONSave_QNAME = new QName("http://endpoint.api.tm.khmelev.ru/", "jaxbJSONSave");
    private final static QName _JaxbJSONSaveResponse_QNAME = new QName("http://endpoint.api.tm.khmelev.ru/", "jaxbJSONSaveResponse");
    private final static QName _JaxbXmlLoad_QNAME = new QName("http://endpoint.api.tm.khmelev.ru/", "jaxbXmlLoad");
    private final static QName _JaxbXmlLoadResponse_QNAME = new QName("http://endpoint.api.tm.khmelev.ru/", "jaxbXmlLoadResponse");
    private final static QName _JaxbXmlSave_QNAME = new QName("http://endpoint.api.tm.khmelev.ru/", "jaxbXmlSave");
    private final static QName _JaxbXmlSaveResponse_QNAME = new QName("http://endpoint.api.tm.khmelev.ru/", "jaxbXmlSaveResponse");
    private final static QName _ListTaskFromProject_QNAME = new QName("http://endpoint.api.tm.khmelev.ru/", "listTaskFromProject");
    private final static QName _ListTaskFromProjectResponse_QNAME = new QName("http://endpoint.api.tm.khmelev.ru/", "listTaskFromProjectResponse");
    private final static QName _RemoveAllTaskFromProject_QNAME = new QName("http://endpoint.api.tm.khmelev.ru/", "removeAllTaskFromProject");
    private final static QName _RemoveAllTaskFromProjectResponse_QNAME = new QName("http://endpoint.api.tm.khmelev.ru/", "removeAllTaskFromProjectResponse");
    private final static QName _RemoveEntity_QNAME = new QName("http://endpoint.api.tm.khmelev.ru/", "removeEntity");
    private final static QName _RemoveEntityResponse_QNAME = new QName("http://endpoint.api.tm.khmelev.ru/", "removeEntityResponse");
    private final static QName _SerializationLoad_QNAME = new QName("http://endpoint.api.tm.khmelev.ru/", "serializationLoad");
    private final static QName _SerializationLoadResponse_QNAME = new QName("http://endpoint.api.tm.khmelev.ru/", "serializationLoadResponse");
    private final static QName _SerializationSave_QNAME = new QName("http://endpoint.api.tm.khmelev.ru/", "serializationSave");
    private final static QName _SerializationSaveResponse_QNAME = new QName("http://endpoint.api.tm.khmelev.ru/", "serializationSaveResponse");
    private final static QName _SoQrt_QNAME = new QName("http://endpoint.api.tm.khmelev.ru/", "soQrt");
    private final static QName _SoQrtResponse_QNAME = new QName("http://endpoint.api.tm.khmelev.ru/", "soQrtResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ru.khmelev.tm.api.endpoint
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ClassNotFoundException }
     * 
     */
    public ClassNotFoundException createClassNotFoundException() {
        return new ClassNotFoundException();
    }

    /**
     * Create an instance of {@link IOException }
     * 
     */
    public IOException createIOException() {
        return new IOException();
    }

    /**
     * Create an instance of {@link JAXBException }
     * 
     */
    public JAXBException createJAXBException() {
        return new JAXBException();
    }

    /**
     * Create an instance of {@link ClearEntity }
     * 
     */
    public ClearEntity createClearEntity() {
        return new ClearEntity();
    }

    /**
     * Create an instance of {@link ClearEntityResponse }
     * 
     */
    public ClearEntityResponse createClearEntityResponse() {
        return new ClearEntityResponse();
    }

    /**
     * Create an instance of {@link CreateEntity }
     * 
     */
    public CreateEntity createCreateEntity() {
        return new CreateEntity();
    }

    /**
     * Create an instance of {@link CreateEntityResponse }
     * 
     */
    public CreateEntityResponse createCreateEntityResponse() {
        return new CreateEntityResponse();
    }

    /**
     * Create an instance of {@link EditEntity }
     * 
     */
    public EditEntity createEditEntity() {
        return new EditEntity();
    }

    /**
     * Create an instance of {@link EditEntityResponse }
     * 
     */
    public EditEntityResponse createEditEntityResponse() {
        return new EditEntityResponse();
    }

    /**
     * Create an instance of {@link FasterXmlLoadJSON }
     * 
     */
    public FasterXmlLoadJSON createFasterXmlLoadJSON() {
        return new FasterXmlLoadJSON();
    }

    /**
     * Create an instance of {@link FasterXmlLoadJSONResponse }
     * 
     */
    public FasterXmlLoadJSONResponse createFasterXmlLoadJSONResponse() {
        return new FasterXmlLoadJSONResponse();
    }

    /**
     * Create an instance of {@link FasterXmlLoadXML }
     * 
     */
    public FasterXmlLoadXML createFasterXmlLoadXML() {
        return new FasterXmlLoadXML();
    }

    /**
     * Create an instance of {@link FasterXmlLoadXMLResponse }
     * 
     */
    public FasterXmlLoadXMLResponse createFasterXmlLoadXMLResponse() {
        return new FasterXmlLoadXMLResponse();
    }

    /**
     * Create an instance of {@link FasterXmlSaveJSON }
     * 
     */
    public FasterXmlSaveJSON createFasterXmlSaveJSON() {
        return new FasterXmlSaveJSON();
    }

    /**
     * Create an instance of {@link FasterXmlSaveJSONResponse }
     * 
     */
    public FasterXmlSaveJSONResponse createFasterXmlSaveJSONResponse() {
        return new FasterXmlSaveJSONResponse();
    }

    /**
     * Create an instance of {@link FasterXmlSaveXML }
     * 
     */
    public FasterXmlSaveXML createFasterXmlSaveXML() {
        return new FasterXmlSaveXML();
    }

    /**
     * Create an instance of {@link FasterXmlSaveXMLResponse }
     * 
     */
    public FasterXmlSaveXMLResponse createFasterXmlSaveXMLResponse() {
        return new FasterXmlSaveXMLResponse();
    }

    /**
     * Create an instance of {@link FindAll }
     * 
     */
    public FindAll createFindAll() {
        return new FindAll();
    }

    /**
     * Create an instance of {@link FindAllDescription }
     * 
     */
    public FindAllDescription createFindAllDescription() {
        return new FindAllDescription();
    }

    /**
     * Create an instance of {@link FindAllDescriptionResponse }
     * 
     */
    public FindAllDescriptionResponse createFindAllDescriptionResponse() {
        return new FindAllDescriptionResponse();
    }

    /**
     * Create an instance of {@link FindAllName }
     * 
     */
    public FindAllName createFindAllName() {
        return new FindAllName();
    }

    /**
     * Create an instance of {@link FindAllNameResponse }
     * 
     */
    public FindAllNameResponse createFindAllNameResponse() {
        return new FindAllNameResponse();
    }

    /**
     * Create an instance of {@link FindAllResponse }
     * 
     */
    public FindAllResponse createFindAllResponse() {
        return new FindAllResponse();
    }

    /**
     * Create an instance of {@link FindEntity }
     * 
     */
    public FindEntity createFindEntity() {
        return new FindEntity();
    }

    /**
     * Create an instance of {@link FindEntityResponse }
     * 
     */
    public FindEntityResponse createFindEntityResponse() {
        return new FindEntityResponse();
    }

    /**
     * Create an instance of {@link JaxbJSONLoad }
     * 
     */
    public JaxbJSONLoad createJaxbJSONLoad() {
        return new JaxbJSONLoad();
    }

    /**
     * Create an instance of {@link JaxbJSONLoadResponse }
     * 
     */
    public JaxbJSONLoadResponse createJaxbJSONLoadResponse() {
        return new JaxbJSONLoadResponse();
    }

    /**
     * Create an instance of {@link JaxbJSONSave }
     * 
     */
    public JaxbJSONSave createJaxbJSONSave() {
        return new JaxbJSONSave();
    }

    /**
     * Create an instance of {@link JaxbJSONSaveResponse }
     * 
     */
    public JaxbJSONSaveResponse createJaxbJSONSaveResponse() {
        return new JaxbJSONSaveResponse();
    }

    /**
     * Create an instance of {@link JaxbXmlLoad }
     * 
     */
    public JaxbXmlLoad createJaxbXmlLoad() {
        return new JaxbXmlLoad();
    }

    /**
     * Create an instance of {@link JaxbXmlLoadResponse }
     * 
     */
    public JaxbXmlLoadResponse createJaxbXmlLoadResponse() {
        return new JaxbXmlLoadResponse();
    }

    /**
     * Create an instance of {@link JaxbXmlSave }
     * 
     */
    public JaxbXmlSave createJaxbXmlSave() {
        return new JaxbXmlSave();
    }

    /**
     * Create an instance of {@link JaxbXmlSaveResponse }
     * 
     */
    public JaxbXmlSaveResponse createJaxbXmlSaveResponse() {
        return new JaxbXmlSaveResponse();
    }

    /**
     * Create an instance of {@link ListTaskFromProject }
     * 
     */
    public ListTaskFromProject createListTaskFromProject() {
        return new ListTaskFromProject();
    }

    /**
     * Create an instance of {@link ListTaskFromProjectResponse }
     * 
     */
    public ListTaskFromProjectResponse createListTaskFromProjectResponse() {
        return new ListTaskFromProjectResponse();
    }

    /**
     * Create an instance of {@link RemoveAllTaskFromProject }
     * 
     */
    public RemoveAllTaskFromProject createRemoveAllTaskFromProject() {
        return new RemoveAllTaskFromProject();
    }

    /**
     * Create an instance of {@link RemoveAllTaskFromProjectResponse }
     * 
     */
    public RemoveAllTaskFromProjectResponse createRemoveAllTaskFromProjectResponse() {
        return new RemoveAllTaskFromProjectResponse();
    }

    /**
     * Create an instance of {@link RemoveEntity }
     * 
     */
    public RemoveEntity createRemoveEntity() {
        return new RemoveEntity();
    }

    /**
     * Create an instance of {@link RemoveEntityResponse }
     * 
     */
    public RemoveEntityResponse createRemoveEntityResponse() {
        return new RemoveEntityResponse();
    }

    /**
     * Create an instance of {@link SerializationLoad }
     * 
     */
    public SerializationLoad createSerializationLoad() {
        return new SerializationLoad();
    }

    /**
     * Create an instance of {@link SerializationLoadResponse }
     * 
     */
    public SerializationLoadResponse createSerializationLoadResponse() {
        return new SerializationLoadResponse();
    }

    /**
     * Create an instance of {@link SerializationSave }
     * 
     */
    public SerializationSave createSerializationSave() {
        return new SerializationSave();
    }

    /**
     * Create an instance of {@link SerializationSaveResponse }
     * 
     */
    public SerializationSaveResponse createSerializationSaveResponse() {
        return new SerializationSaveResponse();
    }

    /**
     * Create an instance of {@link SoQrt }
     * 
     */
    public SoQrt createSoQrt() {
        return new SoQrt();
    }

    /**
     * Create an instance of {@link SoQrtResponse }
     * 
     */
    public SoQrtResponse createSoQrtResponse() {
        return new SoQrtResponse();
    }

    /**
     * Create an instance of {@link Session }
     * 
     */
    public Session createSession() {
        return new Session();
    }

    /**
     * Create an instance of {@link Task }
     * 
     */
    public Task createTask() {
        return new Task();
    }

    /**
     * Create an instance of {@link Throwable }
     * 
     */
    public Throwable createThrowable() {
        return new Throwable();
    }

    /**
     * Create an instance of {@link StackTraceElement }
     * 
     */
    public StackTraceElement createStackTraceElement() {
        return new StackTraceElement();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ClassNotFoundException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.api.tm.khmelev.ru/", name = "ClassNotFoundException")
    public JAXBElement<ClassNotFoundException> createClassNotFoundException(ClassNotFoundException value) {
        return new JAXBElement<ClassNotFoundException>(_ClassNotFoundException_QNAME, ClassNotFoundException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IOException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.api.tm.khmelev.ru/", name = "IOException")
    public JAXBElement<IOException> createIOException(IOException value) {
        return new JAXBElement<IOException>(_IOException_QNAME, IOException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link JAXBException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.api.tm.khmelev.ru/", name = "JAXBException")
    public JAXBElement<JAXBException> createJAXBException(JAXBException value) {
        return new JAXBElement<JAXBException>(_JAXBException_QNAME, JAXBException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ClearEntity }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.api.tm.khmelev.ru/", name = "clearEntity")
    public JAXBElement<ClearEntity> createClearEntity(ClearEntity value) {
        return new JAXBElement<ClearEntity>(_ClearEntity_QNAME, ClearEntity.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ClearEntityResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.api.tm.khmelev.ru/", name = "clearEntityResponse")
    public JAXBElement<ClearEntityResponse> createClearEntityResponse(ClearEntityResponse value) {
        return new JAXBElement<ClearEntityResponse>(_ClearEntityResponse_QNAME, ClearEntityResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateEntity }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.api.tm.khmelev.ru/", name = "createEntity")
    public JAXBElement<CreateEntity> createCreateEntity(CreateEntity value) {
        return new JAXBElement<CreateEntity>(_CreateEntity_QNAME, CreateEntity.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateEntityResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.api.tm.khmelev.ru/", name = "createEntityResponse")
    public JAXBElement<CreateEntityResponse> createCreateEntityResponse(CreateEntityResponse value) {
        return new JAXBElement<CreateEntityResponse>(_CreateEntityResponse_QNAME, CreateEntityResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EditEntity }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.api.tm.khmelev.ru/", name = "editEntity")
    public JAXBElement<EditEntity> createEditEntity(EditEntity value) {
        return new JAXBElement<EditEntity>(_EditEntity_QNAME, EditEntity.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EditEntityResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.api.tm.khmelev.ru/", name = "editEntityResponse")
    public JAXBElement<EditEntityResponse> createEditEntityResponse(EditEntityResponse value) {
        return new JAXBElement<EditEntityResponse>(_EditEntityResponse_QNAME, EditEntityResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FasterXmlLoadJSON }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.api.tm.khmelev.ru/", name = "fasterXmlLoadJSON")
    public JAXBElement<FasterXmlLoadJSON> createFasterXmlLoadJSON(FasterXmlLoadJSON value) {
        return new JAXBElement<FasterXmlLoadJSON>(_FasterXmlLoadJSON_QNAME, FasterXmlLoadJSON.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FasterXmlLoadJSONResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.api.tm.khmelev.ru/", name = "fasterXmlLoadJSONResponse")
    public JAXBElement<FasterXmlLoadJSONResponse> createFasterXmlLoadJSONResponse(FasterXmlLoadJSONResponse value) {
        return new JAXBElement<FasterXmlLoadJSONResponse>(_FasterXmlLoadJSONResponse_QNAME, FasterXmlLoadJSONResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FasterXmlLoadXML }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.api.tm.khmelev.ru/", name = "fasterXmlLoadXML")
    public JAXBElement<FasterXmlLoadXML> createFasterXmlLoadXML(FasterXmlLoadXML value) {
        return new JAXBElement<FasterXmlLoadXML>(_FasterXmlLoadXML_QNAME, FasterXmlLoadXML.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FasterXmlLoadXMLResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.api.tm.khmelev.ru/", name = "fasterXmlLoadXMLResponse")
    public JAXBElement<FasterXmlLoadXMLResponse> createFasterXmlLoadXMLResponse(FasterXmlLoadXMLResponse value) {
        return new JAXBElement<FasterXmlLoadXMLResponse>(_FasterXmlLoadXMLResponse_QNAME, FasterXmlLoadXMLResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FasterXmlSaveJSON }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.api.tm.khmelev.ru/", name = "fasterXmlSaveJSON")
    public JAXBElement<FasterXmlSaveJSON> createFasterXmlSaveJSON(FasterXmlSaveJSON value) {
        return new JAXBElement<FasterXmlSaveJSON>(_FasterXmlSaveJSON_QNAME, FasterXmlSaveJSON.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FasterXmlSaveJSONResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.api.tm.khmelev.ru/", name = "fasterXmlSaveJSONResponse")
    public JAXBElement<FasterXmlSaveJSONResponse> createFasterXmlSaveJSONResponse(FasterXmlSaveJSONResponse value) {
        return new JAXBElement<FasterXmlSaveJSONResponse>(_FasterXmlSaveJSONResponse_QNAME, FasterXmlSaveJSONResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FasterXmlSaveXML }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.api.tm.khmelev.ru/", name = "fasterXmlSaveXML")
    public JAXBElement<FasterXmlSaveXML> createFasterXmlSaveXML(FasterXmlSaveXML value) {
        return new JAXBElement<FasterXmlSaveXML>(_FasterXmlSaveXML_QNAME, FasterXmlSaveXML.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FasterXmlSaveXMLResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.api.tm.khmelev.ru/", name = "fasterXmlSaveXMLResponse")
    public JAXBElement<FasterXmlSaveXMLResponse> createFasterXmlSaveXMLResponse(FasterXmlSaveXMLResponse value) {
        return new JAXBElement<FasterXmlSaveXMLResponse>(_FasterXmlSaveXMLResponse_QNAME, FasterXmlSaveXMLResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindAll }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.api.tm.khmelev.ru/", name = "findAll")
    public JAXBElement<FindAll> createFindAll(FindAll value) {
        return new JAXBElement<FindAll>(_FindAll_QNAME, FindAll.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindAllDescription }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.api.tm.khmelev.ru/", name = "findAllDescription")
    public JAXBElement<FindAllDescription> createFindAllDescription(FindAllDescription value) {
        return new JAXBElement<FindAllDescription>(_FindAllDescription_QNAME, FindAllDescription.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindAllDescriptionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.api.tm.khmelev.ru/", name = "findAllDescriptionResponse")
    public JAXBElement<FindAllDescriptionResponse> createFindAllDescriptionResponse(FindAllDescriptionResponse value) {
        return new JAXBElement<FindAllDescriptionResponse>(_FindAllDescriptionResponse_QNAME, FindAllDescriptionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindAllName }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.api.tm.khmelev.ru/", name = "findAllName")
    public JAXBElement<FindAllName> createFindAllName(FindAllName value) {
        return new JAXBElement<FindAllName>(_FindAllName_QNAME, FindAllName.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindAllNameResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.api.tm.khmelev.ru/", name = "findAllNameResponse")
    public JAXBElement<FindAllNameResponse> createFindAllNameResponse(FindAllNameResponse value) {
        return new JAXBElement<FindAllNameResponse>(_FindAllNameResponse_QNAME, FindAllNameResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindAllResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.api.tm.khmelev.ru/", name = "findAllResponse")
    public JAXBElement<FindAllResponse> createFindAllResponse(FindAllResponse value) {
        return new JAXBElement<FindAllResponse>(_FindAllResponse_QNAME, FindAllResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindEntity }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.api.tm.khmelev.ru/", name = "findEntity")
    public JAXBElement<FindEntity> createFindEntity(FindEntity value) {
        return new JAXBElement<FindEntity>(_FindEntity_QNAME, FindEntity.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindEntityResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.api.tm.khmelev.ru/", name = "findEntityResponse")
    public JAXBElement<FindEntityResponse> createFindEntityResponse(FindEntityResponse value) {
        return new JAXBElement<FindEntityResponse>(_FindEntityResponse_QNAME, FindEntityResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link JaxbJSONLoad }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.api.tm.khmelev.ru/", name = "jaxbJSONLoad")
    public JAXBElement<JaxbJSONLoad> createJaxbJSONLoad(JaxbJSONLoad value) {
        return new JAXBElement<JaxbJSONLoad>(_JaxbJSONLoad_QNAME, JaxbJSONLoad.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link JaxbJSONLoadResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.api.tm.khmelev.ru/", name = "jaxbJSONLoadResponse")
    public JAXBElement<JaxbJSONLoadResponse> createJaxbJSONLoadResponse(JaxbJSONLoadResponse value) {
        return new JAXBElement<JaxbJSONLoadResponse>(_JaxbJSONLoadResponse_QNAME, JaxbJSONLoadResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link JaxbJSONSave }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.api.tm.khmelev.ru/", name = "jaxbJSONSave")
    public JAXBElement<JaxbJSONSave> createJaxbJSONSave(JaxbJSONSave value) {
        return new JAXBElement<JaxbJSONSave>(_JaxbJSONSave_QNAME, JaxbJSONSave.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link JaxbJSONSaveResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.api.tm.khmelev.ru/", name = "jaxbJSONSaveResponse")
    public JAXBElement<JaxbJSONSaveResponse> createJaxbJSONSaveResponse(JaxbJSONSaveResponse value) {
        return new JAXBElement<JaxbJSONSaveResponse>(_JaxbJSONSaveResponse_QNAME, JaxbJSONSaveResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link JaxbXmlLoad }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.api.tm.khmelev.ru/", name = "jaxbXmlLoad")
    public JAXBElement<JaxbXmlLoad> createJaxbXmlLoad(JaxbXmlLoad value) {
        return new JAXBElement<JaxbXmlLoad>(_JaxbXmlLoad_QNAME, JaxbXmlLoad.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link JaxbXmlLoadResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.api.tm.khmelev.ru/", name = "jaxbXmlLoadResponse")
    public JAXBElement<JaxbXmlLoadResponse> createJaxbXmlLoadResponse(JaxbXmlLoadResponse value) {
        return new JAXBElement<JaxbXmlLoadResponse>(_JaxbXmlLoadResponse_QNAME, JaxbXmlLoadResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link JaxbXmlSave }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.api.tm.khmelev.ru/", name = "jaxbXmlSave")
    public JAXBElement<JaxbXmlSave> createJaxbXmlSave(JaxbXmlSave value) {
        return new JAXBElement<JaxbXmlSave>(_JaxbXmlSave_QNAME, JaxbXmlSave.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link JaxbXmlSaveResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.api.tm.khmelev.ru/", name = "jaxbXmlSaveResponse")
    public JAXBElement<JaxbXmlSaveResponse> createJaxbXmlSaveResponse(JaxbXmlSaveResponse value) {
        return new JAXBElement<JaxbXmlSaveResponse>(_JaxbXmlSaveResponse_QNAME, JaxbXmlSaveResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListTaskFromProject }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.api.tm.khmelev.ru/", name = "listTaskFromProject")
    public JAXBElement<ListTaskFromProject> createListTaskFromProject(ListTaskFromProject value) {
        return new JAXBElement<ListTaskFromProject>(_ListTaskFromProject_QNAME, ListTaskFromProject.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListTaskFromProjectResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.api.tm.khmelev.ru/", name = "listTaskFromProjectResponse")
    public JAXBElement<ListTaskFromProjectResponse> createListTaskFromProjectResponse(ListTaskFromProjectResponse value) {
        return new JAXBElement<ListTaskFromProjectResponse>(_ListTaskFromProjectResponse_QNAME, ListTaskFromProjectResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveAllTaskFromProject }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.api.tm.khmelev.ru/", name = "removeAllTaskFromProject")
    public JAXBElement<RemoveAllTaskFromProject> createRemoveAllTaskFromProject(RemoveAllTaskFromProject value) {
        return new JAXBElement<RemoveAllTaskFromProject>(_RemoveAllTaskFromProject_QNAME, RemoveAllTaskFromProject.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveAllTaskFromProjectResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.api.tm.khmelev.ru/", name = "removeAllTaskFromProjectResponse")
    public JAXBElement<RemoveAllTaskFromProjectResponse> createRemoveAllTaskFromProjectResponse(RemoveAllTaskFromProjectResponse value) {
        return new JAXBElement<RemoveAllTaskFromProjectResponse>(_RemoveAllTaskFromProjectResponse_QNAME, RemoveAllTaskFromProjectResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveEntity }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.api.tm.khmelev.ru/", name = "removeEntity")
    public JAXBElement<RemoveEntity> createRemoveEntity(RemoveEntity value) {
        return new JAXBElement<RemoveEntity>(_RemoveEntity_QNAME, RemoveEntity.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveEntityResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.api.tm.khmelev.ru/", name = "removeEntityResponse")
    public JAXBElement<RemoveEntityResponse> createRemoveEntityResponse(RemoveEntityResponse value) {
        return new JAXBElement<RemoveEntityResponse>(_RemoveEntityResponse_QNAME, RemoveEntityResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SerializationLoad }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.api.tm.khmelev.ru/", name = "serializationLoad")
    public JAXBElement<SerializationLoad> createSerializationLoad(SerializationLoad value) {
        return new JAXBElement<SerializationLoad>(_SerializationLoad_QNAME, SerializationLoad.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SerializationLoadResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.api.tm.khmelev.ru/", name = "serializationLoadResponse")
    public JAXBElement<SerializationLoadResponse> createSerializationLoadResponse(SerializationLoadResponse value) {
        return new JAXBElement<SerializationLoadResponse>(_SerializationLoadResponse_QNAME, SerializationLoadResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SerializationSave }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.api.tm.khmelev.ru/", name = "serializationSave")
    public JAXBElement<SerializationSave> createSerializationSave(SerializationSave value) {
        return new JAXBElement<SerializationSave>(_SerializationSave_QNAME, SerializationSave.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SerializationSaveResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.api.tm.khmelev.ru/", name = "serializationSaveResponse")
    public JAXBElement<SerializationSaveResponse> createSerializationSaveResponse(SerializationSaveResponse value) {
        return new JAXBElement<SerializationSaveResponse>(_SerializationSaveResponse_QNAME, SerializationSaveResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SoQrt }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.api.tm.khmelev.ru/", name = "soQrt")
    public JAXBElement<SoQrt> createSoQrt(SoQrt value) {
        return new JAXBElement<SoQrt>(_SoQrt_QNAME, SoQrt.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SoQrtResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.api.tm.khmelev.ru/", name = "soQrtResponse")
    public JAXBElement<SoQrtResponse> createSoQrtResponse(SoQrtResponse value) {
        return new JAXBElement<SoQrtResponse>(_SoQrtResponse_QNAME, SoQrtResponse.class, null, value);
    }

}
