package com.login.exception;

public class LoginApplicationException extends Exception{

    private static final long serialVersionUID = 1L;

    public LoginApplicationException(String msg){
        super(msg);
    }
}
