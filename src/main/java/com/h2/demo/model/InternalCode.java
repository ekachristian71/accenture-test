package com.h2.demo.model;

public enum InternalCode {
    USER_NOT_FOUND("30000"),
    INVALID_REQUEST("30001"),

    CONFLICT_SOCIAL_SECURITY_NUMBER("30002");

    private final String code;

    private InternalCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
