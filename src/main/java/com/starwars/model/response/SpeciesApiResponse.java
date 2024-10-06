package com.starwars.model.response;


import com.starwars.model.Species;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class SpeciesApiResponse {
    private int count;
    private String next;
    private String previous;
    private List<Species> results;

}