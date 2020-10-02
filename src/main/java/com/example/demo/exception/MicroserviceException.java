package com.example.demo.exception;

import org.springframework.http.HttpStatus;

/**
 * The custom exception for wrapping issues with communicating with the Microservice
 */
public class MicroserviceException extends RuntimeException{

    private HttpStatus statusCode;
    private String error;

    /**
     * Constructs the Microservice exception with the given status code and error
     * @param statusCode The HTTP status code
     * @param error The error message
     */
    public MicroserviceException(HttpStatus statusCode, String error) {
        super(error);
        this.statusCode = statusCode;
        this.error = error;
    }

    /**
     * Returns the status code
     * @return the status code
     */
    public HttpStatus getStatusCode() {
        return statusCode;
    }

    /**
     * Sets the status code
     * @param statusCode the status code to set to the Microservice exception
     */
    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * Returns the error message
     * @return
     */
    public String getError() {
        return error;
    }

    /**
     * Sets the error message
     * @param error the error message to set on the Microservice exception
     */
    public void setError(String error) {
        this.error = error;
    }

    /**
     * Returns a string representation of the MicroserviceException
     * @return a string representation of the MicroserviceException
     */
    public String toString() {
        return this.error + "(" + this.statusCode + ")";
    }

}
