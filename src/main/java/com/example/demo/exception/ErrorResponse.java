package com.example.demo.exception;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Template class for a generic error response, for use with our custom exception handler
 */
public class ErrorResponse {
    private String timestamp;
    private int status;
    private String error;
    private String message;
    private String path;

    /**
     * Constructs the ErrorResponse object with the given {@link MicroserviceException} and path
     * @param ex the {@link MicroserviceException}
     * @param path the URI path
     */
    public ErrorResponse(MicroserviceException ex, String path) {
        this.timestamp = DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(LocalDateTime.now());
        this.status = ex.getStatusCode().value();
        this.error = ex.getStatusCode().getReasonPhrase();
        this.message = ex.getError();
        this.path = path;
    }

    /**
     * Returns the ErrorResponse object's timestamp
     * @return the ErrorResponse object's timestamp
     */
    public String getTimestamp() {
        return timestamp;
    }

    /**
     * Sets the ErrorResponse object's timestamp
     * @param timestamp the timestamp for the ErrorResponse
     */
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Returns the ErrorResponse object's status
     * @return the status of the ErrorResponse
     */
    public int getStatus() {
        return status;
    }

    /**
     * Sets the ErrorResponse object's status
     * @param status the status for the ErrorResponse
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * Returns the ErrorResponse object's error
     * @return the error of the ErrorResponse object
     */
    public String getError() {
        return error;
    }

    /**
     * Sets the ErrorResponse object's error
     * @param error the error for the ErrorResponse
     */
    public void setError(String error) {
        this.error = error;
    }

    /**
     * Returns the ErrorResponse object's message
     * @return the message of the ErrorResponse object
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the ErrorResponse object's message
     * @param message the message for the ErrorResponse
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Returns the ErrorResponse object's path
     * @return the path of the ErrorResponse object
     */
    public String getPath() {
        return path;
    }

    /**
     * Sets the ErrorResponse object's path
     * @param path the path for the ErrorResponse
     */
    public void setPath(String path) {
        this.path = path;
    }

}
