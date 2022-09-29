package com.example.ProyectoCiclo3.Controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String login(){
        return "login/login";
    }


    @GetMapping("login")
    public String login_alternal(){
        return "login/login";
    }

}
