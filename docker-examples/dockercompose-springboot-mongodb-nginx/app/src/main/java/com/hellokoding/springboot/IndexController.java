package com.hellokoding.springboot;

import com.mongodb.MongoClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class IndexController {
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    @GetMapping("/")
    public String index(Model model) {
        try(MongoClient mongoClient = new MongoClient("mongodb")){
            logger.log(Level.INFO, "First database name: " + mongoClient.listDatabaseNames().first());
        }
        
        return "index";
    }
}
