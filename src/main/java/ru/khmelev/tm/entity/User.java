package ru.khmelev.tm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public final class User extends Entity {

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