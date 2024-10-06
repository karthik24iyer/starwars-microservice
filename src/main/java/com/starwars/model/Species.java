package com.starwars.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Species {
    private String name;
    private String classification;
    private String designation;
    @JsonProperty("average_height")
    private String averageHeight;
    @JsonProperty("average_lifespan")
    private String averageLifespan;
    @JsonProperty("eye_colors")
    private String eyeColors;
    @JsonProperty("hair_colors")
    private String hairColors;
    @JsonProperty("skin_colors")
    private String skinColors;
    private String language;
    private String homeworld;
    private List<String> people;
    private List<String> films;
    private String created;
    private String edited;
    private String url;
}