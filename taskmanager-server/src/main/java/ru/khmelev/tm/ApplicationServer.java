package ru.khmelev.tm;

import ru.khmelev.tm.bootstrap.Bootstrap;
import ru.khmelev.tm.util.PropertyServerUtil;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;

public final class ApplicationServer {
    public static void main(String[] args) {

        SeContainer container = SeContainerInitializer.newInstance().addPackages(ApplicationServer.class).initialize();
        container.select(PropertyServerUtil.class);
        container.select(Bootstrap.class).get().init();
    }
}