
package ru.khmelev.tm.api.endpoint;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for sqlException complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="sqlException"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://endpoint.api.tm.khmelev.ru/}exception"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="nextException" type="{http://endpoint.api.tm.khmelev.ru/}sqlException" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sqlException", propOrder = {
    "nextException"
})
public class SqlException
    extends Exception
{

    protected SqlException nextException;

    /**
     * Gets the value of the nextException property.
     * 
     * @return
     *     possible object is
     *     {@link SqlException }
     *     
     */
    public SqlException getNextException() {
        return nextException;
    }

    /**
     * Sets the value of the nextException property.
     * 
     * @param value
     *     allowed object is
     *     {@link SqlException }
     *     
     */
    public void setNextException(SqlException value) {
        this.nextException = value;
    }

}
