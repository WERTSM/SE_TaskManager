package ru.khmelev.tm.service;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.api.service.ITaskService;
import ru.khmelev.tm.entity.dto.TaskDTO;

import java.util.Collection;

public class TaskService implements ITaskService {
    @Override
    public void removeAllTaskFromProject(@NotNull String projectId, @NotNull String userId) {

    }

    @Override
    public Collection<TaskDTO> listTaskFromProject(@NotNull String projectId, @NotNull String userId) {
        return null;
    }

    @Override
    public void createEntity(@NotNull String id, @NotNull TaskDTO entity) {

    }

    @Override
    public void editEntity(@NotNull String id, @NotNull TaskDTO entity, @NotNull String userId) {

    }

    @NotNull
    @Override
    public TaskDTO findEntity(@NotNull String id, @NotNull String userId) {
        return null;
    }

    @Override
    public @NotNull Collection<TaskDTO> findAll(@NotNull String userId) {
        return null;
    }

    @Override
    public @NotNull Collection<TaskDTO> findAllName(@NotNull String findParameter, @NotNull String userId) {
        return null;
    }

    @Override
    public @NotNull Collection<TaskDTO> findAllDescription(@NotNull String findParameter, @NotNull String userId) {
        return null;
    }

    @Override
    public void removeEntity(@NotNull String id, @NotNull String userId) {

    }

    @Override
    public void clearEntity(@NotNull String userId) {

    }

//    @NotNull
//    private final SqlSessionFactory sqlSessionFactory;
//
//    public TaskService() {
//        this.sqlSessionFactory = MyBatisConfig.getSqlSessionFactory();
//    }
//
//    @Override
//    public void createEntity(@NotNull final String id, @NotNull final Task task) {
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//        try {
//            ITaskRepositoryMyBatis taskRepositoryMyBatis = sqlSession.getMapper(ITaskRepositoryMyBatis.class);
//            taskRepositoryMyBatis.persist(id, task);
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
//    public Task findEntity(@NotNull final String id, @NotNull final String userId) {
//        if (id.isEmpty() || userId.isEmpty()) throw new ServiceException();
//
//        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
//            ITaskRepositoryMyBatis taskRepositoryMyBatis = sqlSession.getMapper(ITaskRepositoryMyBatis.class);
//            return taskRepositoryMyBatis.findOne(id, userId);
//        } catch (Exception e) {
//            throw new ServiceException(e);
//        }
//    }
//
//    @NotNull
//    @Override
//    public Collection<Task> findAll(@NotNull final String userId) {
//        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
//            ITaskRepositoryMyBatis taskRepositoryMyBatis = sqlSession.getMapper(ITaskRepositoryMyBatis.class);
//            return taskRepositoryMyBatis.findAll(userId);
//        } catch (Exception e) {
//            throw new ServiceException(e);
//        }
//    }
//
//    @Override
//    public void editEntity(@NotNull final String id, @NotNull Task task, @NotNull final String userId) {
//        if (id.isEmpty() || userId.isEmpty()) throw new ServiceException();
//
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//        try {
//            ITaskRepositoryMyBatis taskRepositoryMyBatis = sqlSession.getMapper(ITaskRepositoryMyBatis.class);
//            taskRepositoryMyBatis.merge(id, task, userId);
//            sqlSession.commit();
//        } catch (Exception e) {
//            sqlSession.rollback();
//            throw new ServiceException();
//        } finally {
//            sqlSession.close();
//        }
//    }
//
//    @NotNull
//    @Override
//    public Collection<Task> findAllName(@NotNull final String findParameter, @NotNull final String userId) {
//        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
//            ITaskRepositoryMyBatis taskRepositoryMyBatis = sqlSession.getMapper(ITaskRepositoryMyBatis.class);
//            @NotNull final List<Task> list = new ArrayList<>(taskRepositoryMyBatis.findAll(userId));
//            final Iterator<Task> iterator = list.iterator();
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
//    public Collection<Task> findAllDescription(@NotNull final String findParameter, @NotNull final String userId) {
//        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
//            ITaskRepositoryMyBatis taskRepositoryMyBatis = sqlSession.getMapper(ITaskRepositoryMyBatis.class);
//            @NotNull final List<Task> list = new ArrayList<>(taskRepositoryMyBatis.findAll(userId));
//            final Iterator<Task> iterator = list.iterator();
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
//            ITaskRepositoryMyBatis taskRepositoryMyBatis = sqlSession.getMapper(ITaskRepositoryMyBatis.class);
//            taskRepositoryMyBatis.remove(id, userId);
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
//            ITaskRepositoryMyBatis taskRepositoryMyBatis = sqlSession.getMapper(ITaskRepositoryMyBatis.class);
//            taskRepositoryMyBatis.removeAll(userId);
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
//    public void removeAllTaskFromProject(@NotNull final String projectId, @NotNull final String userId) {
//        if (projectId.isEmpty() || userId.isEmpty()) throw new ServiceException();
//
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//        try {
//            ITaskRepositoryMyBatis taskRepositoryMyBatis = sqlSession.getMapper(ITaskRepositoryMyBatis.class);
//            taskRepositoryMyBatis.removeAllTaskFromProject(projectId, userId);
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
//    public Collection<Task> listTaskFromProject(@NotNull String projectId, @NotNull String userId) {
//        if (projectId.isEmpty() || userId.isEmpty()) throw new ServiceException();
//
//        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
//            ITaskRepositoryMyBatis taskRepositoryMyBatis = sqlSession.getMapper(ITaskRepositoryMyBatis.class);
//            return taskRepositoryMyBatis.listTaskFromProject(projectId, userId);
//        } catch (Exception e) {
//            throw new ServiceException(e);
//        }
//    }
}