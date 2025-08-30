package org.example.shareconfig2.models;

import jakarta.persistence.*;

@Entity
@Table(name  = "configs")
public class ConfigModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",nullable = false)
    private User user;
    @Column(nullable = false)
    private String title;
    @Lob
    @Column(columnDefinition="LONGTEXT",nullable = false)
    private String description;
    @Column(nullable = false)
    private String principal_image;
    @Column(nullable = false)
    private String image1;
    @Column(nullable = false)
    private String image2;
    @Column(nullable = false)
    private String image3;

    protected ConfigModel() {
        // for JPA
    }

    public ConfigModel(User user, String title, String description, String principal_image, String image1, String image2, String image3) {
        this.user = user;
        this.title = title;
        this.description = description;
        this.principal_image = principal_image;
        this.image1 = image1;
        this.image2 = image2;
        this.image3 = image3;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public String getPrincipal_image() {
        return principal_image;
    }
    public String getImage1() {
        return image1;
    }
    public String getImage2() {
        return image2;
    }

    public String getImage3() {
        return image3;
    }
}
