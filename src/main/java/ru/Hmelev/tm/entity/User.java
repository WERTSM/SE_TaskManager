package ru.Hmelev.tm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

@NoArgsConstructor
@Getter
@Setter
public final class User {
    private String id;
    private String name;
    private String hashPassword;
    private Role role;

    public User(@NotNull String id, @NotNull String name, @NotNull String hashPassword, @NotNull Role role) {
        this.id = id;
        this.name = name;
        this.hashPassword = hashPassword;
        this.role = role;
    }
}