package org.example.shareconfig2.models;

import java.util.ArrayList;

public class MyAccountResponse {
    private Long id;
    private String title;
    private String principal_image;

    public MyAccountResponse(Long id, String title, String principal_image) {
        this.id = id;
        this.title = title;
        this.principal_image = principal_image;
    }

    public Long getId() {
        return id;
    }

    public String getTitle(){
        return title;
    }

    public String getPrincipal_image() {
        return principal_image;
    }

}
