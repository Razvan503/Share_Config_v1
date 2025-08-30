package org.example.shareconfig2.Components;

import org.example.shareconfig2.models.Authority;
import org.example.shareconfig2.models.User;
import org.example.shareconfig2.repository.AuthorityRepository;
import org.example.shareconfig2.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DataSeeder implements CommandLineRunner {

    private final AuthorityRepository authorityRepository;

    @Autowired
    userRepository userRepository;

    public DataSeeder(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("data seeder started");
        if(authorityRepository.findByName("ROLE_USER").isEmpty()){
            Authority authority = new Authority("ROLE_USER");
            authorityRepository.save(authority);
        }

        if(authorityRepository.findByName("ROLE_ADMIN").isEmpty()){
            Authority authority = new Authority("ROLE_ADMIN");
            authorityRepository.save(authority);
        }


    }
}
