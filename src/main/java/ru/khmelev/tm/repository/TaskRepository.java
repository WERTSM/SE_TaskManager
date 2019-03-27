package ru.khmelev.tm.repository;

import ru.khmelev.tm.api.InterfaceTaskRepository;
import ru.khmelev.tm.entity.Task;

public class TaskRepository extends SuperEntityRepository<Task> implements InterfaceTaskRepository {
}
