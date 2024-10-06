package com.starwars.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.starwars.service.StarWarsService;
import com.starwars.model.*;
import com.starwars.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/starwars")
@Api(tags = "Star Wars Information")
public class StarWarsController {

    private static final Logger logger = LoggerFactory.getLogger(StarWarsController.class);
    private final StarWarsService starWarsService;

    @Autowired
    public StarWarsController(StarWarsService starWarsService) {
        this.starWarsService = starWarsService;
    }

    @GetMapping("/")
    @ApiOperation(value = "Get the index page", hidden = true)
    public String index() {
        return "index.html";
    }

    @GetMapping("/planets/{name}")
    @ApiOperation(value = "Get information about a planet", response = StarWarsResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved planet information"),
            @ApiResponse(code = 404, message = "Planet not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    public ResponseEntity<StarWarsResponse<Planet>> getPlanet(@PathVariable String name) {
        logger.info("Received request for planet: {}", name);
        StarWarsResponse<Planet> response = starWarsService.getPlanetInfo(name);
        if (response.getData() == null) {
            throw new ResourceNotFoundException("Planet not found: " + name);
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/spaceships/{name}")
    @ApiOperation(value = "Get information about a spaceship", response = StarWarsResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved spaceship information"),
            @ApiResponse(code = 404, message = "Spaceship not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    public ResponseEntity<StarWarsResponse<Spaceship>> getSpaceship(@PathVariable String name) {
        logger.info("Received request for spaceship: {}", name);
        StarWarsResponse<Spaceship> response = starWarsService.getSpaceshipInfo(name);
        if (response.getData() == null) {
            throw new ResourceNotFoundException("Spaceship not found: " + name);
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/vehicles/{name}")
    @ApiOperation(value = "Get information about a vehicle", response = StarWarsResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved vehicle information"),
            @ApiResponse(code = 404, message = "Vehicle not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    public ResponseEntity<StarWarsResponse<Vehicle>> getVehicle(@PathVariable String name) {
        logger.info("Received request for vehicle: {}", name);
        StarWarsResponse<Vehicle> response = starWarsService.getVehicleInfo(name);
        if (response.getData() == null) {
            throw new ResourceNotFoundException("Vehicle not found: " + name);
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/people/{name}")
    @ApiOperation(value = "Get information about a person", response = StarWarsResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved person information"),
            @ApiResponse(code = 404, message = "Person not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    public ResponseEntity<StarWarsResponse<Person>> getPerson(@PathVariable String name) {
        logger.info("Received request for person: {}", name);
        StarWarsResponse<Person> response = starWarsService.getPersonInfo(name);
        if (response.getData() == null) {
            throw new ResourceNotFoundException("Person not found: " + name);
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/films/{name}")
    @ApiOperation(value = "Get information about a film", response = StarWarsResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved film information"),
            @ApiResponse(code = 404, message = "Film not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    public ResponseEntity<StarWarsResponse<Film>> getFilm(@PathVariable String name) {
        logger.info("Received request for film: {}", name);
        StarWarsResponse<Film> response = starWarsService.getFilmInfo(name);
        if (response.getData() == null) {
            throw new ResourceNotFoundException("Film not found: " + name);
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/species/{name}")
    @ApiOperation(value = "Get information about a species", response = StarWarsResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved species information"),
            @ApiResponse(code = 404, message = "Species not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    public ResponseEntity<StarWarsResponse<Species>> getSpecies(@PathVariable String name) {
        logger.info("Received request for species: {}", name);
        StarWarsResponse<Species> response = starWarsService.getSpeciesInfo(name);
        if (response.getData() == null) {
            throw new ResourceNotFoundException("Species not found: " + name);
        }
        return ResponseEntity.ok(response);
    }
}