package com.h2.demo.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(long id) {
        super(String.format("No such resource with id  %d", id));
    }
}
