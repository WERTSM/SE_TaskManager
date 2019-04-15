package ru.khmelev.tm.command.util;

import javax.xml.bind.DatatypeConverter;
import java.util.Calendar;
import java.util.Date;

public final class DataTypeAdapter {
    private DataTypeAdapter() {
    }

    public static Date parseDate(String s) {
        if (s == null) {
            return null;
        }

        return DatatypeConverter.parseDate(s).getTime();
    }

    public static String printDate(Date dt) {
        if (dt == null) {
            return null;
        }

        Calendar c = Calendar.getInstance();
        c.setTime(dt);

        return DatatypeConverter.printDate(c);
    }

    public static Date parseDateTime(String s) {
        if (s == null) {
            return null;
        }

        return DatatypeConverter.parseDateTime(s).getTime();
    }

    public static String printDateTime(Date dt) {
        if (dt == null) {
            return null;
        }

        Calendar c = Calendar.getInstance();
        c.setTime(dt);

        return DatatypeConverter.printDateTime(c);
    }
}