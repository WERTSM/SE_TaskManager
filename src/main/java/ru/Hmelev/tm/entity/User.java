package ru.Hmelev.tm.entity;

public class User {
    private String name;
    byte[] hashPassword;
    private Role role;

    public User(String name, byte[] hashPassword, Role role) {
        this.name = name;
        this.hashPassword = hashPassword;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getHashPassword() {
        return hashPassword;
    }

    public void setHashPassword(byte[] hashPassword) {
        this.hashPassword = hashPassword;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}