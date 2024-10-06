package com.starwars.model.response;

import com.starwars.model.Planet;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PlanetApiResponse {
    private int count;
    private String next;
    private String previous;
    private List<Planet> results;
}