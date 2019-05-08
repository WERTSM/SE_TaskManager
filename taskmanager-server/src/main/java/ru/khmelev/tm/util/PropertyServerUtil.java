package ru.khmelev.tm.util;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.enterprise.context.ApplicationScoped;
import java.util.Properties;

@ApplicationScoped
public class PropertyServerUtil {

    public PropertyServerUtil() {
        loadProperty();
    }

    @NotNull
    private final Properties properties = new Properties();

    @SneakyThrows
    private void loadProperty() {
        properties.load(PropertyServerUtil.class.getResourceAsStream("/server.properties"));

        @Nullable final String port = System.getProperty("server.port");
        if (port != null && !port.isEmpty()) {
            properties.setProperty("server.port", port);
        }
    }

    public String getHost() {
        return properties.getProperty("server.host");
    }

    public String getPort() {
        return properties.getProperty("server.port");
    }
}