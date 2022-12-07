package com.revature.P1AndrewBrittain.Util.Exceptions;

public class InvalidTokenException extends RuntimeException{
    public InvalidTokenException() {
        super("Token expired please log in again to generate new token" +
                "");
    }
}
