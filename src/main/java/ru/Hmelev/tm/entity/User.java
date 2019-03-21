package ru.Hmelev.tm.entity;

public class User {
    private String id;
    private String name;
    private byte[] hashPassword;
    private Role role;

    public User(String id, String name, byte[] hashPassword, Role role) {
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