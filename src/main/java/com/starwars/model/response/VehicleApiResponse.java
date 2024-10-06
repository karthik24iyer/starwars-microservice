package com.starwars.model.response;

import com.starwars.model.Vehicle;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class VehicleApiResponse {
    private int count;
    private String next;
    private String previous;
    private List<Vehicle> results;
}