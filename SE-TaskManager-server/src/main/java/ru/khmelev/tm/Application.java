package ru.khmelev.tm;

import ru.khmelev.tm.bootstrap.Bootstrap;

public final class Application {
    public static void main(String[] args) throws Exception {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.init();
    }
}