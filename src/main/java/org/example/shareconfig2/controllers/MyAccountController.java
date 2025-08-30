package org.example.shareconfig2.controllers;

import org.example.shareconfig2.models.ConfigModel;
import org.example.shareconfig2.models.MyAccountFinalResponse;
import org.example.shareconfig2.models.MyAccountResponse;
import org.example.shareconfig2.models.User;
import org.example.shareconfig2.repository.ConfigRepository;
import org.example.shareconfig2.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class MyAccountController {

    @Autowired
    ConfigRepository configRepository;
    @Autowired
    userRepository userRepository;

    @GetMapping("/myaccount")
    public MyAccountFinalResponse getMyAccount(){
        ArrayList<MyAccountResponse> list = new ArrayList<>();
        SecurityContext securityContext = SecurityContextHolder.getContext();
        String username = securityContext.getAuthentication().getName();
        Optional<User> maybeUser = userRepository.findByUsername(username);
        if(maybeUser.isPresent()){
            User user = maybeUser.get();
            Long id = user.getId();
            List<ConfigModel> configs = configRepository.findByUserId(id);
            for(ConfigModel config : configs){
                MyAccountResponse interConfig = new MyAccountResponse(config.getId(),config.getTitle(),config.getPrincipal_image());
                list.add(interConfig);
            }
            MyAccountFinalResponse myAccountFinalResponse = new MyAccountFinalResponse(id,user.getUsername(),user.getEmail(),list);
            return myAccountFinalResponse;
        }
        return null;
    }
}
