package org.example.shareconfig2.models;

public class Config{
    private String title;
    private String description;
    private String principalImage;
    private String image1;
    private String image2;
    private String image3;

    // Default constructor (needed for JSON deserialization)
    public Config() {}

    // All-args constructor (optional)
    public Config(String title, String description, String principalImage,
                   String image1, String image2, String image3) {
        this.title = title;
        this.description = description;
        this.principalImage = principalImage;
        this.image1 = image1;
        this.image2 = image2;
        this.image3 = image3;
    }

    // Getters and setters
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrincipalImage() {
        return principalImage;
    }
    public void setPrincipalImage(String principalImage) {
        this.principalImage = principalImage;
    }

    public String getImage1() {
        return image1;
    }
    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImage2() {
        return image2;
    }
    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getImage3() {
        return image3;
    }
    public void setImage3(String image3) {
        this.image3 = image3;
    }
}
