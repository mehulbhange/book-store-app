package com.bridgelabz.bookstore.exception;

public class UserAuthenticationException extends RuntimeException{
    public UserAuthenticationException(String msg){
        super(msg);
    }
}
