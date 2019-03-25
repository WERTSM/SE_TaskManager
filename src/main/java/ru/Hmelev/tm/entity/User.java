package ru.Hmelev.tm.entity;

public final class User {
    private String id;
    private String name;
    private String hashPassword;
    private Role role;

    public User(String id, String name, String hashPassword, Role role) {
        this.id = id;
        this.name = name;
        this.hashPassword = hashPassword;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHashPassword() {
        return hashPassword;
    }

    public void setHashPassword(String hashPassword) {
        this.hashPassword = hashPassword;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}