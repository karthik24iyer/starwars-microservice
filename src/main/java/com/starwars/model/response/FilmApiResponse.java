package com.starwars.model.response;


import com.starwars.model.Film;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FilmApiResponse {
    private int count;
    private String next;
    private String previous;
    private List<Film> results;
}