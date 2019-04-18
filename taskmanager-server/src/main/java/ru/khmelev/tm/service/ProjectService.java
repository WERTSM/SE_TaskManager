package ru.khmelev.tm.service;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.api.service.IProjectService;
import ru.khmelev.tm.entity.dto.ProjectDTO;

import java.util.Collection;

public class ProjectService implements IProjectService {
    @Override
    public void createEntity(@NotNull String id, @NotNull ProjectDTO entity) {

    }

    @Override
    public void editEntity(@NotNull String id, @NotNull ProjectDTO entity, @NotNull String userId) {

    }

    @NotNull
    @Override
    public ProjectDTO findEntity(@NotNull String id, @NotNull String userId) {
        return null;
    }

    @Override
    public @NotNull Collection<ProjectDTO> findAll(@NotNull String userId) {
        return null;
    }

    @Override
    public @NotNull Collection<ProjectDTO> findAllName(@NotNull String findParameter, @NotNull String userId) {
        return null;
    }

    @Override
    public @NotNull Collection<ProjectDTO> findAllDescription(@NotNull String findParameter, @NotNull String userId) {
        return null;
    }

    @Override
    public void removeEntity(@NotNull String id, @NotNull String userId) {

    }

    @Override
    public void clearEntity(@NotNull String userId) {

    }

//
//    @NotNull
//    private final SqlSessionFactory sqlSessionFactory;
//
//    public ProjectService() {
//        this.sqlSessionFactory = MyBatisConfig.getSqlSessionFactory();
//    }
//
//    @Override
//    public void createEntity(@NotNull final String id, @NotNull final ProjectDTO projectDTO) {
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//        try {
//            IProjectRepositoryMyBatis projectRepositoryMyBatis = sqlSession.getMapper(IProjectRepositoryMyBatis.class);
//            projectRepositoryMyBatis.persist(id, projectDTO);
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
//    public Project findEntity(@NotNull final String id, @NotNull final String userId) {
//        if (id.isEmpty() || userId.isEmpty()) throw new ServiceException();
//
//        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
//            IProjectRepositoryMyBatis projectRepositoryMyBatis = sqlSession.getMapper(IProjectRepositoryMyBatis.class);
//            return projectRepositoryMyBatis.findOne(id, userId);
//        } catch (Exception e) {
//            throw new ServiceException(e);
//        }
//    }
//
//    @NotNull
//    @Override
//    public Collection<Project> findAll(@NotNull final String userId) {
//        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
//            IProjectRepositoryMyBatis projectRepositoryMyBatis = sqlSession.getMapper(IProjectRepositoryMyBatis.class);
//            return projectRepositoryMyBatis.findAll(userId);
//        } catch (Exception e) {
//            throw new ServiceException(e);
//        }
//    }
//
//    @Override
//    public void editEntity(@NotNull final String id, @NotNull Project project, @NotNull final String userId) {
//        if (id.isEmpty() || userId.isEmpty()) throw new ServiceException();
//
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//        try {
//            IProjectRepositoryMyBatis projectRepositoryMyBatis = sqlSession.getMapper(IProjectRepositoryMyBatis.class);
//            projectRepositoryMyBatis.merge(id, project, userId);
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
//    public Collection<Project> findAllName(@NotNull final String findParameter, @NotNull final String userId) {
//        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
//            IProjectRepositoryMyBatis projectRepositoryMyBatis = sqlSession.getMapper(IProjectRepositoryMyBatis.class);
//            @NotNull final List<Project> list = new ArrayList<>(projectRepositoryMyBatis.findAll(userId));
//            final Iterator<Project> iterator = list.iterator();
//            while (iterator.hasNext()) {
//                if (!iterator.next().getName().contains(findParameter)) {
//                    iterator.remove();
//                }
//            }
//            return list;
//        } catch (Exception e) {
//            throw new ServiceException(e);
//        }
//    }
//
//    @NotNull
//    @Override
//    public Collection<Project> findAllDescription(@NotNull final String findParameter, @NotNull final String userId) {
//        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
//            IProjectRepositoryMyBatis projectRepositoryMyBatis = sqlSession.getMapper(IProjectRepositoryMyBatis.class);
//            @NotNull final List<Project> list = new ArrayList<>(projectRepositoryMyBatis.findAll(userId));
//            final Iterator<Project> iterator = list.iterator();
//            while (iterator.hasNext()) {
//                if (!iterator.next().getDescription().contains(findParameter)) {
//                    iterator.remove();
//                }
//            }
//            return list;
//        } catch (Exception e) {
//            throw new ServiceException(e);
//        }
//    }
//
//    @Override
//    public void removeEntity(@NotNull final String id, @NotNull final String userId) {
//        if (id.isEmpty() || userId.isEmpty()) throw new ServiceException();
//
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//        try {
//            IProjectRepositoryMyBatis projectRepositoryMyBatis = sqlSession.getMapper(IProjectRepositoryMyBatis.class);
//            ITaskRepositoryMyBatis taskRepositoryMyBatis = sqlSession.getMapper(ITaskRepositoryMyBatis.class);
//            taskRepositoryMyBatis.removeAllTaskFromProject(id, userId);
//            projectRepositoryMyBatis.remove(id, userId);
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
//    public void clearEntity(@NotNull final String userId) {
//        if (userId.isEmpty()) throw new ServiceException();
//
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//        try {
//            IProjectRepositoryMyBatis projectRepositoryMyBatis = sqlSession.getMapper(IProjectRepositoryMyBatis.class);
//            projectRepositoryMyBatis.removeAll(userId);
//            sqlSession.commit();
//        } catch (Exception e) {
//            sqlSession.rollback();
//            throw new ServiceException(e);
//        } finally {
//            sqlSession.close();
//        }
//    }
}