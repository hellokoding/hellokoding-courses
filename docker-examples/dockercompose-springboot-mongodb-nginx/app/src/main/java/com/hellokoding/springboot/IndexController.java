package com.hellokoding.springboot;

import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
@RequestMapping("/")
public class IndexController {
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    @Autowired
    MongoClient mongoClient;

    @GetMapping
    public String index(Model model) {
        logger.log(Level.INFO, "First database name: {0}", mongoClient.listDatabaseNames().first());
        return "index";
    }
}
