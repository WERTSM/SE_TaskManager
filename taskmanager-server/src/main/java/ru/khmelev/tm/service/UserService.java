package ru.khmelev.tm.service;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.api.service.IUserService;
import ru.khmelev.tm.entity.User;
import ru.khmelev.tm.entity.dto.UserDTO;

import java.util.Collection;

public class UserService implements IUserService {
    public UserService() {
        super();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    @Override
    public @NotNull String getId(@NotNull User user) {
        return null;
    }

    @Override
    public @NotNull String getName(@NotNull User user) {
        return null;
    }

    @Override
    public void userSetPassword(@NotNull String login, @NotNull String pass) {

    }

    @Override
    public UserDTO getUserFromSession(@NotNull String userId) {
        return null;
    }

    @Override
    public void createEntity(@NotNull String id, @NotNull UserDTO entity) {

    }

    @NotNull
    @Override
    public UserDTO findEntity(@NotNull String id) {
        return null;
    }

    @Override
    public @NotNull Collection<UserDTO> findAll() {
        return null;
    }

    @Override
    public void editEntity(@NotNull String id, @NotNull UserDTO entity) {

    }

    @Override
    public void removeEntity(@NotNull String id) {

    }
    //    @NotNull
//    private final SqlSessionFactory sqlSessionFactory;
//
//    public UserService() {
//        this.sqlSessionFactory = MyBatisConfig.getSqlSessionFactory();
//    }
//
//    @Override
//    public void createEntity(@NotNull final String id, @NotNull final User user) {
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//        try {
//            IUserRepositoryMyBatis userRepositoryMyBatis = sqlSession.getMapper(IUserRepositoryMyBatis.class);
//            userRepositoryMyBatis.persist(id, user);
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
//    public User findEntity(@NotNull final String id) {
//        if (id.isEmpty()) throw new ServiceException();
//
//        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
//            IUserRepositoryMyBatis userRepositoryMyBatis = sqlSession.getMapper(IUserRepositoryMyBatis.class);
//            return userRepositoryMyBatis.findOne(id);
//        } catch (Exception e) {
//            throw new ServiceException(e);
//        }
//    }
//
//    @NotNull
//    @Override
//    public Collection<User> findAll() {
//        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
//            IUserRepositoryMyBatis userRepositoryMyBatis = sqlSession.getMapper(IUserRepositoryMyBatis.class);
//            return userRepositoryMyBatis.findAll();
//        } catch (Exception e) {
//            throw new ServiceException(e);
//        }
//    }
//
//    @Override
//    public void editEntity(@NotNull final String id, @NotNull User user) {
//        if (id.isEmpty()) throw new ServiceException();
//
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//        try {
//            IUserRepositoryMyBatis userRepositoryMyBatis = sqlSession.getMapper(IUserRepositoryMyBatis.class);
//            userRepositoryMyBatis.merge(id, user);
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
//            IUserRepositoryMyBatis userRepositoryMyBatis = sqlSession.getMapper(IUserRepositoryMyBatis.class);
//            userRepositoryMyBatis.remove(id);
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
//    public String getId(@NotNull final User user) {
//        return user.getId();
//    }
//
//    @NotNull
//    @Override
//    public String getName(@NotNull final User user) {
//        return user.getLogin();
//    }
//
//    @Override
//    public void userSetPassword(@NotNull final String login, @NotNull final String pass) {
//        if (login.isEmpty() || pass.isEmpty()) throw new ServiceException();
//
//        Collection<User> users = findAll();
//        for (User user : users) {
//            if (user.getLogin().equals(login)) {
//                @NotNull final String password = Objects.requireNonNull(PasswordHashUtil.md5(pass));
//                user.setHashPassword(password);
//                editEntity(user.getId(), user);
//            }
//        }
//    }
//
//    @NotNull
//    @Override
//    public User getUserFromSession(@NotNull final String userId) {
//        return findEntity(userId);
//    }
}