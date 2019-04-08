
package ru.khmelev.tm.api.endpoint;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 3.2.7
 * 2019-04-08T11:44:25.577+03:00
 * Generated source version: 3.2.7
 */

@WebFault(name = "JAXBException", targetNamespace = "http://endpoint.api.tm.khmelev.ru/")
public class JAXBException_Exception extends java.lang.Exception {

    private ru.khmelev.tm.api.endpoint.JAXBException jaxbException;

    public JAXBException_Exception() {
        super();
    }

    public JAXBException_Exception(String message) {
        super(message);
    }

    public JAXBException_Exception(String message, java.lang.Throwable cause) {
        super(message, cause);
    }

    public JAXBException_Exception(String message, ru.khmelev.tm.api.endpoint.JAXBException jaxbException) {
        super(message);
        this.jaxbException = jaxbException;
    }

    public JAXBException_Exception(String message, ru.khmelev.tm.api.endpoint.JAXBException jaxbException, java.lang.Throwable cause) {
        super(message, cause);
        this.jaxbException = jaxbException;
    }

    public ru.khmelev.tm.api.endpoint.JAXBException getFaultInfo() {
        return this.jaxbException;
    }
}
