package com.h2.demo.service;

import com.h2.demo.exception.UserNotFoundException;
import com.h2.demo.model.User;
import com.h2.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User getDetailUserById(long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        if (user.isDeleted()) {
            throw new UserNotFoundException(id);
        }
        return user;
    }

    public Object deleteUserById(long id) {
        Object object = new Object();
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        if(user.isDeleted()) {
            throw new UserNotFoundException(id);
        } else {
            user.setDeleted(true);
            userRepository.saveAndFlush(user);
        }
        return object;
    }

}
