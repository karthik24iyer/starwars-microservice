package com.starwars.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.starwars.config.StarWarsProperties;

@RestController
@RequestMapping("/api/config")
public class ConfigController {

    private final StarWarsProperties starWarsProperties;

    @Autowired
    public ConfigController(StarWarsProperties starWarsProperties) {
        this.starWarsProperties = starWarsProperties;
    }

    @PostMapping("/toggle-offline-mode")
    public String toggleOfflineMode(@RequestParam boolean offlineMode) {
        starWarsProperties.setOfflineMode(offlineMode);
        return "Offline mode set to: " + offlineMode;
    }

    @GetMapping("/offline-mode")
    public String getOfflineMode() {
        return "Current offline mode: " + starWarsProperties.isOfflineMode();
    }
}