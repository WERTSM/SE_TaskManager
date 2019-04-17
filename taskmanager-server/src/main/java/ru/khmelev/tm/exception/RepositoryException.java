package ru.khmelev.tm.exception;

public class RepositoryException extends RuntimeException {
    public RepositoryException() {
        super("Ошибка в репозитории");
    }

    public RepositoryException(Exception e) {
        super(e);
        System.err.println("Ошибка в репозитории");
    }
}