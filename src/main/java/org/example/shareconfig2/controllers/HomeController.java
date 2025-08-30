package org.example.shareconfig2.controllers;

import org.example.shareconfig2.models.ConfigModel;
import org.example.shareconfig2.models.HomeResponse;
import org.example.shareconfig2.repository.ConfigRepository;
import org.example.shareconfig2.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/api")
public class HomeController {

    @Autowired
    ConfigRepository configRepository;
    @Autowired
    userRepository userRepository;

    @GetMapping("/home")
    public ArrayList<HomeResponse> home(){
        ArrayList<HomeResponse> homeResponses = new ArrayList<>();
        ArrayList<ConfigModel> configModels = (ArrayList<ConfigModel>) configRepository.findAll();
        for (ConfigModel configModel : configModels) {
           HomeResponse homeResponse= new HomeResponse(configModel.getId(),configModel.getTitle(),configModel.getDescription(),configModel.getPrincipal_image());
           homeResponses.add(homeResponse);
        }
        return homeResponses;
    }
}
