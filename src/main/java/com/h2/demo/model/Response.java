package com.h2.demo.model;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class Response {
    private HttpStatus status;
    private String code;
    private Object data;
}
