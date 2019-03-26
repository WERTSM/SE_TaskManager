package ru.Hmelev.tm.exception;

public class RepositoryException extends RuntimeException {
    public RepositoryException() {
        super("Ошибка в репозитории");
    }
}