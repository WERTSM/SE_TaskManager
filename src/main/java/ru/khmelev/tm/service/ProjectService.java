package ru.khmelev.tm.service;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.api.IProjectRepository;
import ru.khmelev.tm.api.IProjectService;
import ru.khmelev.tm.entity.Project;
import ru.khmelev.tm.entity.Sort;
import ru.khmelev.tm.service.util.SortedEntity;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ProjectService extends AbstractEntityService<Project> implements IProjectService {

    private IProjectRepository projectRepository;

    @NotNull
    private String path = new File("").getAbsolutePath() + "/serialization/";

    {

        try {
            Files.createDirectories(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ProjectService(final IProjectRepository projectRepository) {
        super(projectRepository);
        this.projectRepository = projectRepository;
    }

    @Override
    public void sort(@NotNull List<Project> list, @NotNull Sort sortParameter) {
        new SortedEntity<Project>().sort(list, sortParameter);
    }

    public void serializationSave(String userId) throws IOException {

        Collection<Project> list = projectRepository.findAll(userId);

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(path + getClass().getSimpleName() + ".out"));

        for (Project prw : list) {
            System.out.println("WWWWWWWWWWWW");
            try {
                objectOutputStream.writeObject(prw);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        objectOutputStream.close();
    }

    public void serializationLoad(String userId) throws IOException, ClassNotFoundException {
        System.out.println("1111111111111111111");
        Collection<Project> list = new ArrayList<>();
        System.out.println("1111111111111111111");

        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(path + getClass().getSimpleName() + ".out"))) {
            while (true) {
                Project pr = (Project) objectInputStream.readObject();
                list.add(pr);
            }
        } catch (EOFException ignored) {
        }

        for (Project pr2 : list) {
            projectRepository.persist(pr2.getId(), pr2);
        }
    }
}