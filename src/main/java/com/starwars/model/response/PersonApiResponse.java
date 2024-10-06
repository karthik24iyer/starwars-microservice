package com.starwars.model.response;

import com.starwars.model.Person;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PersonApiResponse {
    private int count;
    private String next;
    private String previous;
    private List<Person> results;
}