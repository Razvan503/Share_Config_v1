package org.example.shareconfig2.models;

import java.util.ArrayList;

public class MyAccountFinalResponse {
    Long id;
    String username;
    String email;
    ArrayList<MyAccountResponse> configs;

    public MyAccountFinalResponse(Long id, String username, String email, ArrayList<MyAccountResponse> configs) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.configs = configs;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }
    public String getEmail() {
        return email;
    }

    public ArrayList<MyAccountResponse> getConfigs() {
        return configs;
    }

}
