package ru.khmelev.tm.util;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class ConverterUtil {

    @NotNull
    private static final SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");

    @NotNull
    static String convertFromXMLDateToString(@NotNull final XMLGregorianCalendar XMLDate) {
        return DEFAULT_DATE_FORMAT.format(convertFromXMLDateToDate(XMLDate));
    }

    @Nullable
    public static Date convertFromStringToDate(@NotNull final String dateString) {
        try {
            return DEFAULT_DATE_FORMAT.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date convertFromXMLDateToDate(@NotNull final XMLGregorianCalendar XMLDate) {
        return XMLDate.toGregorianCalendar().getTime();
    }

    @Nullable
    public static XMLGregorianCalendar convertFromDateToXMLDate(@NotNull final Date date) {
        @NotNull final GregorianCalendar c = new GregorianCalendar();
        c.setTime(date);
        try {
            XMLGregorianCalendar xmlGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
            xmlGregorianCalendar.setMillisecond(0);
            return xmlGregorianCalendar;
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }
        return null;
    }
}