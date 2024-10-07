package com.starwars.service;

import com.starwars.model.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;
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
    private final CacheManager cacheManager;

    @Value("${starwars.api.base-url}")
    private String apiBaseUrl;

    @Autowired
    public StarWarsService(StarWarsProperties properties, RestTemplate restTemplate, CacheManager cacheManager) {
        this.properties = properties;
        this.restTemplate = restTemplate;
        this.cacheManager = cacheManager;
    }

    public StarWarsResponse<Planet> getPlanetInfo(String name) {
        logger.info("Fetching information for planet: {}", name);

        if (properties.isOfflineMode()) {
            logger.info("Operating in offline mode");
            return getCachedOrOfflinePlanetInfo(name);
        }

        try {
            String url = apiBaseUrl + "/planets/?search=" + name;
            logger.debug("Making API request to: {}", url);

            PlanetApiResponse response = restTemplate.getForObject(url, PlanetApiResponse.class);

            if (response != null && !response.getResults().isEmpty()) {
                Planet planet = response.getResults().get(0);
                logger.info("Successfully retrieved information for planet: {}", name);
                StarWarsResponse<Planet> starWarsResponse = new StarWarsResponse<>(planet, "Information about planet " + name, true, false);

                cacheManager.getCache("planets").put(name, starWarsResponse);

                return starWarsResponse;
            } else {
                logger.warn("No information found for planet: {}", name);
                return new StarWarsResponse<>(null, "Planet not found: " + name, false, false);
            }
        } catch (Exception e) {
            logger.error("Error fetching planet data: ", e);
            return getCachedOrOfflinePlanetInfo(name);
        }
    }

    private StarWarsResponse<Planet> getCachedOrOfflinePlanetInfo(String name) {
        StarWarsResponse<Planet> cachedResponse = cacheManager.getCache("planets").get(name, StarWarsResponse.class);
        if (cachedResponse != null) {
            logger.info("Retrieved planet information from cache: {}", name);
            return cachedResponse;
        }

        logger.info("No cached data found, returning offline data for planet: {}", name);
        Planet planet = new Planet();
        planet.setName(name);
        planet.setClimate("Unknown (Offline Mode)");
        planet.setTerrain("Unknown (Offline Mode)");
        planet.setPopulation("Unknown (Offline Mode)");
        return new StarWarsResponse<>(planet, "Offline mode: Information about planet " + name, true, true);
    }

    public StarWarsResponse<Spaceship> getSpaceshipInfo(String name) {
        logger.info("Fetching information for spaceship: {}", name);

        if (properties.isOfflineMode()) {
            logger.info("Operating in offline mode");
            return getCachedOrOfflineSpaceshipInfo(name);
        }

        try {
            String url = apiBaseUrl + "/starships/?search=" + name;
            logger.debug("Making API request to: {}", url);

            SpaceshipApiResponse response = restTemplate.getForObject(url, SpaceshipApiResponse.class);

            if (response != null && !response.getResults().isEmpty()) {
                Spaceship spaceship = response.getResults().get(0);
                logger.info("Successfully retrieved information for spaceship: {}", name);
                StarWarsResponse<Spaceship> starWarsResponse = new StarWarsResponse<>(spaceship, "Information about spaceship " + name, true, false);

                cacheManager.getCache("spaceships").put(name, starWarsResponse);

                return starWarsResponse;
            } else {
                logger.warn("No information found for spaceship: {}", name);
                return new StarWarsResponse<>(null, "Spaceship not found: " + name, false, false);
            }
        } catch (Exception e) {
            logger.error("Error fetching spaceship data: ", e);
            return getCachedOrOfflineSpaceshipInfo(name);
        }
    }

    private StarWarsResponse<Spaceship> getCachedOrOfflineSpaceshipInfo(String name) {
        StarWarsResponse<Spaceship> cachedResponse = cacheManager.getCache("spaceships").get(name, StarWarsResponse.class);
        if (cachedResponse != null) {
            logger.info("Retrieved spaceship information from cache: {}", name);
            return cachedResponse;
        }

        logger.info("No cached data found, returning offline data for spaceship: {}", name);
        Spaceship spaceship = new Spaceship();
        spaceship.setName(name);
        spaceship.setModel("Unknown (Offline Mode)");
        spaceship.setManufacturer("Unknown (Offline Mode)");
        return new StarWarsResponse<>(spaceship, "Offline mode: Information about spaceship " + name, true, true);
    }

    public StarWarsResponse<Vehicle> getVehicleInfo(String name) {
        logger.info("Fetching information for vehicle: {}", name);

        if (properties.isOfflineMode()) {
            logger.info("Operating in offline mode");
            return getCachedOrOfflineVehicleInfo(name);
        }

        try {
            String url = apiBaseUrl + "/vehicles/?search=" + name;
            logger.debug("Making API request to: {}", url);

            VehicleApiResponse response = restTemplate.getForObject(url, VehicleApiResponse.class);

            if (response != null && !response.getResults().isEmpty()) {
                Vehicle vehicle = response.getResults().get(0);
                logger.info("Successfully retrieved information for vehicle: {}", name);
                StarWarsResponse<Vehicle> starWarsResponse = new StarWarsResponse<>(vehicle, "Information about vehicle " + name, true, false);

                cacheManager.getCache("vehicles").put(name, starWarsResponse);

                return starWarsResponse;
            } else {
                logger.warn("No information found for vehicle: {}", name);
                return new StarWarsResponse<>(null, "Vehicle not found: " + name, false, false);
            }
        } catch (Exception e) {
            logger.error("Error fetching vehicle data: ", e);
            return getCachedOrOfflineVehicleInfo(name);
        }
    }

    private StarWarsResponse<Vehicle> getCachedOrOfflineVehicleInfo(String name) {
        StarWarsResponse<Vehicle> cachedResponse = cacheManager.getCache("vehicles").get(name, StarWarsResponse.class);
        if (cachedResponse != null) {
            logger.info("Retrieved vehicle information from cache: {}", name);
            return cachedResponse;
        }

        logger.info("No cached data found, returning offline data for vehicle: {}", name);
        Vehicle vehicle = new Vehicle();
        vehicle.setName(name);
        vehicle.setModel("Unknown (Offline Mode)");
        vehicle.setManufacturer("Unknown (Offline Mode)");
        return new StarWarsResponse<>(vehicle, "Offline mode: Information about vehicle " + name, true, true);
    }

    public StarWarsResponse<Person> getPersonInfo(String name) {
        logger.info("Fetching information for person: {}", name);

        if (properties.isOfflineMode()) {
            logger.info("Operating in offline mode");
            return getCachedOrOfflinePersonInfo(name);
        }

        try {
            String url = apiBaseUrl + "/people/?search=" + name;
            logger.debug("Making API request to: {}", url);

            PersonApiResponse response = restTemplate.getForObject(url, PersonApiResponse.class);

            if (response != null && !response.getResults().isEmpty()) {
                Person person = response.getResults().get(0);
                logger.info("Successfully retrieved information for person: {}", name);
                StarWarsResponse<Person> starWarsResponse = new StarWarsResponse<>(person, "Information about person " + name, true, false);

                cacheManager.getCache("people").put(name, starWarsResponse);

                return starWarsResponse;
            } else {
                logger.warn("No information found for person: {}", name);
                return new StarWarsResponse<>(null, "Person not found: " + name, false, false);
            }
        } catch (Exception e) {
            logger.error("Error fetching person data: ", e);
            return getCachedOrOfflinePersonInfo(name);
        }
    }

    private StarWarsResponse<Person> getCachedOrOfflinePersonInfo(String name) {
        StarWarsResponse<Person> cachedResponse = cacheManager.getCache("people").get(name, StarWarsResponse.class);
        if (cachedResponse != null) {
            logger.info("Retrieved person information from cache: {}", name);
            return cachedResponse;
        }

        logger.info("No cached data found, returning offline data for person: {}", name);
        Person person = new Person();
        person.setName(name);
        person.setBirthYear("Unknown (Offline Mode)");
        person.setGender("Unknown (Offline Mode)");
        return new StarWarsResponse<>(person, "Offline mode: Information about person " + name, true, true);
    }

    public StarWarsResponse<Film> getFilmInfo(String name) {
        logger.info("Fetching information for film: {}", name);

        if (properties.isOfflineMode()) {
            logger.info("Operating in offline mode");
            return getCachedOrOfflineFilmInfo(name);
        }

        try {
            String url = apiBaseUrl + "/films/?search=" + name;
            logger.debug("Making API request to: {}", url);

            FilmApiResponse response = restTemplate.getForObject(url, FilmApiResponse.class);

            if (response != null && !response.getResults().isEmpty()) {
                Film film = response.getResults().get(0);
                logger.info("Successfully retrieved information for film: {}", name);
                StarWarsResponse<Film> starWarsResponse = new StarWarsResponse<>(film, "Information about film " + name, true, false);

                cacheManager.getCache("films").put(name, starWarsResponse);

                return starWarsResponse;
            } else {
                logger.warn("No information found for film: {}", name);
                return new StarWarsResponse<>(null, "Film not found: " + name, false, false);
            }
        } catch (Exception e) {
            logger.error("Error fetching film data: ", e);
            return getCachedOrOfflineFilmInfo(name);
        }
    }

    private StarWarsResponse<Film> getCachedOrOfflineFilmInfo(String name) {
        StarWarsResponse<Film> cachedResponse = cacheManager.getCache("films").get(name, StarWarsResponse.class);
        if (cachedResponse != null) {
            logger.info("Retrieved film information from cache: {}", name);
            return cachedResponse;
        }

        logger.info("No cached data found, returning offline data for film: {}", name);
        Film film = new Film();
        film.setTitle(name);
        film.setDirector("Unknown (Offline Mode)");
        film.setReleaseDate("Unknown (Offline Mode)");
        return new StarWarsResponse<>(film, "Offline mode: Information about film " + name, true, true);
    }

    public StarWarsResponse<Species> getSpeciesInfo(String name) {
        logger.info("Fetching information for species: {}", name);

        if (properties.isOfflineMode()) {
            logger.info("Operating in offline mode");
            return getCachedOrOfflineSpeciesInfo(name);
        }

        try {
            String url = apiBaseUrl + "/species/?search=" + name;
            logger.debug("Making API request to: {}", url);

            SpeciesApiResponse response = restTemplate.getForObject(url, SpeciesApiResponse.class);

            if (response != null && !response.getResults().isEmpty()) {
                Species species = response.getResults().get(0);
                logger.info("Successfully retrieved information for species: {}", name);
                StarWarsResponse<Species> starWarsResponse = new StarWarsResponse<>(species, "Information about species " + name, true, false);

                cacheManager.getCache("species").put(name, starWarsResponse);

                return starWarsResponse;
            } else {
                logger.warn("No information found for species: {}", name);
                return new StarWarsResponse<>(null, "Species not found: " + name, false, false);
            }
        } catch (Exception e) {
            logger.error("Error fetching species data: ", e);
            return getCachedOrOfflineSpeciesInfo(name);
        }
    }

    private StarWarsResponse<Species> getCachedOrOfflineSpeciesInfo(String name) {
        StarWarsResponse<Species> cachedResponse = cacheManager.getCache("species").get(name, StarWarsResponse.class);
        if (cachedResponse != null) {
            logger.info("Retrieved species information from cache: {}", name);
            return cachedResponse;
        }

        logger.info("No cached data found, returning offline data for species: {}", name);
        Species species = new Species();
        species.setName(name);
        species.setClassification("Unknown (Offline Mode)");
        species.setDesignation("Unknown (Offline Mode)");
        return new StarWarsResponse<>(species, "Offline mode: Information about species " + name, true, true);
    }
}