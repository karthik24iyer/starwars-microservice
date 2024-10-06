package com.starwars.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StarWarsResponse<T> {
    private T data;
    private String message;
    private boolean success;
    private boolean offlineMode;

    public StarWarsResponse(T data, String message, boolean success, boolean offlineMode) {
        this.data = data;
        this.message = message;
        this.success = success;
        this.offlineMode = offlineMode;
    }
}