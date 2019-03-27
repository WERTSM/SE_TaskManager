package ru.Hmelev.tm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.Date;

@NoArgsConstructor
public final class Task extends Entity {
    @Getter
    private String id;
    @Setter
    @Getter
    private String name;
    @Setter
    @Getter
    private String description;
    @Setter
    @Getter
    private Date startDate;
    @Setter
    @Getter
    private Date finishDate;
    @Setter
    @Getter
    private String idProject;
    @Getter
    @Setter
    private Status status;
    @Getter
    private Date dataCreate;
    @Getter
    private String userId;

    public Task(@NotNull String id, @NotNull String name, @NotNull String description, @NotNull Date startDate, @NotNull Date finishDate, @NotNull String idProject, @NotNull Status status, @NotNull String userId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.idProject = idProject;
        this.status = status;
        this.dataCreate = new Date();
        this.userId = userId;
    }
}