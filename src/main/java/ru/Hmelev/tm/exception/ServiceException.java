package ru.Hmelev.tm.exception;

public class ServiceException extends RuntimeException {
    public ServiceException() {
        super("Ошибка в сервисах");
    }
}