package com.h2.demo.exception;

public class ConflictSocialSecurityNumberException extends RuntimeException{

    public ConflictSocialSecurityNumberException(String socialSecurityNumber) {
        super(String.format("Record with SSN %s already exists in the system", socialSecurityNumber));
    }
}
