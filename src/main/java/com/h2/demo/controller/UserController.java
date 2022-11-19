package com.h2.demo.controller;

import com.h2.demo.model.Response;
import com.h2.demo.model.User;
import com.h2.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
        response.setCode(String.valueOf(HttpStatus.OK.value()));
        response.setData(userService.getDetailUserById(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping("/users")
    public ResponseEntity<Response> addNewUser(@Validated(User.class) @RequestBody User user) {
        Response response = new Response();
        response.setStatus(HttpStatus.OK);
        response.setCode(String.valueOf(HttpStatus.OK.value()));
        response.setData(userService.addNewUser(user));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<Response> updateUserById(@PathVariable("id") long id, @RequestBody User user) {
        Response response = new Response();
        response.setStatus(HttpStatus.OK);
        response.setCode(String.valueOf(HttpStatus.OK.value()));
        response.setData(userService.updateUserById(user, id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Response> deleteUserById(@PathVariable("id") long id) {
        Response response = new Response();
        response.setStatus(HttpStatus.NO_CONTENT);
        response.setCode(String.valueOf(HttpStatus.NO_CONTENT.value()));
        response.setData(userService.deleteUserById(id));
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }

}
