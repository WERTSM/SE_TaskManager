package ru.khmelev.tm.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.eclipse.persistence.jaxb.MarshallerProperties;
import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.api.repository.ISerializationRepository;
import ru.khmelev.tm.entity.Identifiable;
import ru.khmelev.tm.service.util.EntityListJAXB;

import javax.xml.bind.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
        jaxbList.setList((List) serializationRepository.findAll(userId));

        @NotNull JAXBContext jaxbContext = null;
        try {
            jaxbContext = JAXBContext.newInstance(jaxbList.getClass());
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        @NotNull Marshaller marshaller = null;
        try {
            assert jaxbContext != null;
            marshaller = jaxbContext.createMarshaller();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        try {
            assert marshaller != null;
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        } catch (PropertyException e) {
            e.printStackTrace();
        }

        createDir();
        try {
            marshaller.marshal(jaxbList, new File(path + getClass().getSimpleName() + "JAXB.xml"));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public void jaxbXmlLoad(@NotNull final String userId) {
        @NotNull EntityListJAXB<T> jaxbList = new EntityListJAXB<>();

        @NotNull JAXBContext jaxbContext = null;
        try {
            jaxbContext = JAXBContext.newInstance(jaxbList.getClass());
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        try {
            assert jaxbContext != null;
            @NotNull final Unmarshaller un = jaxbContext.createUnmarshaller();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        Unmarshaller unmarshaller = null;
        try {
            unmarshaller = jaxbContext.createUnmarshaller();
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        try {
            assert unmarshaller != null;
            jaxbList = (EntityListJAXB<T>) unmarshaller.unmarshal(new File(path + getClass().getSimpleName() + "JAXB.xml"));
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        for (T entity : jaxbList.getList()) {
            serializationRepository.persist(entity.getId(), entity);
        }
    }

    public void jaxbJSONSave(@NotNull final String userId) {
        System.setProperty("javax.xml.bind.context.factory", "org.eclipse.persistence.jaxb.JAXBContextFactory");

        @NotNull final EntityListJAXB<T> jaxbList = new EntityListJAXB<>();
        jaxbList.setList((List) serializationRepository.findAll(userId));

        @NotNull JAXBContext jaxbContext = null;
        try {
            jaxbContext = JAXBContext.newInstance(jaxbList.getClass());
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        @NotNull Marshaller marshaller = null;
        try {
            assert jaxbContext != null;
            marshaller = jaxbContext.createMarshaller();
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        try {
            assert marshaller != null;
            marshaller.setProperty(MarshallerProperties.MEDIA_TYPE, "application/json");
        } catch (PropertyException e) {
            e.printStackTrace();
        }

        try {
            marshaller.setProperty(MarshallerProperties.JSON_INCLUDE_ROOT, true);
        } catch (PropertyException e) {
            e.printStackTrace();
        }

        try {
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        } catch (PropertyException e) {
            e.printStackTrace();
        }

        createDir();
        try {
            marshaller.marshal(jaxbList, new File(path + getClass().getSimpleName() + "JAXB.json"));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public void jaxbJSONLoad(@NotNull final String userId) {
        System.setProperty("javax.xml.bind.context.factory", "org.eclipse.persistence.jaxb.JAXBContextFactory");

        @NotNull EntityListJAXB<T> jaxbList = new EntityListJAXB<>();

        @NotNull JAXBContext jaxbContext = null;
        try {
            jaxbContext = JAXBContext.newInstance(jaxbList.getClass());
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        try {
            assert jaxbContext != null;
            @NotNull final Unmarshaller un = jaxbContext.createUnmarshaller();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        Unmarshaller unmarshaller = null;
        try {
            assert false;
            unmarshaller = jaxbContext.createUnmarshaller();
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        try {
            assert unmarshaller != null;
            unmarshaller.setProperty(MarshallerProperties.MEDIA_TYPE, "application/json");
        } catch (PropertyException e) {
            e.printStackTrace();
        }

        try {
            unmarshaller.setProperty(MarshallerProperties.JSON_INCLUDE_ROOT, true);
        } catch (PropertyException e) {
            e.printStackTrace();
        }

        try {
            jaxbList = (EntityListJAXB<T>) unmarshaller.unmarshal(new File(path + getClass().getSimpleName() + "JAXB.json"));
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        for (T entity : jaxbList.getList()) {
            serializationRepository.persist(entity.getId(), entity);
        }
    }

    public void fasterXmlSaveXML(@NotNull final String userId) {
        @NotNull final XmlMapper xmlMapper = new XmlMapper();
        try {
            xmlMapper.writerWithDefaultPrettyPrinter().writeValue(new File(path + getClass().getSimpleName() + "FasterXml.xml"), serializationRepository.findAll(userId));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void fasterXmlLoadXML(@NotNull final String userId){
        @NotNull final XmlMapper xmlMapper = new XmlMapper();
        Collection<T> col = null;
        try {
            col = xmlMapper.readValue(new File(path + getClass().getSimpleName() + "FasterXml.xml"), getTypeReference());
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (T entity : col) {
            serializationRepository.persist(entity.getId(), entity);
        }
    }

    public void fasterXmlSaveJSON(@NotNull final String userId) {
        @NotNull final ObjectMapper jsonMapper = new ObjectMapper();
        try {
            jsonMapper.writerWithDefaultPrettyPrinter().writeValue(new File(path + getClass().getSimpleName() + "FasterXml.json"), serializationRepository.findAll(userId));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void fasterXmlLoadJSON(@NotNull final String userId) {
        @NotNull final ObjectMapper jsonMapper = new ObjectMapper();
        @NotNull Collection<T> col = null;
        try {
            col = jsonMapper.readValue(new File(path + getClass().getSimpleName() + "FasterXml.json"), getTypeReference());
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert col != null;
        for (T entity : col) {
            serializationRepository.persist(entity.getId(), entity);
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