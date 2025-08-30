package org.example.shareconfig2.models;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "authorities")
public class Authority implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true)
    private String name;

    @Override
    public String getAuthority() {
        return name;
    }
    public Authority(String name){
        this.name = name;
    }
   protected Authority() {}

    public void setId(Long id) {
        this.id = id;
    }


    public String getName(){
        return name;
    }

    public Long getId() {
        return id;
    }
}
