package ru.khmelev.tm;

import ru.khmelev.tm.bootstrap.Bootstrap;

public final class ApplicationServer {
    public static void main(String[] args) {

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.init();
    }
}