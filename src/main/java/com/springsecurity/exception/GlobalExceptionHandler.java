package com.springsecurity.exception;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiError>handleUserNotFoundException(UserNotFoundException ex){
        log.error(ex.getMessage() + ex);
        return new  ResponseEntity<>(new ApiError( HttpStatus.NOT_FOUND, ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNameAlreadyExistException.class)
    public ResponseEntity<ApiError> handleUserNameAlreadyExistException(UserNameAlreadyExistException exception){
        return new ResponseEntity<>(new ApiError(HttpStatus.CONFLICT, exception.getMessage()), HttpStatus.CONFLICT);
    }
    @ExceptionHandler(UserNameAndPasswordNotMatch.class)
    public ResponseEntity<ApiError> handleUserNameAndPasswordNotMatch(UserNameAndPasswordNotMatch exception){
        return new ResponseEntity<>(new ApiError(HttpStatus.BAD_REQUEST, exception.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
