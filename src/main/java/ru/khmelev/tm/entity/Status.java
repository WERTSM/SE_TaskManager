package ru.khmelev.tm.entity;

import lombok.Getter;
import lombok.Setter;

public enum Status {

    PLANNED("ЗАПЛАНИРОВАНО"), INPROGRESS("В ПРОЦЕССЕ"), DONE("ГОТОВО");

    @Setter
    @Getter
    private String displayName;

    Status(String displayName) {
        this.displayName = displayName;
    }
}