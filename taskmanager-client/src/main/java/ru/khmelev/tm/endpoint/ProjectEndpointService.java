package ru.khmelev.tm.endpoint;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;
import ru.khmelev.tm.api.endpoint.IProjectEndpoint;

/**
 * This class was generated by Apache CXF 3.2.7
 * 2019-05-13T15:51:16.342+03:00
 * Generated source version: 3.2.7
 *
 */
@WebServiceClient(name = "ProjectEndpointService",
                  wsdlLocation = "http://127.0.0.1:80/ProjectEndpoint?wsdl",
                  targetNamespace = "http://endpoint.tm.khmelev.ru/")
public class ProjectEndpointService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://endpoint.tm.khmelev.ru/", "ProjectEndpointService");
    public final static QName ProjectEndpointPort = new QName("http://endpoint.tm.khmelev.ru/", "ProjectEndpointPort");
    static {
        URL url = null;
        try {
            url = new URL("http://127.0.0.1:80/ProjectEndpoint?wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(ProjectEndpointService.class.getName())
                .log(java.util.logging.Level.INFO,
                     "Can not initialize the default wsdl from {0}", "http://127.0.0.1:80/ProjectEndpoint?wsdl");
        }
        WSDL_LOCATION = url;
    }

    public ProjectEndpointService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public ProjectEndpointService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ProjectEndpointService() {
        super(WSDL_LOCATION, SERVICE);
    }

    public ProjectEndpointService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    public ProjectEndpointService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public ProjectEndpointService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }




    /**
     *
     * @return
     *     returns IProjectEndpoint
     */
    @WebEndpoint(name = "ProjectEndpointPort")
    public IProjectEndpoint getProjectEndpointPort() {
        return super.getPort(ProjectEndpointPort, IProjectEndpoint.class);
    }

    /**
     *
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns IProjectEndpoint
     */
    @WebEndpoint(name = "ProjectEndpointPort")
    public IProjectEndpoint getProjectEndpointPort(WebServiceFeature... features) {
        return super.getPort(ProjectEndpointPort, IProjectEndpoint.class, features);
    }

}
