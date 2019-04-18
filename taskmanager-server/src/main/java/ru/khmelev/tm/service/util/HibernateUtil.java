package ru.khmelev.tm.service.util;

import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.khmelev.tm.entity.Project;
import ru.khmelev.tm.entity.Session;
import ru.khmelev.tm.entity.Task;
import ru.khmelev.tm.entity.User;

import javax.persistence.EntityManagerFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class HibernateUtil {

    @Nullable
    private static EntityManagerFactory entityManagerFactory;

    @NotNull
    public static EntityManagerFactory getEntityManagerFactory() {
        if (entityManagerFactory == null) {
            entityManagerFactory = new HibernateUtil().factory();
        }
        return entityManagerFactory;
    }

    @NotNull
    private Properties getProperties() throws Exception {
        @NotNull final ClassLoader loader = Thread.currentThread().getContextClassLoader();
        @NotNull final Properties propertyService = new Properties();
        propertyService.load(loader.getResourceAsStream("database/database.properties"));
        return propertyService;
    }

    private EntityManagerFactory factory() {
        final Map<String, String> settings = new HashMap<>();
        @NotNull final Properties propertyService;
        try {
            propertyService = getProperties();
            settings.put(Environment.DRIVER, propertyService.getProperty("database.driver"));
            settings.put(Environment.URL, propertyService.getProperty("database.url"));
            settings.put(Environment.USER, propertyService.getProperty("database.user"));
            settings.put(Environment.PASS, propertyService.getProperty("database.password"));
            settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5InnoDBDialect");
            settings.put(Environment.HBM2DDL_AUTO, "update");
            settings.put(Environment.SHOW_SQL, "true");
        } catch (Exception e) {
            e.printStackTrace();
        }
        final StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();
        registryBuilder.applySettings(settings);
        final StandardServiceRegistry registry = registryBuilder.build();
        final MetadataSources sources = new MetadataSources(registry);
        sources.addAnnotatedClass(Project.class);
        sources.addAnnotatedClass(Task.class);

        sources.addAnnotatedClass(User.class);
        sources.addAnnotatedClass(Session.class);
        final Metadata metadata = sources.getMetadataBuilder().build();
        return metadata.getSessionFactoryBuilder().build();
    }
}