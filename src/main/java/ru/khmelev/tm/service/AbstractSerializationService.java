package ru.khmelev.tm.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.eclipse.persistence.jaxb.MarshallerProperties;
import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.api.ISerializationService;
import ru.khmelev.tm.entity.Identifiable;
import ru.khmelev.tm.repository.IdentifiableRepository;
import ru.khmelev.tm.service.util.EntityListJAXB;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class AbstractSerializationService<T extends Identifiable> implements ISerializationService {

    protected abstract TypeReference getTypeReference();

    @NotNull
    private String path = new File("").getAbsolutePath() + "/serialization/";

    private IdentifiableRepository<T> identifiableRepository;

    public AbstractSerializationService(IdentifiableRepository<T> identifiableRepository) {
        this.identifiableRepository = identifiableRepository;
    }

    @Override
    public void serializationSave(@NotNull final String userId) {
        @NotNull final Collection<T> list = identifiableRepository.findAll(userId);

        if (!createDir() && list.size() == 0) {
            return;
        }

        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(path + getClass().getSimpleName() + "Serialization.out"))) {
            for (T entity : list) {
                objectOutputStream.writeObject(entity);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void serializationLoad(@NotNull String userId) throws IOException, ClassNotFoundException {
        @NotNull final Collection<T> list = new ArrayList<>();

        try (@NotNull final ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(path + getClass().getSimpleName() + "Serialization.out"))) {
            while (true) {
                @NotNull T entity = (T) objectInputStream.readObject();
                list.add(entity);
            }
        } catch (EOFException ignored) {
        }

        if (list.size() == 0) {
            return;
        }

        for (T entity : list) {
            identifiableRepository.persist(entity.getId(), entity);
        }
    }

    @Override
    public void jaxbXmlSave(String userId) throws JAXBException {
        @NotNull final EntityListJAXB<T> jaxbList = new EntityListJAXB<>();
        jaxbList.setList((List) identifiableRepository.findAll(userId));

        @NotNull final JAXBContext jaxbContext = JAXBContext.newInstance(jaxbList.getClass());
        @NotNull final Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        createDir();
        marshaller.marshal(jaxbList, new File(path + getClass().getSimpleName() + "JAXB.xml"));
    }

    @Override
    public void jaxbXmlLoad(String userId) throws JAXBException {
        @NotNull EntityListJAXB<T> jaxbList = new EntityListJAXB<>();

        @NotNull final JAXBContext jaxbContext = JAXBContext.newInstance(jaxbList.getClass());
        @NotNull final Unmarshaller un = jaxbContext.createUnmarshaller();
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        jaxbList = (EntityListJAXB<T>) unmarshaller.unmarshal(new File(path + getClass().getSimpleName() + "JAXB.xml"));

        for (T entity : jaxbList.getList()) {
            identifiableRepository.persist(entity.getId(), entity);
        }
    }

    @Override
    public void jaxbJSONSave(String userId) throws JAXBException {
        System.setProperty("javax.xml.bind.context.factory", "org.eclipse.persistence.jaxb.JAXBContextFactory");

        @NotNull final EntityListJAXB<T> jaxbList = new EntityListJAXB<>();
        jaxbList.setList((List) identifiableRepository.findAll(userId));

        @NotNull final JAXBContext jaxbContext = JAXBContext.newInstance(jaxbList.getClass());
        @NotNull final Marshaller marshaller = jaxbContext.createMarshaller();

        marshaller.setProperty(MarshallerProperties.MEDIA_TYPE, "application/json");

        marshaller.setProperty(MarshallerProperties.JSON_INCLUDE_ROOT, true);

        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        createDir();
        marshaller.marshal(jaxbList, new File(path + getClass().getSimpleName() + "JAXB.json"));
    }

    @Override
    public void jaxbJSONLoad(String userId) throws JAXBException {
        System.setProperty("javax.xml.bind.context.factory", "org.eclipse.persistence.jaxb.JAXBContextFactory");

        @NotNull EntityListJAXB<T> jaxbList = new EntityListJAXB<>();

        @NotNull final JAXBContext jaxbContext = JAXBContext.newInstance(jaxbList.getClass());
        @NotNull final Unmarshaller un = jaxbContext.createUnmarshaller();
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        unmarshaller.setProperty(MarshallerProperties.MEDIA_TYPE, "application/json");

        unmarshaller.setProperty(MarshallerProperties.JSON_INCLUDE_ROOT, true);

        jaxbList = (EntityListJAXB<T>) unmarshaller.unmarshal(new File(path + getClass().getSimpleName() + "JAXB.json"));

        for (T entity : jaxbList.getList()) {
            identifiableRepository.persist(entity.getId(), entity);
        }
    }

    @Override
    public void fasterXmlSaveXML(String userId) throws IOException {
        @NotNull final XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.writerWithDefaultPrettyPrinter().writeValue(new File(path + getClass().getSimpleName() + "FasterXml.xml"), identifiableRepository.findAll(userId));
    }

    @Override
    public void fasterXmlLoadXML(String userId) throws IOException {
        @NotNull final XmlMapper xmlMapper = new XmlMapper();
        Collection<T> col = xmlMapper.readValue(new File(path + getClass().getSimpleName() + "FasterXml.xml"), getTypeReference());
        for (T entity : col) {
            identifiableRepository.persist(entity.getId(), entity);
        }
    }

    @Override
    public void fasterXmlSaveJSON(String userId) throws IOException {
        @NotNull final ObjectMapper jsonMapper = new ObjectMapper();
        jsonMapper.writerWithDefaultPrettyPrinter().writeValue(new File(path + getClass().getSimpleName() + "FasterXml.json"), identifiableRepository.findAll(userId));
    }

    @Override
    public void fasterXmlLoadJSON(String userId) throws IOException {
        @NotNull final ObjectMapper jsonMapper = new ObjectMapper();
        @NotNull final Collection<T> col = jsonMapper.readValue(new File(path + getClass().getSimpleName() + "FasterXml.json"), getTypeReference());
        for (T entity : col) {
            identifiableRepository.persist(entity.getId(), entity);
        }
    }

    private boolean createDir() {
        try {
            Files.createDirectories(Paths.get(path));
            return true;
        } catch (IOException e) {
            System.out.println("Невозможно создать директорию для сериализации");
            return false;
        }
    }
}