package com.camaraturismoorosi.apicamaraturismoorosi.controller.home;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class homeController {

    @GetMapping("home")
    public String home() {
        return "home";
    }
    
}
