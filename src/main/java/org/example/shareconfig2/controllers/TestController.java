package org.example.shareconfig2.controllers;

import org.example.shareconfig2.models.User;
import org.example.shareconfig2.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class TestController {

    private userRepository userRepository;
    @Autowired
    public TestController(userRepository userRepository) {
        this.userRepository = userRepository;
    }
    @GetMapping("/test")
    public void test(){
        ArrayList<User> users = (ArrayList<User>) userRepository.findAll();
        for (User user : users) {
            System.out.println(user.getUsername());
            System.out.println(user);
        }
    }
}
