package ru.khmelev.tm.entity;

public enum Sort {
    START("Создание"), FINISH("Завершение"), CREATE("Добавление"), STATUS("Статус");
    String name;

    Sort(String name) {
        this.name = name;
    }
}