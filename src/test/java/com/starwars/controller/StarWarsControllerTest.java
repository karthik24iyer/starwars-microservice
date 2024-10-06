package com.starwars.controller;

import com.starwars.model.*;
import com.starwars.service.StarWarsService;
import com.starwars.exception.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StarWarsControllerTest {

    @Mock
    private StarWarsService starWarsService;

    @InjectMocks
    private StarWarsController starWarsController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getPlanet_Success() {
        String planetName = "Tatooine";
        Planet planet = new Planet();
        planet.setName(planetName);
        StarWarsResponse<Planet> serviceResponse = new StarWarsResponse<>(planet, "Success", true, false);
        when(starWarsService.getPlanetInfo(planetName)).thenReturn(serviceResponse);

        ResponseEntity<StarWarsResponse<Planet>> response = starWarsController.getPlanet(planetName);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(planetName, response.getBody().getData().getName());
    }

    @Test
    void getPlanet_NotFound() {
        String planetName = "NonExistentPlanet";
        StarWarsResponse<Planet> serviceResponse = new StarWarsResponse<>(null, "Not found", false, false);
        when(starWarsService.getPlanetInfo(planetName)).thenReturn(serviceResponse);

        assertThrows(ResourceNotFoundException.class, () -> starWarsController.getPlanet(planetName));
    }

    @Test
    void getSpaceship_Success() {
        String spaceshipName = "Millennium Falcon";
        Spaceship spaceship = new Spaceship();
        spaceship.setName(spaceshipName);
        StarWarsResponse<Spaceship> serviceResponse = new StarWarsResponse<>(spaceship, "Success", true, false);
        when(starWarsService.getSpaceshipInfo(spaceshipName)).thenReturn(serviceResponse);

        ResponseEntity<StarWarsResponse<Spaceship>> response = starWarsController.getSpaceship(spaceshipName);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(spaceshipName, response.getBody().getData().getName());
    }

    @Test
    void getSpaceship_NotFound() {
        String spaceshipName = "NonExistentSpaceship";
        StarWarsResponse<Spaceship> serviceResponse = new StarWarsResponse<>(null, "Not found", false, false);
        when(starWarsService.getSpaceshipInfo(spaceshipName)).thenReturn(serviceResponse);

        assertThrows(ResourceNotFoundException.class, () -> starWarsController.getSpaceship(spaceshipName));
    }

    @Test
    void getVehicle_Success() {
        String vehicleName = "Sand Crawler";
        Vehicle vehicle = new Vehicle();
        vehicle.setName(vehicleName);
        StarWarsResponse<Vehicle> serviceResponse = new StarWarsResponse<>(vehicle, "Success", true, false);
        when(starWarsService.getVehicleInfo(vehicleName)).thenReturn(serviceResponse);

        ResponseEntity<StarWarsResponse<Vehicle>> response = starWarsController.getVehicle(vehicleName);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(vehicleName, response.getBody().getData().getName());
    }

    @Test
    void getVehicle_NotFound() {
        String vehicleName = "NonExistentVehicle";
        StarWarsResponse<Vehicle> serviceResponse = new StarWarsResponse<>(null, "Not found", false, false);
        when(starWarsService.getVehicleInfo(vehicleName)).thenReturn(serviceResponse);

        assertThrows(ResourceNotFoundException.class, () -> starWarsController.getVehicle(vehicleName));
    }

    @Test
    void getPerson_Success() {
        String personName = "Luke Skywalker";
        Person person = new Person();
        person.setName(personName);
        StarWarsResponse<Person> serviceResponse = new StarWarsResponse<>(person, "Success", true, false);
        when(starWarsService.getPersonInfo(personName)).thenReturn(serviceResponse);

        ResponseEntity<StarWarsResponse<Person>> response = starWarsController.getPerson(personName);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(personName, response.getBody().getData().getName());
    }

    @Test
    void getPerson_NotFound() {
        String personName = "NonExistentPerson";
        StarWarsResponse<Person> serviceResponse = new StarWarsResponse<>(null, "Not found", false, false);
        when(starWarsService.getPersonInfo(personName)).thenReturn(serviceResponse);

        assertThrows(ResourceNotFoundException.class, () -> starWarsController.getPerson(personName));
    }

    @Test
    void getFilm_Success() {
        String filmName = "A New Hope";
        Film film = new Film();
        film.setTitle(filmName);
        StarWarsResponse<Film> serviceResponse = new StarWarsResponse<>(film, "Success", true, false);
        when(starWarsService.getFilmInfo(filmName)).thenReturn(serviceResponse);

        ResponseEntity<StarWarsResponse<Film>> response = starWarsController.getFilm(filmName);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(filmName, response.getBody().getData().getTitle());
    }

    @Test
    void getFilm_NotFound() {
        String filmName = "NonExistentFilm";
        StarWarsResponse<Film> serviceResponse = new StarWarsResponse<>(null, "Not found", false, false);
        when(starWarsService.getFilmInfo(filmName)).thenReturn(serviceResponse);

        assertThrows(ResourceNotFoundException.class, () -> starWarsController.getFilm(filmName));
    }

    @Test
    void getSpecies_Success() {
        String speciesName = "Human";
        Species species = new Species();
        species.setName(speciesName);
        StarWarsResponse<Species> serviceResponse = new StarWarsResponse<>(species, "Success", true, false);
        when(starWarsService.getSpeciesInfo(speciesName)).thenReturn(serviceResponse);

        ResponseEntity<StarWarsResponse<Species>> response = starWarsController.getSpecies(speciesName);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(speciesName, response.getBody().getData().getName());
    }

    @Test
    void getSpecies_NotFound() {
        String speciesName = "NonExistentSpecies";
        StarWarsResponse<Species> serviceResponse = new StarWarsResponse<>(null, "Not found", false, false);
        when(starWarsService.getSpeciesInfo(speciesName)).thenReturn(serviceResponse);

        assertThrows(ResourceNotFoundException.class, () -> starWarsController.getSpecies(speciesName));
    }
}