package com.starwars.controller;

import com.starwars.config.StarWarsProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/config")
public class ConfigController {

    private final StarWarsProperties starWarsProperties;

    @Autowired
    public ConfigController(StarWarsProperties starWarsProperties) {
        this.starWarsProperties = starWarsProperties;
    }

    @PostMapping("/toggle-offline-mode")
    public ResponseEntity<String> toggleOfflineMode(@RequestParam boolean enabled) {
        starWarsProperties.setOfflineMode(enabled);
        String message = enabled ? "Offline mode enabled" : "Offline mode disabled";
        return ResponseEntity.ok(message);
    }

    @GetMapping("/offline-mode-status")
    public ResponseEntity<String> getOfflineModeStatus() {
        boolean status = starWarsProperties.isOfflineMode();
        String message = status ? "Offline mode is currently enabled" : "Offline mode is currently disabled";
        return ResponseEntity.ok(message);
    }
}