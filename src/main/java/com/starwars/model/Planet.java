package com.starwars.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Planet {
    private String name;
    private String climate;
    private String terrain;
    private String population;
    @JsonProperty("rotation_period")
    private String rotationPeriod;
    @JsonProperty("orbital_period")
    private String orbitalPeriod;
    private String diameter;
    private String gravity;
    @JsonProperty("surface_water")
    private String surfaceWater;
    private List<String> residents;
    private List<String> films;
    private String created;
    private String edited;
    private String url;

}