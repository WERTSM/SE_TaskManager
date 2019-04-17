package ru.khmelev.tm.service;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.khmelev.tm.api.repository.mybatis.ISessionRepositoryMyBatis;
import ru.khmelev.tm.api.service.ISessionService;
import ru.khmelev.tm.entity.Session;
import ru.khmelev.tm.exception.ServiceException;
import ru.khmelev.tm.service.util.MyBatisConfig;

import java.util.Collection;
import java.util.Objects;

public class SessionService implements ISessionService {

    @NotNull
    private final SqlSessionFactory sqlSessionFactory;

    public SessionService() {
        this.sqlSessionFactory = MyBatisConfig.getSqlSessionFactory();
    }

    @Override
    public void createEntity(@NotNull final String id, @NotNull final Session session) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            ISessionRepositoryMyBatis sessionRepositoryMyBatis = sqlSession.getMapper(ISessionRepositoryMyBatis.class);
            sessionRepositoryMyBatis.persist(id, session);
            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
            throw new ServiceException(e);
        } finally {
            sqlSession.close();
        }
    }

    @NotNull
    @Override
    public Session findEntity(@NotNull final String id) {
        if (id.isEmpty()) throw new ServiceException();

        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ISessionRepositoryMyBatis sessionRepositoryMyBatis = sqlSession.getMapper(ISessionRepositoryMyBatis.class);
            return sessionRepositoryMyBatis.findOne(id);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @NotNull
    @Override
    public Collection<Session> findAll() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ISessionRepositoryMyBatis sessionRepositoryMyBatis = sqlSession.getMapper(ISessionRepositoryMyBatis.class);
            return sessionRepositoryMyBatis.findAll();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void editEntity(@NotNull final String id, @NotNull Session session) {
        if (id.isEmpty()) throw new ServiceException();

        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            ISessionRepositoryMyBatis sessionRepositoryMyBatis = sqlSession.getMapper(ISessionRepositoryMyBatis.class);
            sessionRepositoryMyBatis.merge(id, session);
            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
            throw new ServiceException(e);
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public void removeEntity(@NotNull final String id) {
        if (id.isEmpty()) throw new ServiceException();

        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            ISessionRepositoryMyBatis sessionRepositoryMyBatis = sqlSession.getMapper(ISessionRepositoryMyBatis.class);
            sessionRepositoryMyBatis.remove(id);
            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
            throw new ServiceException(e);
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public void clearEntity() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            ISessionRepositoryMyBatis sessionRepositoryMyBatis = sqlSession.getMapper(ISessionRepositoryMyBatis.class);
            sessionRepositoryMyBatis.removeAll();
            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
            throw new ServiceException(e);
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public void setSession(@NotNull final Session session) {
        createEntity(session.getId(), session);
    }

    @Override
    public void removeSession(@NotNull final Session session) {
        removeEntity(session.getUserId());
    }

    @Override
    public void checkSession(@Nullable final Session session) {
        if (!Objects.requireNonNull(session).getSignature().equals(findEntity(session.getId()).getSignature())) {
            throw new ServiceException();
        }
    }
}