package org.example.shareconfig2.models;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true)
    private String username;
    @Column(nullable = false,unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    private String token;

    protected User() {}
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_authorities", // The name of our join table
            joinColumns = @JoinColumn(name = "user_id"), // Column in join table that points to this entity (User)
            inverseJoinColumns = @JoinColumn(name = "authority_id") // Column that points to the other entity (Authority)
    )
    private Set<Authority> authorities = new HashSet<>();

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<ConfigModel> configs = new ArrayList<>();

    public User(String username, String email, String password, Set<Authority> authorities) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
        this.token = "placeholder";
    }

    public Long getId() {
        return id;
    }

    @Override
    public String getUsername(){
        return username;
    }

    public String getEmail(){
        return email;
    }

    public String getToken(){
        return token;
    }

    public void setToken(String token){
        this.token = token;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword(){
        return password;
    }

}
