package ru.Hmelev.tm;

import java.io.IOException;
import java.text.ParseException;

public class Application {
    public static void main(String[] args) throws IOException, ParseException {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.init();
    }
}