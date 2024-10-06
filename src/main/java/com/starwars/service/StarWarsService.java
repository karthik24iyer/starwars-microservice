package com.starwars.service;

import com.starwars.model.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import com.starwars.config.StarWarsProperties;
import com.starwars.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class StarWarsService {

    private static final Logger logger = LoggerFactory.getLogger(StarWarsService.class);

    private final StarWarsProperties properties;
    private final RestTemplate restTemplate;

    @Value("${starwars.api.base-url}")
    private String apiBaseUrl;

    @Autowired
    public StarWarsService(StarWarsProperties properties, RestTemplate restTemplate) {
        this.properties = properties;
        this.restTemplate = restTemplate;
    }

    @Cacheable(value = "planets", key = "#name", unless = "#result == null")
    public StarWarsResponse<Planet> getPlanetInfo(String name) {
        logger.info("Fetching information for planet: {}", name);

        if (properties.isOfflineMode()) {
            logger.info("Operating in offline mode");
            return getOfflinePlanetInfo(name);
        }

        try {
            String url = apiBaseUrl + "/planets/?search=" + name;
            logger.debug("Making API request to: {}", url);

            PlanetApiResponse response = restTemplate.getForObject(url, PlanetApiResponse.class);

            if (response != null && !response.getResults().isEmpty()) {
                Planet planet = response.getResults().get(0);
                logger.info("Successfully retrieved information for planet: {}", name);
                return new StarWarsResponse<>(planet, "Information about planet " + name, true, false);
            } else {
                logger.warn("No information found for planet: {}", name);
                return new StarWarsResponse<>(null, "Planet not found: " + name, false, false);
            }
        } catch (HttpClientErrorException e) {
            logger.error("HTTP error when fetching planet data: {}", e.getStatusCode(), e);
            return new StarWarsResponse<>(null, "Error fetching planet data: " + e.getStatusCode(), false, false);
        } catch (RestClientException e) {
            logger.error("Error connecting to Star Wars API", e);
            return new StarWarsResponse<>(null, "Error connecting to Star Wars API", false, false);
        }
    }

    @Cacheable(value = "spaceships", key = "#name", unless = "#result == null")
    public StarWarsResponse<Spaceship> getSpaceshipInfo(String name) {
        logger.info("Fetching information for spaceship: {}", name);

        if (properties.isOfflineMode()) {
            logger.info("Operating in offline mode");
            return getOfflineSpaceshipInfo(name);
        }

        try {
            String url = apiBaseUrl + "/starships/?search=" + name;
            logger.debug("Making API request to: {}", url);

            SpaceshipApiResponse response = restTemplate.getForObject(url, SpaceshipApiResponse.class);

            if (response != null && !response.getResults().isEmpty()) {
                Spaceship spaceship = response.getResults().get(0);
                logger.info("Successfully retrieved information for spaceship: {}", name);
                return new StarWarsResponse<>(spaceship, "Information about spaceship " + name, true, false);
            } else {
                logger.warn("No information found for spaceship: {}", name);
                return new StarWarsResponse<>(null, "Spaceship not found: " + name, false, false);
            }
        } catch (HttpClientErrorException e) {
            logger.error("HTTP error when fetching spaceship data: {}", e.getStatusCode(), e);
            return new StarWarsResponse<>(null, "Error fetching spaceship data: " + e.getStatusCode(), false, false);
        } catch (RestClientException e) {
            logger.error("Error connecting to Star Wars API", e);
            return new StarWarsResponse<>(null, "Error connecting to Star Wars API", false, false);
        }
    }

    @Cacheable(value = "vehicles", key = "#name", unless = "#result == null")
    public StarWarsResponse<Vehicle> getVehicleInfo(String name) {
        logger.info("Fetching information for vehicle: {}", name);

        if (properties.isOfflineMode()) {
            logger.info("Operating in offline mode");
            return getOfflineVehicleInfo(name);
        }

        try {
            String url = apiBaseUrl + "/vehicles/?search=" + name;
            logger.debug("Making API request to: {}", url);

            VehicleApiResponse response = restTemplate.getForObject(url, VehicleApiResponse.class);

            if (response != null && !response.getResults().isEmpty()) {
                Vehicle vehicle = response.getResults().get(0);
                logger.info("Successfully retrieved information for vehicle: {}", name);
                return new StarWarsResponse<>(vehicle, "Information about vehicle " + name, true, false);
            } else {
                logger.warn("No information found for vehicle: {}", name);
                return new StarWarsResponse<>(null, "Vehicle not found: " + name, false, false);
            }
        } catch (HttpClientErrorException e) {
            logger.error("HTTP error when fetching vehicle data: {}", e.getStatusCode(), e);
            return new StarWarsResponse<>(null, "Error fetching vehicle data: " + e.getStatusCode(), false, false);
        } catch (RestClientException e) {
            logger.error("Error connecting to Star Wars API", e);
            return new StarWarsResponse<>(null, "Error connecting to Star Wars API", false, false);
        }
    }

    @Cacheable(value = "people", key = "#name", unless = "#result == null")
    public StarWarsResponse<Person> getPersonInfo(String name) {
        logger.info("Fetching information for person: {}", name);

        if (properties.isOfflineMode()) {
            logger.info("Operating in offline mode");
            return getOfflinePersonInfo(name);
        }

        try {
            String url = apiBaseUrl + "/people/?search=" + name;
            logger.debug("Making API request to: {}", url);

            PersonApiResponse response = restTemplate.getForObject(url, PersonApiResponse.class);

            if (response != null && !response.getResults().isEmpty()) {
                Person person = response.getResults().get(0);
                logger.info("Successfully retrieved information for person: {}", name);
                return new StarWarsResponse<>(person, "Information about person " + name, true, false);
            } else {
                logger.warn("No information found for person: {}", name);
                return new StarWarsResponse<>(null, "Person not found: " + name, false, false);
            }
        } catch (HttpClientErrorException e) {
            logger.error("HTTP error when fetching person data: {}", e.getStatusCode(), e);
            return new StarWarsResponse<>(null, "Error fetching person data: " + e.getStatusCode(), false, false);
        } catch (RestClientException e) {
            logger.error("Error connecting to Star Wars API", e);
            return new StarWarsResponse<>(null, "Error connecting to Star Wars API", false, false);
        }
    }

    @Cacheable(value = "films", key = "#name", unless = "#result == null")
    public StarWarsResponse<Film> getFilmInfo(String name) {
        logger.info("Fetching information for film: {}", name);

        if (properties.isOfflineMode()) {
            logger.info("Operating in offline mode");
            return getOfflineFilmInfo(name);
        }

        try {
            String url = apiBaseUrl + "/films/?search=" + name;
            logger.debug("Making API request to: {}", url);

            FilmApiResponse response = restTemplate.getForObject(url, FilmApiResponse.class);

            if (response != null && !response.getResults().isEmpty()) {
                Film film = response.getResults().get(0);
                logger.info("Successfully retrieved information for film: {}", name);
                return new StarWarsResponse<>(film, "Information about film " + name, true, false);
            } else {
                logger.warn("No information found for film: {}", name);
                return new StarWarsResponse<>(null, "Film not found: " + name, false, false);
            }
        } catch (HttpClientErrorException e) {
            logger.error("HTTP error when fetching film data: {}", e.getStatusCode(), e);
            return new StarWarsResponse<>(null, "Error fetching film data: " + e.getStatusCode(), false, false);
        } catch (RestClientException e) {
            logger.error("Error connecting to Star Wars API", e);
            return new StarWarsResponse<>(null, "Error connecting to Star Wars API", false, false);
        }
    }

    @Cacheable(value = "species", key = "#name", unless = "#result == null")
    public StarWarsResponse<Species> getSpeciesInfo(String name) {
        logger.info("Fetching information for species: {}", name);

        if (properties.isOfflineMode()) {
            logger.info("Operating in offline mode");
            return getOfflineSpeciesInfo(name);
        }

        try {
            String url = apiBaseUrl + "/species/?search=" + name;
            logger.debug("Making API request to: {}", url);

            SpeciesApiResponse response = restTemplate.getForObject(url, SpeciesApiResponse.class);

            if (response != null && !response.getResults().isEmpty()) {
                Species species = response.getResults().get(0);
                logger.info("Successfully retrieved information for species: {}", name);
                return new StarWarsResponse<>(species, "Information about species " + name, true, false);
            } else {
                logger.warn("No information found for species: {}", name);
                return new StarWarsResponse<>(null, "Species not found: " + name, false, false);
            }
        } catch (HttpClientErrorException e) {
            logger.error("HTTP error when fetching species data: {}", e.getStatusCode(), e);
            return new StarWarsResponse<>(null, "Error fetching species data: " + e.getStatusCode(), false, false);
        } catch (RestClientException e) {
            logger.error("Error connecting to Star Wars API", e);
            return new StarWarsResponse<>(null, "Error connecting to Star Wars API", false, false);
        }
    }

    private StarWarsResponse<Planet> getOfflinePlanetInfo(String name) {
        Planet planet = new Planet();
        planet.setName(name);
        planet.setClimate("Unknown (Offline Mode)");
        planet.setTerrain("Unknown (Offline Mode)");
        planet.setPopulation("Unknown (Offline Mode)");
        return new StarWarsResponse<>(planet, "Offline mode: Information about planet " + name, true, true);
    }

    private StarWarsResponse<Spaceship> getOfflineSpaceshipInfo(String name) {
        Spaceship spaceship = new Spaceship();
        spaceship.setName(name);
        spaceship.setModel("Unknown (Offline Mode)");
        spaceship.setManufacturer("Unknown (Offline Mode)");
        spaceship.setCrew("Unknown (Offline Mode)");
        return new StarWarsResponse<>(spaceship, "Offline mode: Information about spaceship " + name, true, true);
    }

    private StarWarsResponse<Vehicle> getOfflineVehicleInfo(String name) {
        Vehicle vehicle = new Vehicle();
        vehicle.setName(name);
        vehicle.setModel("Unknown (Offline Mode)");
        vehicle.setManufacturer("Unknown (Offline Mode)");
        vehicle.setVehicleClass("Unknown (Offline Mode)");
        return new StarWarsResponse<>(vehicle, "Offline mode: Information about vehicle " + name, true, true);
    }

    private StarWarsResponse<Person> getOfflinePersonInfo(String name) {
        Person person = new Person();
        person.setName(name);
        person.setBirthYear("Unknown (Offline Mode)");
        person.setGender("Unknown (Offline Mode)");
        person.setHomeworld("Unknown (Offline Mode)");
        return new StarWarsResponse<>(person, "Offline mode: Information about person " + name, true, true);
    }

    private StarWarsResponse<Film> getOfflineFilmInfo(String name) {
        Film film = new Film();
        film.setTitle(name);
        film.setEpisodeId(0);
        film.setDirector("Unknown (Offline Mode)");
        film.setReleaseDate("Unknown (Offline Mode)");
        return new StarWarsResponse<>(film, "Offline mode: Information about film " + name, true, true);
    }

    private StarWarsResponse<Species> getOfflineSpeciesInfo(String name) {
        Species species = new Species();
        species.setName(name);
        species.setClassification("Unknown (Offline Mode)");
        species.setDesignation("Unknown (Offline Mode)");
        species.setAverageLifespan("Unknown (Offline Mode)");
        species.setHomeworld("Unknown (Offline Mode)");
        return new StarWarsResponse<>(species, "Offline mode: Information about species " + name, true, true);
    }
}