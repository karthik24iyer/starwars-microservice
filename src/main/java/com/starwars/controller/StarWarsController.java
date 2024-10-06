package com.starwars.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StarWarsController {

    @GetMapping("/")
    public String home() {
        return "Welcome to the Star Wars Microservice!";
    }
}