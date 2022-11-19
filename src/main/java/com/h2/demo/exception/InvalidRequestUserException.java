package com.h2.demo.exception;

public class InvalidRequestUserException extends RuntimeException {

    public InvalidRequestUserException(String fieldName, String fieldValue) {
        super(String.format("Invalid value for field  %s ,  rejected value: %s", fieldName, fieldValue));
    }
}
