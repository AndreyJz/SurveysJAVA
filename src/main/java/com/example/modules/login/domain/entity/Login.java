package com.example.modules.login.domain.entity;

public class Login {
    private int id;
    private String username;
    private String password;

    public Login() {}
    public Login(int id, String username, String password) {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
