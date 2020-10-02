package com.example.demo.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * The main exception handler for preventing microservice execution from bubbling up to the controller
 */
@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandler.class);

    /**
     * Handles the actual exception
     * @param ex the {@link MicroserviceException} being thrown
     * @param request the {@link HttpServletRequest} that caused the exception
     * @return
     */
    // this exception handler only seems capable of catching specific errors reported by the microservice
    @org.springframework.web.bind.annotation.ExceptionHandler(value = MicroserviceException.class)
    ResponseEntity<ErrorResponse> handleMicroserviceException(MicroserviceException ex, HttpServletRequest request) {
        LOGGER.error("An error happened while calling microservice: {}", ex.toString());
        return new ResponseEntity<>(new ErrorResponse(ex, request.getRequestURI()), ex.getStatusCode());
    }
}