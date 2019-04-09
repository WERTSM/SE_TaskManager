
package ru.khmelev.tm.api.endpoint;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import java.util.Date;


/**
 * <p>Java class for task complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="task"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://endpoint.api.tm.khmelev.ru/}entity"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="dateCreate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="dateFinish" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="dateStart" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="idProject" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="status" type="{http://endpoint.api.tm.khmelev.ru/}status" minOccurs="0"/&gt;
 *         &lt;element name="userId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "task", propOrder = {
        "dateCreate",
        "dateFinish",
        "dateStart",
        "idProject",
        "status",
        "userId"
})
public class Task
        extends Entity {

    @XmlSchemaType(name = "dateTime")
    protected Date dateCreate;
    @XmlSchemaType(name = "dateTime")
    protected Date dateFinish;
    @XmlSchemaType(name = "dateTime")
    protected Date dateStart;
    protected String idProject;
    @XmlSchemaType(name = "string")
    protected Status status;
    protected String userId;

    /**
     * Gets the value of the dateCreate property.
     *
     * @return possible object is
     * {@link Date }
     */
    public Date getDateCreate() {
        return dateCreate;
    }

    /**
     * Sets the value of the dateCreate property.
     *
     * @param value allowed object is
     *              {@link Date }
     */
    public void setDateCreate(Date value) {
        this.dateCreate = value;
    }

    /**
     * Gets the value of the dateFinish property.
     *
     * @return possible object is
     * {@link Date }
     */
    public Date getDateFinish() {
        return dateFinish;
    }

    /**
     * Sets the value of the dateFinish property.
     *
     * @param value allowed object is
     *              {@link Date }
     */
    public void setDateFinish(Date value) {
        this.dateFinish = value;
    }

    /**
     * Gets the value of the dateStart property.
     *
     * @return possible object is
     * {@link Date }
     */
    public Date getDateStart() {
        return dateStart;
    }

    /**
     * Sets the value of the dateStart property.
     *
     * @param value allowed object is
     *              {@link Date }
     */
    public void setDateStart(Date value) {
        this.dateStart = value;
    }

    /**
     * Gets the value of the idProject property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getIdProject() {
        return idProject;
    }

    /**
     * Sets the value of the idProject property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setIdProject(String value) {
        this.idProject = value;
    }

    /**
     * Gets the value of the status property.
     *
     * @return possible object is
     * {@link Status }
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     *
     * @param value allowed object is
     *              {@link Status }
     */
    public void setStatus(Status value) {
        this.status = value;
    }

    /**
     * Gets the value of the userId property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Sets the value of the userId property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setUserId(String value) {
        this.userId = value;
    }

}
