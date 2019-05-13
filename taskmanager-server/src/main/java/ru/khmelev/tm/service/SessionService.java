package ru.khmelev.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.khmelev.tm.api.repository.ISessionRepository;
import ru.khmelev.tm.api.repository.IUserRepository;
import ru.khmelev.tm.api.service.ISessionService;
import ru.khmelev.tm.dto.SessionDTO;
import ru.khmelev.tm.entity.Session;
import ru.khmelev.tm.entity.User;
import ru.khmelev.tm.exception.EndpointException;
import ru.khmelev.tm.exception.ServiceException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Service
public class SessionService implements ISessionService {

    @Autowired
    private ISessionRepository sessionRepository;

    @Autowired
    private IUserRepository userRepository;

    @Override
    @Transactional
    public void createEntity(@NotNull final String id, @NotNull final SessionDTO sessionDTO) {
        @NotNull final Session session = new Session();
        session.setId(id);
        fromDTOToSession(sessionDTO, session);
        sessionRepository.save(session);
    }

    @NotNull
    @Override
    @Transactional
    public SessionDTO findEntity(@NotNull final String id) {
        if (id.isEmpty()) throw new ServiceException();
        @NotNull final Session session = sessionRepository.findOne(id);
        return fromSessionToDTO(session);
    }

    @Override
    @Transactional
    public void setUser(@NotNull final String id, @Nullable final User user) {
        @NotNull final Session session = sessionRepository.findOne(id);
        session.setUser(user);
    }

    @NotNull
    @Override
    @Transactional
    public Collection<SessionDTO> findAll() {
        @NotNull final Collection<Session> list = sessionRepository.findAll();
        @NotNull final List<SessionDTO> listDTO = new ArrayList<>();
        for (Session session : list) {
            listDTO.add(fromSessionToDTO(session));
        }
        return listDTO;
    }

    @Override
    @Transactional
    public void editEntity(@NotNull final String id, @NotNull SessionDTO sessionDTO) {
        if (id.isEmpty()) throw new ServiceException();
        @NotNull final Session session = sessionRepository.findOne(id);
        sessionRepository.save(fromDTOToSession(sessionDTO, session));
    }

    @Override
    @Transactional
    public void removeEntity(@NotNull final String id) {
        if (id.isEmpty()) throw new ServiceException();
        sessionRepository.delete(sessionRepository.findOne(id));
    }

    @Override
    @Transactional
    public void clearEntity() {
        sessionRepository.removeAll();
    }

    @Override
    @Transactional
    public void checkSession(@Nullable final SessionDTO sessionDTO) {
        if (!Objects.requireNonNull(sessionDTO).getSignature().equals(findEntity(sessionDTO.getId()).getSignature())) {
            throw new EndpointException();
        }
    }

    private Session fromDTOToSession(@NotNull final SessionDTO sessionDTO, @NotNull final Session session) {
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
        if (session.getUser() == null) {
            sessionDTO.setUserId(null);
        } else {
            sessionDTO.setUserId(session.getUser().getId());
        }
        return sessionDTO;
    }
}