package ru.khmelev.tm.endpoint;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;
import ru.khmelev.tm.api.endpoint.IUserEndpoint;

/**
 * This class was generated by Apache CXF 3.2.7
 * 2019-05-07T12:28:27.397+03:00
 * Generated source version: 3.2.7
 *
 */
@WebServiceClient(name = "UserEndpointService",
                  wsdlLocation = "http://localhost:2019/UserEndpoint?wsdl",
                  targetNamespace = "http://endpoint.tm.khmelev.ru/")
public class UserEndpointService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://endpoint.tm.khmelev.ru/", "UserEndpointService");
    public final static QName UserEndpointPort = new QName("http://endpoint.tm.khmelev.ru/", "UserEndpointPort");
    static {
        URL url = null;
        try {
            url = new URL("http://localhost:2019/UserEndpoint?wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(UserEndpointService.class.getName())
                .log(java.util.logging.Level.INFO,
                     "Can not initialize the default wsdl from {0}", "http://localhost:2019/UserEndpoint?wsdl");
        }
        WSDL_LOCATION = url;
    }

    public UserEndpointService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public UserEndpointService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public UserEndpointService() {
        super(WSDL_LOCATION, SERVICE);
    }

    public UserEndpointService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    public UserEndpointService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public UserEndpointService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }




    /**
     *
     * @return
     *     returns IUserEndpoint
     */
    @WebEndpoint(name = "UserEndpointPort")
    public IUserEndpoint getUserEndpointPort() {
        return super.getPort(UserEndpointPort, IUserEndpoint.class);
    }

    /**
     *
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns IUserEndpoint
     */
    @WebEndpoint(name = "UserEndpointPort")
    public IUserEndpoint getUserEndpointPort(WebServiceFeature... features) {
        return super.getPort(UserEndpointPort, IUserEndpoint.class, features);
    }

}
