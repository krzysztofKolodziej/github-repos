package com.example.errorhandler;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

@Provider
public class ExceptionHandler implements ExceptionMapper<WebApplicationException> {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandler.class);

    private static final Map<Integer, String> ERROR_MESSAGES = Map.of(
            404, "GitHub user or repository not found.",
            403, "Rate limit exceeded. Please try again later.",
            500, "Internal server error."
    );

    @Override
    public Response toResponse(WebApplicationException exception) {
        int status = exception.getResponse().getStatus();
        String message = ERROR_MESSAGES.getOrDefault(status, "Unexpected error occurred.");

        LOGGER.error("Error with status {}: {}", status, exception.getMessage(), exception);

        ErrorResponse errorResponse = new ErrorResponse(status, message);

        return Response.status(status)
                .type(MediaType.APPLICATION_JSON)
                .entity(errorResponse)
                .build();
    }
}
