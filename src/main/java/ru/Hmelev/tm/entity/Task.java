package ru.Hmelev.tm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.Date;
@NoArgsConstructor
public final class Task extends Entity {
    @Getter
    @NotNull
    private String id;
    @Setter
    @Getter
    @NotNull
    private String name;
    @Setter
    @Getter
    @NotNull
    private String description;
    @Setter
    @Getter
    @NotNull
    private Date startDate;
    @Setter
    @Getter
    @NotNull
    private Date finishDate;
    @Setter
    @Getter
    @NotNull
    private String idProject;
    @Getter
    @NotNull
    private String userId;

    public Task(@NotNull String id, @NotNull String name, @NotNull String description, @NotNull Date startDate, @NotNull Date finishDate, @NotNull String idProject, @NotNull String userId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.idProject = idProject;
        this.userId = userId;
    }
}