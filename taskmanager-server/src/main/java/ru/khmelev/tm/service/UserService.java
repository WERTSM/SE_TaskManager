package ru.khmelev.tm.service;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.api.repository.mybatis.IUserRepositoryMyBatis;
import ru.khmelev.tm.api.service.IUserService;
import ru.khmelev.tm.endpoint.util.PasswordHashUtil;
import ru.khmelev.tm.entity.User;
import ru.khmelev.tm.exception.ServiceException;
import ru.khmelev.tm.service.util.MyBatisConfig;

import java.util.Collection;
import java.util.Objects;

public class UserService implements IUserService {

    @NotNull
    private final SqlSessionFactory sqlSessionFactory;

    public UserService() {
        this.sqlSessionFactory = MyBatisConfig.getSqlSessionFactory();
    }

    @Override
    public void createEntity(@NotNull final String id, @NotNull final User user) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            IUserRepositoryMyBatis userRepositoryMyBatis = sqlSession.getMapper(IUserRepositoryMyBatis.class);
            userRepositoryMyBatis.persist(id, user);
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
    public User findEntity(@NotNull final String id) {
        if (id.isEmpty()) throw new ServiceException();

        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IUserRepositoryMyBatis userRepositoryMyBatis = sqlSession.getMapper(IUserRepositoryMyBatis.class);
            return userRepositoryMyBatis.findOne(id);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @NotNull
    @Override
    public Collection<User> findAll() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IUserRepositoryMyBatis userRepositoryMyBatis = sqlSession.getMapper(IUserRepositoryMyBatis.class);
            return userRepositoryMyBatis.findAll();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void editEntity(@NotNull final String id, @NotNull User user) {
        if (id.isEmpty()) throw new ServiceException();

        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            IUserRepositoryMyBatis userRepositoryMyBatis = sqlSession.getMapper(IUserRepositoryMyBatis.class);
            userRepositoryMyBatis.merge(id, user);
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
            IUserRepositoryMyBatis userRepositoryMyBatis = sqlSession.getMapper(IUserRepositoryMyBatis.class);
            userRepositoryMyBatis.remove(id);
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
    public String getId(@NotNull final User user) {
        return user.getId();
    }

    @NotNull
    @Override
    public String getName(@NotNull final User user) {
        return user.getLogin();
    }

    @Override
    public void userSetPassword(@NotNull final String login, @NotNull final String pass) {
        if (login.isEmpty() || pass.isEmpty()) throw new ServiceException();

        Collection<User> users = findAll();
        for (User user : users) {
            if (user.getLogin().equals(login)) {
                @NotNull final String password = Objects.requireNonNull(PasswordHashUtil.md5(pass));
                user.setHashPassword(password);
                editEntity(user.getId(), user);
            }
        }
    }

    @NotNull
    @Override
    public User getUserFromSession(@NotNull final String userId) {
        return findEntity(userId);
    }
}