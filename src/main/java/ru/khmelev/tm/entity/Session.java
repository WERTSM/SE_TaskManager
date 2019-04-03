package ru.khmelev.tm.entity;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.Date;

@Getter
@Setter
public class Session extends Identifiable {

    public Session() {
        this.id = "";
        this.sign = "";
        this.userId = "";
        this.createDate = new Date();
    }

    @NotNull
    private String id;

    @NotNull
    private String userId;

    @NotNull
    private String sign;

    @NotNull
    private Date createDate;
}