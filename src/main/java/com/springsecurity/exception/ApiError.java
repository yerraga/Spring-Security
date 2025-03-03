package com.springsecurity.exception;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@Data
public class ApiError {

    private HttpStatus status;
    private String message;
    private Object details;
    private List<String> errors =new ArrayList<>();

    public ApiError(final HttpStatus status, final String message, final List<String> errors) {
        this.status = status;
        this.message = message;
        this.errors.addAll(errors);
    }

    public ApiError(final HttpStatus status, final String message, final String error) {
        this.status = status;
        this.message = message;
        this.errors.add(error);
    }

    public ApiError(final HttpStatus status, final String message) {
        this.status = status;
        this.message = message;
        if(HttpStatus.OK.value() != status.value()) {
            this.errors.add(message);
        }
    }

    public ApiError(HttpStatus status, String message, Object details) {
        super();
        this.status = status;
        this.message = message;
        this.details = details;
    }
}

