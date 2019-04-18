package ru.khmelev.tm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;
import ru.khmelev.tm.entity.enumeration.Role;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@javax.persistence.Entity
@Table(name = "user")
public class User extends Identifiable implements Serializable {

    @Column(name="login", unique = true)
    private String login;

    private String hashPassword;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Nullable
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Project> projects;

    @Nullable
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasks;
}