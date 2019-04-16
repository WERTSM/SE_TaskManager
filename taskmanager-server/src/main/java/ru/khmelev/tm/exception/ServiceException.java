package ru.khmelev.tm.exception;

public class ServiceException extends RuntimeException {
    public ServiceException() {
        super("Ошибка в сервисах");
    }

    public ServiceException(Exception e) {
        super(e);
        System.err.println("Ошибка в сервисах");
    }
}