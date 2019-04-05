
package ru.khmelev.tm.api.endpoint;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for sort.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="sort"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="START"/&gt;
 *     &lt;enumeration value="FINISH"/&gt;
 *     &lt;enumeration value="CREATE"/&gt;
 *     &lt;enumeration value="STATUS"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "sort")
@XmlEnum
public enum Sort {

    START,
    FINISH,
    CREATE,
    STATUS;

    public String value() {
        return name();
    }

    public static Sort fromValue(String v) {
        return valueOf(v);
    }

}
