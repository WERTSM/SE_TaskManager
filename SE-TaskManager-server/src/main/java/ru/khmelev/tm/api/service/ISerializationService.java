package ru.khmelev.tm.api.service;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.entity.Identifiable;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface ISerializationService<T extends Identifiable> {

    void serializationSave(@NotNull final String userId);

    void serializationLoad(@NotNull final String userId) throws IOException, ClassNotFoundException;

    void jaxbXmlSave(@NotNull final String userId) throws JAXBException;

    void jaxbXmlLoad(@NotNull final String userId) throws JAXBException;

    void jaxbJSONSave(@NotNull final String userId) throws JAXBException;

    void jaxbJSONLoad(@NotNull final String userId) throws JAXBException;

    void fasterXmlSaveXML(@NotNull final String userId) throws IOException;

    void fasterXmlLoadXML(@NotNull final String userId) throws IOException;

    void fasterXmlSaveJSON(@NotNull final String userId) throws IOException;

    void fasterXmlLoadJSON(@NotNull final String userId) throws IOException;
}
