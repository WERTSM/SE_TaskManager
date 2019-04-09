
package ru.khmelev.tm.api.endpoint;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 3.2.7
 * 2019-04-09T17:01:15.167+03:00
 * Generated source version: 3.2.7
 */

@WebFault(name = "SerialException", targetNamespace = "http://endpoint.api.tm.khmelev.ru/")
public class SerialException_Exception extends java.lang.Exception {

    private ru.khmelev.tm.api.endpoint.SerialException serialException;

    public SerialException_Exception() {
        super();
    }

    public SerialException_Exception(String message) {
        super(message);
    }

    public SerialException_Exception(String message, java.lang.Throwable cause) {
        super(message, cause);
    }

    public SerialException_Exception(String message, ru.khmelev.tm.api.endpoint.SerialException serialException) {
        super(message);
        this.serialException = serialException;
    }

    public SerialException_Exception(String message, ru.khmelev.tm.api.endpoint.SerialException serialException, java.lang.Throwable cause) {
        super(message, cause);
        this.serialException = serialException;
    }

    public ru.khmelev.tm.api.endpoint.SerialException getFaultInfo() {
        return this.serialException;
    }
}
