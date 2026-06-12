package com.bus.exception;

public class AuthenticationException extends RuntimeException {
    public AuthenticationException (String message){
        super(message);
    }   
}
