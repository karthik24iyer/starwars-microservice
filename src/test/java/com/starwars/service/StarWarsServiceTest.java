package com.starwars.service;

import com.starwars.config.StarWarsProperties;
import com.starwars.model.*;
import com.starwars.model.response.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StarWarsServiceTest {

    @Value("${starwars.api.base-url}")
    private String apiBaseUrl;
    
    @Mock
    private RestTemplate restTemplate;

    @Mock
    private StarWarsProperties properties;

    @InjectMocks
    private StarWarsService starWarsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getPlanetInfo_OnlineMode_Success() {
        String planetName = "Tatooine";
        String apiUrl = apiBaseUrl + "/planets/?search=" + planetName;
        PlanetApiResponse apiResponse = new PlanetApiResponse();
        Planet planet = new Planet();
        planet.setName(planetName);
        apiResponse.setResults(Collections.singletonList(planet));

        when(properties.isOfflineMode()).thenReturn(false);
        when(restTemplate.getForObject(eq(apiUrl), eq(PlanetApiResponse.class))).thenReturn(apiResponse);

        StarWarsResponse<Planet> result = starWarsService.getPlanetInfo(planetName);

        assertNotNull(result);
        assertTrue(result.isSuccess());
        assertEquals(planetName, result.getData().getName());
    }

    @Test
    void getPlanetInfo_OfflineMode_Success() {
        String planetName = "Tatooine";
        when(properties.isOfflineMode()).thenReturn(true);

        StarWarsResponse<Planet> result = starWarsService.getPlanetInfo(planetName);

        assertNotNull(result);
        assertTrue(result.isSuccess());
        assertEquals(planetName, result.getData().getName());
        assertTrue(result.getMessage().contains("Offline mode"));
    }

    @Test
    void getPlanetInfo_OnlineMode_NotFound() {
        String planetName = "NonExistentPlanet";
        String apiUrl = apiBaseUrl + "/planets/?search=" + planetName;
        PlanetApiResponse apiResponse = new PlanetApiResponse();
        apiResponse.setResults(Collections.emptyList());

        when(properties.isOfflineMode()).thenReturn(false);
        when(restTemplate.getForObject(eq(apiUrl), eq(PlanetApiResponse.class))).thenReturn(apiResponse);

        StarWarsResponse<Planet> result = starWarsService.getPlanetInfo(planetName);

        assertNotNull(result);
        assertFalse(result.isSuccess());
        assertNull(result.getData());
    }

    @Test
    void getSpaceshipInfo_OnlineMode_Success() {
        String spaceshipName = "Millennium Falcon";
        String apiUrl = apiBaseUrl + "/starships/?search=" + spaceshipName;
        SpaceshipApiResponse apiResponse = new SpaceshipApiResponse();
        Spaceship spaceship = new Spaceship();
        spaceship.setName(spaceshipName);
        apiResponse.setResults(Collections.singletonList(spaceship));

        when(properties.isOfflineMode()).thenReturn(false);
        when(restTemplate.getForObject(eq(apiUrl), eq(SpaceshipApiResponse.class))).thenReturn(apiResponse);

        StarWarsResponse<Spaceship> result = starWarsService.getSpaceshipInfo(spaceshipName);

        assertNotNull(result);
        assertTrue(result.isSuccess());
        assertEquals(spaceshipName, result.getData().getName());
    }

    @Test
    void getSpaceshipInfo_OfflineMode_Success() {
        String spaceshipName = "Millennium Falcon";
        when(properties.isOfflineMode()).thenReturn(true);

        StarWarsResponse<Spaceship> result = starWarsService.getSpaceshipInfo(spaceshipName);

        assertNotNull(result);
        assertTrue(result.isSuccess());
        assertEquals(spaceshipName, result.getData().getName());
        assertTrue(result.getMessage().contains("Offline mode"));
    }

    @Test
    void getVehicleInfo_OnlineMode_Success() {
        String vehicleName = "Sand Crawler";
        String apiUrl = apiBaseUrl + "/vehicles/?search=" + vehicleName;
        VehicleApiResponse apiResponse = new VehicleApiResponse();
        Vehicle vehicle = new Vehicle();
        vehicle.setName(vehicleName);
        apiResponse.setResults(Collections.singletonList(vehicle));

        when(properties.isOfflineMode()).thenReturn(false);
        when(restTemplate.getForObject(eq(apiUrl), eq(VehicleApiResponse.class))).thenReturn(apiResponse);

        StarWarsResponse<Vehicle> result = starWarsService.getVehicleInfo(vehicleName);

        assertNotNull(result);
        assertTrue(result.isSuccess());
        assertEquals(vehicleName, result.getData().getName());
    }

    @Test
    void getVehicleInfo_OfflineMode_Success() {
        String vehicleName = "Sand Crawler";
        when(properties.isOfflineMode()).thenReturn(true);

        StarWarsResponse<Vehicle> result = starWarsService.getVehicleInfo(vehicleName);

        assertNotNull(result);
        assertTrue(result.isSuccess());
        assertEquals(vehicleName, result.getData().getName());
        assertTrue(result.getMessage().contains("Offline mode"));
    }

    @Test
    void getPersonInfo_OnlineMode_Success() {
        String personName = "Luke Skywalker";
        String apiUrl = apiBaseUrl + "/people/?search=" + personName;
        PersonApiResponse apiResponse = new PersonApiResponse();
        Person person = new Person();
        person.setName(personName);
        apiResponse.setResults(Collections.singletonList(person));

        when(properties.isOfflineMode()).thenReturn(false);
        when(restTemplate.getForObject(eq(apiUrl), eq(PersonApiResponse.class))).thenReturn(apiResponse);

        StarWarsResponse<Person> result = starWarsService.getPersonInfo(personName);

        assertNotNull(result);
        assertTrue(result.isSuccess());
        assertEquals(personName, result.getData().getName());
    }

    @Test
    void getPersonInfo_OfflineMode_Success() {
        String personName = "Luke Skywalker";
        when(properties.isOfflineMode()).thenReturn(true);

        StarWarsResponse<Person> result = starWarsService.getPersonInfo(personName);

        assertNotNull(result);
        assertTrue(result.isSuccess());
        assertEquals(personName, result.getData().getName());
        assertTrue(result.getMessage().contains("Offline mode"));
    }

    @Test
    void getFilmInfo_OnlineMode_Success() {
        String filmName = "A New Hope";
        String apiUrl = apiBaseUrl + "/films/?search=" + filmName;
        FilmApiResponse apiResponse = new FilmApiResponse();
        Film film = new Film();
        film.setTitle(filmName);
        apiResponse.setResults(Collections.singletonList(film));

        when(properties.isOfflineMode()).thenReturn(false);
        when(restTemplate.getForObject(eq(apiUrl), eq(FilmApiResponse.class))).thenReturn(apiResponse);

        StarWarsResponse<Film> result = starWarsService.getFilmInfo(filmName);

        assertNotNull(result);
        assertTrue(result.isSuccess());
        assertEquals(filmName, result.getData().getTitle());
    }

    @Test
    void getFilmInfo_OfflineMode_Success() {
        String filmName = "A New Hope";
        when(properties.isOfflineMode()).thenReturn(true);

        StarWarsResponse<Film> result = starWarsService.getFilmInfo(filmName);

        assertNotNull(result);
        assertTrue(result.isSuccess());
        assertEquals(filmName, result.getData().getTitle());
        assertTrue(result.getMessage().contains("Offline mode"));
    }

    @Test
    void getSpeciesInfo_OnlineMode_Success() {
        String speciesName = "Human";
        String apiUrl = apiBaseUrl + "/species/?search=" + speciesName;
        SpeciesApiResponse apiResponse = new SpeciesApiResponse();
        Species species = new Species();
        species.setName(speciesName);
        apiResponse.setResults(Collections.singletonList(species));

        when(properties.isOfflineMode()).thenReturn(false);
        when(restTemplate.getForObject(eq(apiUrl), eq(SpeciesApiResponse.class))).thenReturn(apiResponse);

        StarWarsResponse<Species> result = starWarsService.getSpeciesInfo(speciesName);

        assertNotNull(result);
        assertTrue(result.isSuccess());
        assertEquals(speciesName, result.getData().getName());
    }

    @Test
    void getSpeciesInfo_OfflineMode_Success() {
        String speciesName = "Human";
        when(properties.isOfflineMode()).thenReturn(true);

        StarWarsResponse<Species> result = starWarsService.getSpeciesInfo(speciesName);

        assertNotNull(result);
        assertTrue(result.isSuccess());
        assertEquals(speciesName, result.getData().getName());
        assertTrue(result.getMessage().contains("Offline mode"));
    }
}