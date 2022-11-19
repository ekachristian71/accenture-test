package com.h2.demo.controller;

import com.h2.demo.model.Response;
import com.h2.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/users/{id}")
    public ResponseEntity<Response> getUserById(@PathVariable("id") long id) {
        Response response = new Response();
        response.setStatus(HttpStatus.OK);
        response.setCode("200");
        response.setData(userService.getDetailUserById(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
//    @PostMapping("/users")
//    @PutMapping("/users/{id}")
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Response> deleteUserById(@PathVariable("id") long id) {
        return null;
    }

}
