package ru.khmelev.tm.api;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.entity.Identifiable;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface ISerializationService<T extends Identifiable> {

    void serializationSave(@NotNull String userId);

    void serializationLoad(@NotNull String userId) throws IOException, ClassNotFoundException;

    void jaxbXmlSave(String userId) throws JAXBException;

    void jaxbXmlLoad(String userId) throws JAXBException;

    void jaxbJSONSave(String userId) throws JAXBException;

    void jaxbJSONLoad(String userId) throws JAXBException;

    void fasterXmlSaveXML(String userId) throws IOException;

    void fasterXmlLoadXML(String userId) throws IOException;

    void fasterXmlSaveJSON(String userId) throws IOException;

    void fasterXmlLoadJSON(String userId) throws IOException;
}
