package ru.khmelev.tm.service;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.api.ISessionRepository;
import ru.khmelev.tm.api.ISessionService;
import ru.khmelev.tm.entity.Session;
import ru.khmelev.tm.entity.User;
import ru.khmelev.tm.exception.ServiceException;

public class SessionService extends AbstractIdentifiableService<Session> implements ISessionService {

    private ISessionRepository sessionRepository;

    private String signatureRepository;

    private String password;

    public SessionService(ISessionRepository sessionRepository) {
        super(sessionRepository);
        this.sessionRepository = sessionRepository;
    }

    @Override
    public void setSession(Session session) {
        createEntity(session.getUserId(), session);
    }

    @Override
    public void removeSession(Session session) {
        removeEntity(session.getUserId());
    }

    @Override
    public void checkSession(@NotNull final Session session) {
        signatureRepository = sessionRepository.findOne(session.getUserId()).getSignature();
        if (!session.getSignature().equals(signatureRepository)) {
            throw new ServiceException();
        }
    }

    @NotNull
    @Override
    public String getId(@NotNull User user) {
        return user.getId();
    }

    @NotNull
    @Override
    public String getName(@NotNull User user) {
        return user.getLogin();
    }


}