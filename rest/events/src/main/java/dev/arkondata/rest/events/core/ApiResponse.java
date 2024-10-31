package dev.arkondata.rest.events.core;

import org.springframework.http.HttpStatus;

public record ApiResponse(String message, HttpStatus httpStatus, boolean error, Object data) {
    public static ApiResponse ofError(HttpStatus status, String message) {
        return new ApiResponse(message, status, true, null);
    }

    public static ApiResponse ofOk(Object data) {
        return new ApiResponse("OK", HttpStatus.OK, false, data);
    }
}
