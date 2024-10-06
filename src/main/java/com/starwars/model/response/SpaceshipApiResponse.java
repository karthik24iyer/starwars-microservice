package com.starwars.model.response;


import com.starwars.model.Spaceship;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SpaceshipApiResponse {
    private int count;
    private String next;
    private String previous;
    private List<Spaceship> results;

}