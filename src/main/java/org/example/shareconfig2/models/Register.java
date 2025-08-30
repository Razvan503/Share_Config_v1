package org.example.shareconfig2.models;

public class Register {
    private String username;
    private String email;
    private String password;

    Register(String name, String email, String password) {
        this.username = name;
        this.email = email;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
