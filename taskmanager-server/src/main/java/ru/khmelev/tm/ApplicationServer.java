package ru.khmelev.tm;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.khmelev.tm.bootstrap.Bootstrap;
import ru.khmelev.tm.util.SpringJPAConfigUtil;

public final class ApplicationServer {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringJPAConfigUtil.class);
        applicationContext.getBean(Bootstrap.class).init();
    }
}