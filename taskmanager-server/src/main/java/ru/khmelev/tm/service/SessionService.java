package ru.khmelev.tm.service;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.api.service.ISessionService;
import ru.khmelev.tm.entity.dto.SessionDTO;

import java.util.Collection;

public class SessionService implements ISessionService {
    @Override
    public void clearEntity() {

    }

    @Override
    public void setSession(@NotNull SessionDTO sessionDTO) {

    }

    @Override
    public void removeSession(@NotNull SessionDTO sessionDTO) {

    }

    @Override
    public void checkSession(@NotNull SessionDTO sessionDTO) {

    }

    @Override
    public void createEntity(@NotNull String id, @NotNull SessionDTO entity) {

    }

    @NotNull
    @Override
    public SessionDTO findEntity(@NotNull String id) {
        return null;
    }

    @Override
    public @NotNull Collection<SessionDTO> findAll() {
        return null;
    }

    @Override
    public void editEntity(@NotNull String id, @NotNull SessionDTO entity) {

    }

    @Override
    public void removeEntity(@NotNull String id) {

    }
    //    @NotNull
//    private final SqlSessionFactory sqlSessionFactory;
//
//    public SessionService() {
//        this.sqlSessionFactory = MyBatisConfig.getSqlSessionFactory();
//    }
//
//    @Override
//    public void createEntity(@NotNull final String id, @NotNull final Session session) {
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//        try {
//            ISessionRepositoryMyBatis sessionRepositoryMyBatis = sqlSession.getMapper(ISessionRepositoryMyBatis.class);
//            sessionRepositoryMyBatis.persist(id, session);
//            sqlSession.commit();
//        } catch (Exception e) {
//            sqlSession.rollback();
//            throw new ServiceException(e);
//        } finally {
//            sqlSession.close();
//        }
//    }
//
//    @NotNull
//    @Override
//    public Session findEntity(@NotNull final String id) {
//        if (id.isEmpty()) throw new ServiceException();
//
//        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
//            ISessionRepositoryMyBatis sessionRepositoryMyBatis = sqlSession.getMapper(ISessionRepositoryMyBatis.class);
//            return sessionRepositoryMyBatis.findOne(id);
//        } catch (Exception e) {
//            throw new ServiceException(e);
//        }
//    }
//
//    @NotNull
//    @Override
//    public Collection<Session> findAll() {
//        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
//            ISessionRepositoryMyBatis sessionRepositoryMyBatis = sqlSession.getMapper(ISessionRepositoryMyBatis.class);
//            return sessionRepositoryMyBatis.findAll();
//        } catch (Exception e) {
//            throw new ServiceException(e);
//        }
//    }
//
//    @Override
//    public void editEntity(@NotNull final String id, @NotNull Session session) {
//        if (id.isEmpty()) throw new ServiceException();
//
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//        try {
//            ISessionRepositoryMyBatis sessionRepositoryMyBatis = sqlSession.getMapper(ISessionRepositoryMyBatis.class);
//            sessionRepositoryMyBatis.merge(id, session);
//            sqlSession.commit();
//        } catch (Exception e) {
//            sqlSession.rollback();
//            throw new ServiceException(e);
//        } finally {
//            sqlSession.close();
//        }
//    }
//
//    @Override
//    public void removeEntity(@NotNull final String id) {
//        if (id.isEmpty()) throw new ServiceException();
//
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//        try {
//            ISessionRepositoryMyBatis sessionRepositoryMyBatis = sqlSession.getMapper(ISessionRepositoryMyBatis.class);
//            sessionRepositoryMyBatis.remove(id);
//            sqlSession.commit();
//        } catch (Exception e) {
//            sqlSession.rollback();
//            throw new ServiceException(e);
//        } finally {
//            sqlSession.close();
//        }
//    }
//
//    @Override
//    public void clearEntity() {
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//        try {
//            ISessionRepositoryMyBatis sessionRepositoryMyBatis = sqlSession.getMapper(ISessionRepositoryMyBatis.class);
//            sessionRepositoryMyBatis.removeAll();
//            sqlSession.commit();
//        } catch (Exception e) {
//            sqlSession.rollback();
//            throw new ServiceException(e);
//        } finally {
//            sqlSession.close();
//        }
//    }
//
//    @Override
//    public void setSession(@NotNull final Session session) {
//        createEntity(session.getId(), session);
//    }
//
//    @Override
//    public void removeSession(@NotNull final Session session) {
//        removeEntity(session.getUserId());
//    }
//
//    @Override
//    public void checkSession(@Nullable final Session session) {
//        if (!Objects.requireNonNull(session).getSignature().equals(findEntity(session.getId()).getSignature())) {
//            throw new ServiceException();
//        }
//    }
}