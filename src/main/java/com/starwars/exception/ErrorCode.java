package com.starwars.exception;

public enum ErrorCode {
    RESOURCE_NOT_FOUND(404, "Requested resource not found"),
    INTERNAL_SERVER_ERROR(500, "An unexpected error occurred on the server"),
    BAD_REQUEST(400, "Invalid request parameters");

    private final int status;
    private final String message;

    ErrorCode(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}