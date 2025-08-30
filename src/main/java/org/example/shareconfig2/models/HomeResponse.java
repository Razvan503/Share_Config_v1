package org.example.shareconfig2.models;


public class HomeResponse {

    Long id;
    String title;
    String description;
    String image;


    public HomeResponse(Long id,String title,String description,String image){
        this.id = id;
        this.title=title;
        this.description=description;
        this.image=image;
    }
    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public Long getId() {
        return id;
    }
}
