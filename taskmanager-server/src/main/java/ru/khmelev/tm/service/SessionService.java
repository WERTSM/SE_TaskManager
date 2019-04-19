package ru.khmelev.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.khmelev.tm.api.service.ISessionService;
import ru.khmelev.tm.entity.Session;
import ru.khmelev.tm.entity.dto.SessionDTO;
import ru.khmelev.tm.exception.ServiceException;
import ru.khmelev.tm.repository.SessionRepository;
import ru.khmelev.tm.repository.UserRepository;
import ru.khmelev.tm.service.util.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class SessionService implements ISessionService {

    @NotNull
    private final EntityManagerFactory entityManagerFactory = HibernateUtil.getEntityManagerFactory();

    private EntityManager entityManager;

    @Override
    public void createEntity(@NotNull final String id, @NotNull final SessionDTO sessionDTO) {
        try {
            entityManager = entityManagerFactory.createEntityManager();
            @NotNull final EntityTransaction transaction = entityManager.getTransaction();
            @NotNull final SessionRepository sessionRepository = new SessionRepository(entityManager);
            transaction.begin();

            @NotNull final Session session = new Session();
            session.setId(id);
            fromDTOToSession(sessionDTO, session);

            sessionRepository.persist(session);
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
    public SessionDTO findEntity(@NotNull final String id) {
        if (id.isEmpty()) throw new ServiceException();

        try {
            entityManager = entityManagerFactory.createEntityManager();
            @NotNull final EntityTransaction transaction = entityManager.getTransaction();
            @NotNull final SessionRepository sessionRepository = new SessionRepository(entityManager);
            transaction.begin();
            @NotNull final Session session = sessionRepository.findOne(id);
            return fromSessionToDTO(session);
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new ServiceException(e);
        } finally {
            entityManager.close();
        }
    }

    @NotNull
    @Override
    public Collection<SessionDTO> findAll() {
        try {
            entityManager = entityManagerFactory.createEntityManager();
            @NotNull final EntityTransaction transaction = entityManager.getTransaction();
            @NotNull final SessionRepository sessionRepository = new SessionRepository(entityManager);
            transaction.begin();

            @NotNull final Collection<Session> list = sessionRepository.findAll();
            @NotNull final List<SessionDTO> listDTO = new ArrayList<>();
            for (Session session : list) {
                listDTO.add(fromSessionToDTO(session));
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
    public void editEntity(@NotNull final String id, @NotNull SessionDTO sessionDTO) {
        if (id.isEmpty()) throw new ServiceException();

        try {
            entityManager = entityManagerFactory.createEntityManager();
            @NotNull final EntityTransaction transaction = entityManager.getTransaction();
            @NotNull final SessionRepository sessionRepository = new SessionRepository(entityManager);
            transaction.begin();

            @NotNull final Session session = sessionRepository.findOne(id);
            sessionRepository.merge(fromDTOToSession(sessionDTO, session));
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
            @NotNull final SessionRepository sessionRepository = new SessionRepository(entityManager);
            transaction.begin();
            sessionRepository.remove(sessionRepository.findOne(id));
            transaction.commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new ServiceException(e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void clearEntity() {
        try {
            entityManager = entityManagerFactory.createEntityManager();
            @NotNull final EntityTransaction transaction = entityManager.getTransaction();
            @NotNull final SessionRepository sessionRepository = new SessionRepository(entityManager);
            transaction.begin();
            sessionRepository.removeAll();
            transaction.commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new ServiceException(e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void setSession(@NotNull final SessionDTO sessionDTO) {
        createEntity(sessionDTO.getId(), sessionDTO);
    }

    @Override
    public void removeSession(@NotNull final SessionDTO sessionDTO) {
        removeEntity(sessionDTO.getUserId());
    }

    @Override
    public void checkSession(@Nullable final SessionDTO sessionDTO) {
        if (!Objects.requireNonNull(sessionDTO).getSignature().equals(findEntity(sessionDTO.getId()).getSignature())) {
            throw new ServiceException();
        }
    }

    private Session fromDTOToSession(@NotNull final SessionDTO sessionDTO, @NotNull final Session session) {
        @NotNull final UserRepository userRepository = new UserRepository(entityManager);
        session.setSignature(sessionDTO.getSignature());
        session.setDateCreate(sessionDTO.getDateCreate());
        session.setUser(userRepository.findOne(sessionDTO.getUserId()));
        return session;
    }

    private SessionDTO fromSessionToDTO(@NotNull final Session session) {
        @NotNull final SessionDTO sessionDTO = new SessionDTO();
        sessionDTO.setId(session.getId());
        sessionDTO.setSignature(session.getSignature());
        sessionDTO.setDateCreate(session.getDateCreate());
        sessionDTO.setUserId(session.getUser().getId());
        return sessionDTO;
    }
}