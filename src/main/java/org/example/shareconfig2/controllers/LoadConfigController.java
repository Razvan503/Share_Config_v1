package org.example.shareconfig2.controllers;

import org.example.shareconfig2.models.ConfigModel;
import org.example.shareconfig2.models.LoadConfigModel;
import org.example.shareconfig2.repository.ConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Optional;


@RestController
@RequestMapping("/api")
public class LoadConfigController {

    @Autowired
    ConfigRepository configRepository;

   @GetMapping("/config/{id}")
   public LoadConfigModel config(@PathVariable Long id){
       Optional<ConfigModel> config  =  configRepository.findById(id);
       if(config.isPresent()){
           ConfigModel configModel = config.get();

           ArrayList<String> images = new ArrayList<>();
           images.add(configModel.getImage1());
           images.add(configModel.getImage2());
           images.add(configModel.getImage3());

           LoadConfigModel responseConfig = new LoadConfigModel(configModel.getTitle(),configModel.getDescription(),images);
            return responseConfig;
       }
       return null;
   }

}
