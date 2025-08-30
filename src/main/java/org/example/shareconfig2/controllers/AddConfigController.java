package org.example.shareconfig2.controllers;

import org.example.shareconfig2.models.Config;
import org.example.shareconfig2.models.ConfigModel;
import org.example.shareconfig2.models.User;
import org.example.shareconfig2.repository.ConfigRepository;
import org.example.shareconfig2.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Service
@Transactional
@RestController
@RequestMapping("/api")
public class AddConfigController {

    @Autowired
    ConfigRepository configRepository;
    @Autowired
    userRepository userRepository;

    @PostMapping("/config")
    public String addConfig(@RequestBody Config config) {
        SecurityContext context = SecurityContextHolder.getContext();
        String username = context.getAuthentication().getName();
        Optional<User> retrieveuser = userRepository.findByUsername(username);
        User user = retrieveuser.get();

        ConfigModel configModel = new ConfigModel(user,config.getTitle(),config.getDescription(),config.getPrincipalImage(),config.getImage1(),config.getImage2(),config.getImage3());
        configRepository.save(configModel);
        return "success";
    }
}
