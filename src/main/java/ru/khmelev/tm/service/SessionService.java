package ru.khmelev.tm.service;

import com.fasterxml.jackson.core.type.TypeReference;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.khmelev.tm.api.service.IService;
import ru.khmelev.tm.api.repository.ISessionRepository;
import ru.khmelev.tm.api.service.ISessionService;
import ru.khmelev.tm.entity.Session;
import ru.khmelev.tm.entity.User;
import ru.khmelev.tm.exception.ServiceException;

public class SessionService extends AbstractIdentifiableService<Session> implements IService<Session>, ISessionService {

    @NotNull
    private final ISessionRepository sessionRepository;

    private String password;

    public SessionService(@NotNull final ISessionRepository sessionRepository) {
        super(sessionRepository);
        this.sessionRepository = sessionRepository;
    }

    public void setSession(@NotNull final Session session) {
        createEntity(session.getUserId(), session);
    }

    public void removeSession(@NotNull final Session session) {
        removeEntity(session.getUserId());
    }

    public void checkSession(@Nullable final Session session) {
        assert session != null;
        String signatureRepository = sessionRepository.findOne(session.getId()).getSignature();
        if (!session.getSignature().equals(signatureRepository)) {
            throw new ServiceException();
        }
    }

    @NotNull
    public String getId(@NotNull final User user) {
        return user.getId();
    }

    @NotNull
    public String getName(@NotNull final User user) {
        return user.getLogin();
    }

    @Override
    protected TypeReference getTypeReference() {
        throw new ServiceException();
    }
}