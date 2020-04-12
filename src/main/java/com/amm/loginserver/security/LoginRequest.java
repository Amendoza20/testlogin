package com.amm.loginserver.security;

public class LoginRequest {
    private String Username;
    private String Password;
// do I need setters for these?
    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
