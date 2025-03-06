package com.example.errorhandler;

public record ErrorResponse(
        int status,
        String message
) {
}
