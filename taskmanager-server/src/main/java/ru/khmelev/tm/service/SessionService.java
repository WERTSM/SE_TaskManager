package ru.khmelev.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.khmelev.tm.api.repository.ISessionRepository;
import ru.khmelev.tm.api.service.IService;
import ru.khmelev.tm.api.service.ISessionService;
import ru.khmelev.tm.entity.Session;
import ru.khmelev.tm.entity.User;
import ru.khmelev.tm.exception.ServiceException;
import ru.khmelev.tm.service.util.ConnectionJDBC;

import java.sql.Connection;
import java.sql.SQLException;

public class SessionService extends AbstractIdentifiableService<Session> implements IService<Session>, ISessionService {

    @NotNull
    private final ISessionRepository sessionRepository;

    private String password;

    public SessionService(@NotNull final ISessionRepository sessionRepository) {
        super(sessionRepository);
        this.sessionRepository = sessionRepository;
    }

    public void setSession(@NotNull final Session session) {
        createEntity(session.getId(), session);
    }

    public void removeSession(@NotNull final Session session) {
        removeEntity(session.getUserId());
    }

    public void checkSession(@Nullable final Session session) {
        try (Connection connection = ConnectionJDBC.getConnection()) {
            sessionRepository.setConnection(connection);
            assert session != null;
            String signatureRepository = sessionRepository.findOne(session.getId()).getSignature();
            if (!session.getSignature().equals(signatureRepository)) {
                throw new ServiceException();
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
}