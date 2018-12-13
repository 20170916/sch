package com.lo.core.exception;

public class SchException extends RuntimeException {
    public SchException(String message){
        super(message);
    }
    public SchException(String message,Throwable e){
        super(message,e);
    }
}
