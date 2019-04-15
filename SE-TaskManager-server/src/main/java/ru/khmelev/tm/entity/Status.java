package ru.khmelev.tm.entity;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlType;

public enum Status {

    PLANNED("PLANNED"), INPROGRESS("INPROGRESS"), DONE("DONE");

    @Setter
    @Getter
    private String displayName;

    Status(String displayName) {
        this.displayName = displayName;
    }
}