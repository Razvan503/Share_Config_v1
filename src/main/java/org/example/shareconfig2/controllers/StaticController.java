package org.example.shareconfig2.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StaticController {
    @GetMapping("/addconfig")
    public String addConfig() {
        System.out.println("addConfig");
        return "add_config";
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }
    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    @GetMapping("/signin")
    public String signin() {
        return "signin";
    }

    @GetMapping("/home")
    public String home(){
        return "main";
    }


    @GetMapping("/my-account")
    public String myaccount(){
        return "Myaccount";
    }

    @GetMapping("/config/{id}")
    public String config(){
        return "config";
    }

    @GetMapping("/error")
    public String error(){
        return "error";
    }
}
