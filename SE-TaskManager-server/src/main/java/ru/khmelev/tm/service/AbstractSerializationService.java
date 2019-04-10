package ru.khmelev.tm.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.eclipse.persistence.jaxb.MarshallerProperties;
import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.api.repository.ISerializationRepository;
import ru.khmelev.tm.entity.Identifiable;
import ru.khmelev.tm.exception.ServiceException;
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

public abstract class AbstractSerializationService<T extends Identifiable> {

    @NotNull
    private final String path = new File("").getAbsolutePath() + "/serialization/";

    @NotNull
    private final ISerializationRepository<T> serializationRepository;

    AbstractSerializationService(@NotNull final ISerializationRepository<T> serializationRepository) {
        this.serializationRepository = serializationRepository;
    }

    protected abstract TypeReference getTypeReference();

    public void serializationSave(@NotNull final String userId) {
        @NotNull final Collection<T> list = serializationRepository.findAll(userId);

        if (!createDir() && list.size() == 0) {
            return;
        }

        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(path + getClass().getSimpleName() + "Serialization.out"))) {
            for (T entity : list) {
                objectOutputStream.writeObject(entity);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new ServiceException();
        }
    }

    public void serializationLoad(@NotNull String userId) {
        @NotNull final Collection<T> list = new ArrayList<>();

        try (@NotNull final ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(path + getClass().getSimpleName() + "Serialization.out"))) {
            while (true) {
                @NotNull T entity = (T) objectInputStream.readObject();
                list.add(entity);
            }
        } catch (EOFException ignored) {
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
            throw new ServiceException();
        }

        if (list.size() == 0) {
            return;
        }

        for (T entity : list) {
            serializationRepository.persist(entity.getId(), entity);
        }
    }

    public void jaxbXmlSave(@NotNull final String userId) {
        @NotNull final EntityListJAXB<T> jaxbList = new EntityListJAXB<>();
        try {
            jaxbList.setList(new ArrayList(serializationRepository.findAll(userId)));

            @NotNull JAXBContext jaxbContext = JAXBContext.newInstance(jaxbList.getClass());
            @NotNull Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            createDir();
            marshaller.marshal(jaxbList, new File(path + getClass().getSimpleName() + "JAXB.xml"));

        } catch (JAXBException e) {
            e.printStackTrace();
            throw new ServiceException();
        }
    }

    public void jaxbXmlLoad(@NotNull final String userId) {
        @NotNull EntityListJAXB<T> jaxbList = new EntityListJAXB<>();

        try {
            @NotNull JAXBContext jaxbContext = JAXBContext.newInstance(jaxbList.getClass());
            @NotNull final Unmarshaller un = jaxbContext.createUnmarshaller();

            Unmarshaller unmarshaller = null;
            unmarshaller = jaxbContext.createUnmarshaller();

            jaxbList = (EntityListJAXB<T>) unmarshaller.unmarshal(new File(path + getClass().getSimpleName() + "JAXB.xml"));


            for (T entity : jaxbList.getList()) {
                serializationRepository.persist(entity.getId(), entity);
            }
        } catch (JAXBException e) {
            e.printStackTrace();
            throw new ServiceException();
        }
    }

    public void jaxbJSONSave(@NotNull final String userId) {
        System.setProperty("javax.xml.bind.context.factory", "org.eclipse.persistence.jaxb.JAXBContextFactory");

        @NotNull final EntityListJAXB<T> jaxbList = new EntityListJAXB<>();

        try {
            jaxbList.setList(new ArrayList(serializationRepository.findAll(userId)));

            @NotNull JAXBContext jaxbContext = JAXBContext.newInstance(jaxbList.getClass());
            @NotNull Marshaller marshaller = jaxbContext.createMarshaller();

            marshaller.setProperty(MarshallerProperties.MEDIA_TYPE, "application/json");
            marshaller.setProperty(MarshallerProperties.JSON_INCLUDE_ROOT, true);
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            createDir();
            marshaller.marshal(jaxbList, new File(path + getClass().getSimpleName() + "JAXB.json"));
        } catch (JAXBException e) {
            e.printStackTrace();
            throw new ServiceException();
        }
    }

    public void jaxbJSONLoad(@NotNull final String userId) {
        System.setProperty("javax.xml.bind.context.factory", "org.eclipse.persistence.jaxb.JAXBContextFactory");

        @NotNull EntityListJAXB<T> jaxbList = new EntityListJAXB<>();

        try {
            @NotNull JAXBContext jaxbContext = JAXBContext.newInstance(jaxbList.getClass());
            @NotNull final Unmarshaller un = jaxbContext.createUnmarshaller();

            Unmarshaller unmarshaller = null;
            unmarshaller = jaxbContext.createUnmarshaller();
            unmarshaller.setProperty(MarshallerProperties.MEDIA_TYPE, "application/json");

            unmarshaller.setProperty(MarshallerProperties.JSON_INCLUDE_ROOT, true);
            jaxbList = (EntityListJAXB<T>) unmarshaller.unmarshal(new File(path + getClass().getSimpleName() + "JAXB.json"));


            for (T entity : jaxbList.getList()) {
                serializationRepository.persist(entity.getId(), entity);
            }
        } catch (JAXBException e) {
            e.printStackTrace();
            throw new ServiceException();
        }
    }

    public void fasterXmlSaveXML(@NotNull final String userId) {
        @NotNull final XmlMapper xmlMapper = new XmlMapper();
        try {
            xmlMapper.writerWithDefaultPrettyPrinter().writeValue(new File(path + getClass().getSimpleName() + "FasterXml.xml"), serializationRepository.findAll(userId));
        } catch (IOException e) {
            e.printStackTrace();
            throw new ServiceException();
        }
    }

    public void fasterXmlLoadXML(@NotNull final String userId) {
        @NotNull final XmlMapper xmlMapper = new XmlMapper();
        try {
            Collection<T> col = xmlMapper.readValue(new File(path + getClass().getSimpleName() + "FasterXml.xml"), getTypeReference());

            for (T entity : col) {
                serializationRepository.persist(entity.getId(), entity);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new ServiceException();
        }
    }

    public void fasterXmlSaveJSON(@NotNull final String userId) {
        @NotNull final ObjectMapper jsonMapper = new ObjectMapper();
        try {
            jsonMapper.writerWithDefaultPrettyPrinter().writeValue(new File(path + getClass().getSimpleName() + "FasterXml.json"), serializationRepository.findAll(userId));
        } catch (IOException e) {
            e.printStackTrace();
            throw new ServiceException();
        }
    }

    public void fasterXmlLoadJSON(@NotNull final String userId) {
        @NotNull final ObjectMapper jsonMapper = new ObjectMapper();
        try {
            @NotNull Collection<T> col = jsonMapper.readValue(new File(path + getClass().getSimpleName() + "FasterXml.json"), getTypeReference());
            for (T entity : col) {
                serializationRepository.persist(entity.getId(), entity);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new ServiceException();
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