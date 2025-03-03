package com.springsecurity.exception;

public class UserNameAndPasswordNotMatch extends RuntimeException{

    public UserNameAndPasswordNotMatch(String message){
        super(message);
    }
}
