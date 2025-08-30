package org.example.shareconfig2.models;

import java.util.List;

public class LoadConfigModel {
    private String title;
    private String content;
    private List<String> images;

    public LoadConfigModel(String title, String content, List<String> images){
        this.title=title;
        this.content=content;
        this.images=images;
    }

    public String getTitle() {
        return title;
    }
    public String getContent() {
        return content;
    }

    public List<String> getImages() {
        return images;
    }
}
