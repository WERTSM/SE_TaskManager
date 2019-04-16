package ru.khmelev.tm.exception;

public class EndpointException extends RuntimeException {
    public EndpointException() {
        super("Ошибка в веб-сервисах");
    }

    public EndpointException(Exception e) {
        super(e);
        System.err.println("Ошибка в веб-сервисах");
    }
}