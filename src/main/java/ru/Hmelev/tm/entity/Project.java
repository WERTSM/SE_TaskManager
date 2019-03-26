package ru.Hmelev.tm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.Date;
@NoArgsConstructor
@Setter
@Getter
public class Project extends Entity {
    @NotNull
    private String id;
    @NotNull
    private String name;
    @NotNull
    private String description;
    @NotNull
    private Date dateStart;
    @NotNull
    private Date dataFinish;
    @NotNull
    private String userId;

    public Project(@NotNull String id, @NotNull String name, @NotNull String description, @NotNull Date dateStart, @NotNull Date dataFinish, @NotNull String userId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dateStart = dateStart;
        this.dataFinish = dataFinish;
        this.userId = userId;
    }
}