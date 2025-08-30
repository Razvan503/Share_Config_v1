package org.example.shareconfig2.models;

public class Login {
    private final String username;
    private final String password;


    public Login(String  username,String password){
        this.username = username;
        this.password = password;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }


}
