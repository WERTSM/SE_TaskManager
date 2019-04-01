package ru.khmelev.tm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public final class User extends Entity implements Serializable {

    private String id;
    private String login;
    private String hashPassword;
    private Role role;

    @Override
    public String getUserId() {
        return this.getId();
    }

    @Override
    public String getDescription() {
        return this.getLogin();
    }

    @Override
    public void setDescription(String description) {
        this.setLogin(description);
    }
}