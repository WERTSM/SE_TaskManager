package ru.khmelev.tm.api.service;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.entity.Identifiable;

public interface ISerializationService<T extends Identifiable> {

    void serializationSave(@NotNull final String userId);

    void serializationLoad(@NotNull final String userId);

    void jaxbXmlSave(@NotNull final String userId);

    void jaxbXmlLoad(@NotNull final String userId);

    void jaxbJSONSave(@NotNull final String userId);

    void jaxbJSONLoad(@NotNull final String userId);

    void fasterXmlSaveXML(@NotNull final String userId);

    void fasterXmlLoadXML(@NotNull final String userId);

    void fasterXmlSaveJSON(@NotNull final String userId);

    void fasterXmlLoadJSON(@NotNull final String userId);
}
