package com.h2.demo.service;

import com.h2.demo.exception.ConflictSocialSecurityNumberException;
import com.h2.demo.exception.InvalidRequestUserException;
import com.h2.demo.exception.UserNotFoundException;
import com.h2.demo.model.User;
import com.h2.demo.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addNewUser(User targetUser) {
        if (!isValidName(targetUser)) {
            throw new InvalidRequestUserException("name", targetUser.getName());
        }

        modifyValidSocialSecurityNumber(targetUser);

        User newUser = buildNewUser(targetUser);
        return userRepository.saveAndFlush(newUser);
    }

    private static User buildNewUser(User targetUser) {
        User newUser = new User();
        newUser.setName(targetUser.getName());
        isValidDateOfBirth(targetUser, newUser);

        newUser.setCreatedBy(targetUser.getCreatedBy());
        newUser.setUpdatedBy(targetUser.getUpdatedBy());
        newUser.setSocialSecurityNumber(targetUser.getSocialSecurityNumber());
        return newUser;
    }

    private void modifyValidSocialSecurityNumber(User targetUser) {
        if(!StringUtils.isNumericSpace(targetUser.getSocialSecurityNumber())) {
            throw new InvalidRequestUserException("socialSecurityNumber", targetUser.getSocialSecurityNumber());
        } else {
            if(targetUser.getSocialSecurityNumber().length() < 5) {
                String updatedNumber = String.format("%05d", Integer.valueOf(targetUser.getSocialSecurityNumber()));
                targetUser.setSocialSecurityNumber(updatedNumber);
            }
        }
        Optional<User> existUser = userRepository.findBySocialSecurityNumber(targetUser.getSocialSecurityNumber());
        if (existUser.isPresent()) {
            throw new ConflictSocialSecurityNumberException(targetUser.getSocialSecurityNumber());
        }
    }

    public User getDetailUserById(long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        if (user.isDeleted()) {
            throw new UserNotFoundException(id);
        }
        return user;
    }

    public Object deleteUserById(long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        if (user.isDeleted()) {
            throw new UserNotFoundException(id);
        } else {
            user.setDeleted(true);
            userRepository.saveAndFlush(user);
        }
        return user;
    }

    public User updateUserById(User targetUser, long targetUserId) {
        User user = userRepository.findById(targetUserId)
                .orElseThrow(() -> new UserNotFoundException(targetUserId));
        if (isValidName(targetUser)) {
            user.setName(targetUser.getName());
        } else {
            throw new InvalidRequestUserException("name", targetUser.getName());
        }

        isValidDateOfBirth(targetUser, user);
        user.setUpdatedDate(new Date().getTime());
        userRepository.saveAndFlush(user);
        return user;
    }

    private static void isValidDateOfBirth(User targetUser, User user) {
        try {
            SimpleDateFormat simpleDateFormatter = new SimpleDateFormat("yyyy-MM-dd");
            Date targetUserDateOfBirth = simpleDateFormatter.parse(targetUser.getDateOfBirth());
            user.setDateOfBirth(simpleDateFormatter.format(targetUserDateOfBirth));
        } catch (Exception e) {
            throw new InvalidRequestUserException("dateOfBirth", targetUser.getDateOfBirth());
        }
    }

    private static boolean isValidName(User targetUser) {
        return (targetUser.getName() != null && targetUser.getName().matches("^[a-zA-Z0-9]*$")) &&
                (targetUser.getName().length() > 2 && targetUser.getName().length() < 50);
    }

}
