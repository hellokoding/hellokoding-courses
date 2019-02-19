package com.hellokoding.springboot.restful;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StockController {
    @GetMapping("/")
    public String list(){
        return "stocklist";
    }
}
