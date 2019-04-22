package ru.khmelev.tm.service;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.api.service.IUserService;
import ru.khmelev.tm.dto.UserDTO;
import ru.khmelev.tm.entity.User;
import ru.khmelev.tm.exception.ServiceException;
import ru.khmelev.tm.repository.UserRepository;
import ru.khmelev.tm.util.HibernateUtil;
import ru.khmelev.tm.util.PasswordHashUtil;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@ApplicationScoped
public class UserService implements IUserService {

    @Inject
    private EntityManagerFactory entityManagerFactory;

    private EntityManager entityManager;

    @Override
    public void createEntity(@NotNull final String id, @NotNull final UserDTO userDTO) {
        try {
            entityManager = entityManagerFactory.createEntityManager();
            @NotNull final EntityTransaction transaction = entityManager.getTransaction();
            @NotNull final UserRepository userRepository = new UserRepository(entityManager);
            transaction.begin();

            @NotNull final User user = new User();
            user.setId(id);
            fromDTOToUser(userDTO, user);

            userRepository.persist(user);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new ServiceException(e);

        } finally {
            entityManager.close();
        }
    }

    @NotNull
    @Override
    public UserDTO findEntity(@NotNull final String id) {
        if (id.isEmpty()) throw new ServiceException();

        try {
            entityManager = entityManagerFactory.createEntityManager();
            @NotNull final EntityTransaction transaction = entityManager.getTransaction();
            @NotNull final UserRepository userRepository = new UserRepository(entityManager);
            transaction.begin();
            @NotNull final User user = userRepository.findOne(id);
            return fromUserToDTO(user);
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new ServiceException(e);
        } finally {
            entityManager.close();
        }
    }

    @NotNull
    @Override
    public Collection<UserDTO> findAll() {
        try {
            entityManager = entityManagerFactory.createEntityManager();
            @NotNull final EntityTransaction transaction = entityManager.getTransaction();
            @NotNull final UserRepository userRepository = new UserRepository(entityManager);
            transaction.begin();

            @NotNull final Collection<User> list = userRepository.findAll();
            @NotNull final List<UserDTO> listDTO = new ArrayList<>();
            for (User user : list) {
                listDTO.add(fromUserToDTO(user));
            }
            transaction.commit();
            return listDTO;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new ServiceException(e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void editEntity(@NotNull final String id, @NotNull UserDTO userDTO) {
        if (id.isEmpty()) throw new ServiceException();

        try {
            entityManager = entityManagerFactory.createEntityManager();
            @NotNull final EntityTransaction transaction = entityManager.getTransaction();
            @NotNull final UserRepository userRepository = new UserRepository(entityManager);
            transaction.begin();

            @NotNull final User user = userRepository.findOne(id);
            userRepository.merge(fromDTOToUser(userDTO, user));
            transaction.commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new ServiceException(e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void removeEntity(@NotNull final String id) {
        if (id.isEmpty()) throw new ServiceException();

        try {
            entityManager = entityManagerFactory.createEntityManager();
            @NotNull final EntityTransaction transaction = entityManager.getTransaction();
            @NotNull final UserRepository userRepository = new UserRepository(entityManager);
            transaction.begin();
            userRepository.remove(userRepository.findOne(id));
            transaction.commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new ServiceException(e);
        } finally {
            entityManager.close();
        }
    }

    @NotNull
    @Override
    public String getId(@NotNull final UserDTO userDTO) {
        return userDTO.getId();
    }

    @NotNull
    @Override
    public String getName(@NotNull final UserDTO userDTO) {
        return userDTO.getLogin();
    }

    @Override
    public void userSetPassword(@NotNull final String login, @NotNull final String pass) {
        if (login.isEmpty() || pass.isEmpty()) throw new ServiceException();

        @NotNull final Collection<UserDTO> users = findAll();
        for (UserDTO userDTO : users) {
            if (userDTO.getLogin().equals(login)) {
                @NotNull final String password = Objects.requireNonNull(PasswordHashUtil.md5(pass));
                userDTO.setHashPassword(password);
                editEntity(userDTO.getId(), userDTO);
            }
        }
    }

    @NotNull
    @Override
    public UserDTO getUserFromSession(@NotNull final String userId) {
        return findEntity(userId);
    }

    @NotNull
    private User fromDTOToUser(@NotNull final UserDTO userDTO, @NotNull final User user) {
        user.setLogin(userDTO.getLogin());
        user.setHashPassword(userDTO.getHashPassword());
        user.setRole(userDTO.getRole());
        return user;
    }

    @NotNull
    private UserDTO fromUserToDTO(@NotNull final User user) {
        @NotNull final UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setLogin(user.getLogin());
        userDTO.setHashPassword(user.getHashPassword());
        userDTO.setRole(user.getRole());
        return userDTO;
    }
}