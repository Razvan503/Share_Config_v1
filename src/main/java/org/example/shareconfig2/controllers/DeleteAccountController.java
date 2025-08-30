package org.example.shareconfig2.controllers;

import org.example.shareconfig2.models.DeleteAccount;
import org.example.shareconfig2.models.User;
import org.example.shareconfig2.repository.ConfigRepository;
import org.example.shareconfig2.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class DeleteAccountController {

    @Autowired
    userRepository userRepository;
    @Autowired
    ConfigRepository configRepository;

    @DeleteMapping("/deleteaccount")
    public void deleteAccount(@RequestBody DeleteAccount deleteAccount) {
        Long id = deleteAccount.getId();
        SecurityContext securityContext = SecurityContextHolder.getContext();
        String username = securityContext.getAuthentication().getName();
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isPresent()){
            User user1 = user.get();
            configRepository.deleteByUserId(user1.getId());
            userRepository.delete(user1);
        }
    }
}
