package com.springsecurity.exception;

public class UserNameAlreadyExistException extends RuntimeException{

    public UserNameAlreadyExistException (String message){
        super(message);
    }
}
