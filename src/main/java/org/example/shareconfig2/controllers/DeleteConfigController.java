package org.example.shareconfig2.controllers;

import org.example.shareconfig2.models.ConfigModel;
import org.example.shareconfig2.models.DeleteConfig;
import org.example.shareconfig2.models.User;
import org.example.shareconfig2.repository.ConfigRepository;
import org.example.shareconfig2.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.ResourceTransactionManager;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
@Service
public class DeleteConfigController {

    @Autowired
    ConfigRepository configRepository;
    @Autowired
    userRepository userRepository;

    @DeleteMapping("/deleteconfig")
    public void deleteConfig(@RequestBody DeleteConfig deleteConfig) {
        Long id = deleteConfig.getId();

        Optional<ConfigModel> configModel = configRepository.findById(id);
        SecurityContext securityContext = SecurityContextHolder.getContext();
        String username = securityContext.getAuthentication().getName();

        Optional<User> user = userRepository.findByUsername(username);

        if (user.isPresent() && configModel.isPresent()) {
            User user1 = user.get();
            ConfigModel config = configModel.get();

            String RequestUser = user1.getUsername();
            String ConfigUser = config.getUser().getUsername();

            if(RequestUser.equals(ConfigUser)){
                configRepository.deleteById(id);
            }
        }
    }
}
