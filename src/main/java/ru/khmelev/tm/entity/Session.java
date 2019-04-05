package ru.khmelev.tm.entity;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.Date;

@Getter
@Setter
public class Session extends Identifiable {

    @NotNull
    private String id;
    @NotNull
    private String userId;
    @NotNull
    private String signature;
    @NotNull
    private Date createDate;

    public Session() {
        this.id = "";
        this.signature = "";
        this.userId = "";
        this.createDate = new Date();
    }
}