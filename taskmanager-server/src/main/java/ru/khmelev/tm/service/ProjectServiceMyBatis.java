package ru.khmelev.tm.service;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.api.repository.mybatis.IProjectRepositoryMyBatis;
import ru.khmelev.tm.api.repository.mybatis.ITaskRepositoryMyBatis;
import ru.khmelev.tm.entity.Project;
import ru.khmelev.tm.exception.ServiceException;
import ru.khmelev.tm.service.util.MyBatisConfig;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class ProjectServiceMyBatis {

    @NotNull
    private final SqlSessionFactory sqlSessionFactory;

    public ProjectServiceMyBatis() {
        this.sqlSessionFactory = MyBatisConfig.getSqlSessionFactory();
    }

    public void createEntity(@NotNull final String id, @NotNull final Project project) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IProjectRepositoryMyBatis projectRepositoryMyBatis = sqlSession.getMapper(IProjectRepositoryMyBatis.class);
            projectRepositoryMyBatis.persist(id, project);
            sqlSession.commit();
        }
    }

    public Project findEntity(@NotNull final String id, @NotNull final String userId) {
        if (id.isEmpty() || userId.isEmpty()) throw new ServiceException();

        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IProjectRepositoryMyBatis projectRepositoryMyBatis = sqlSession.getMapper(IProjectRepositoryMyBatis.class);
            return projectRepositoryMyBatis.findOne(id, userId);
        }
    }

    @NotNull
    public Collection<Project> findAll(@NotNull final String userId) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IProjectRepositoryMyBatis projectRepositoryMyBatis = sqlSession.getMapper(IProjectRepositoryMyBatis.class);
            return projectRepositoryMyBatis.findAll(userId);
        }
    }

    public void editEntity(@NotNull final String id, @NotNull Project project, @NotNull final String userId) {
        if (id.isEmpty() || userId.isEmpty()) throw new ServiceException();

        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            IProjectRepositoryMyBatis projectRepositoryMyBatis = sqlSession.getMapper(IProjectRepositoryMyBatis.class);
            projectRepositoryMyBatis.merge(id, project, userId);
            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
            throw new ServiceException();
        } finally {
            sqlSession.close();
        }
    }

    @NotNull
    public Collection<Project> findAllName(@NotNull final String findParameter, @NotNull final String userId) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IProjectRepositoryMyBatis projectRepositoryMyBatis = sqlSession.getMapper(IProjectRepositoryMyBatis.class);
            @NotNull final List<Project> list = new ArrayList<>(projectRepositoryMyBatis.findAll(userId));
            final Iterator<Project> iterator = list.iterator();
            while (iterator.hasNext()) {
                if (!iterator.next().getName().contains(findParameter)) {
                    iterator.remove();
                }
            }
            return list;
        }
    }

    @NotNull
    public Collection<Project> findAllDescription(@NotNull final String findParameter, @NotNull final String userId) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IProjectRepositoryMyBatis projectRepositoryMyBatis = sqlSession.getMapper(IProjectRepositoryMyBatis.class);
            @NotNull final List<Project> list = new ArrayList<>(projectRepositoryMyBatis.findAll(userId));
            final Iterator<Project> iterator = list.iterator();
            while (iterator.hasNext()) {
                if (!iterator.next().getDescription().contains(findParameter)) {
                    iterator.remove();
                }
            }
            return list;
        }
    }

    public void removeEntity(@NotNull final String id, @NotNull final String userId) {
        if (id.isEmpty() || userId.isEmpty()) throw new ServiceException();

        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IProjectRepositoryMyBatis projectRepositoryMyBatis = sqlSession.getMapper(IProjectRepositoryMyBatis.class);
            ITaskRepositoryMyBatis taskRepositoryMyBatis = sqlSession.getMapper(ITaskRepositoryMyBatis.class);
            taskRepositoryMyBatis.removeAllTaskFromProject(id, userId);
            projectRepositoryMyBatis.remove(id, userId);
            sqlSession.commit();
        }
    }

    public void clearEntity(@NotNull final String userId) {
        if (userId.isEmpty()) throw new ServiceException();
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IProjectRepositoryMyBatis projectRepositoryMyBatis = sqlSession.getMapper(IProjectRepositoryMyBatis.class);
            projectRepositoryMyBatis.removeAll(userId);
            sqlSession.commit();
        }
    }
}