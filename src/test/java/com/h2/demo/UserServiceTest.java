package com.h2.demo;

import com.h2.demo.exception.InvalidRequestUserException;
import com.h2.demo.exception.UserNotFoundException;
import com.h2.demo.model.User;
import com.h2.demo.repository.UserRepository;
import com.h2.demo.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void getDetailUserById() {
        User user = new User();
        user.setId(1L);
        user.setName("christian");
        user.setDeleted(false);
        Optional<User> userOptional = Optional.of(user);
        Mockito.when(userRepository.findById(1L)).thenReturn(userOptional);

        User actualResult = userService.getDetailUserById(1L);
        Assert.assertEquals(actualResult, user);
    }

    @Test(expected = UserNotFoundException.class)
    public void getDetailUserByIdThrowNotFound() {
        User user = new User();
        user.setId(1L);
        user.setName("christian");
        user.setDeleted(true);
        Optional<User> userOptional = Optional.of(user);
        Mockito.when(userRepository.findById(1L)).thenReturn(userOptional);

        User actualResult = userService.getDetailUserById(1L);
        Assert.assertEquals(actualResult, user);
    }

    @Test(expected = UserNotFoundException.class)
    public void deleteUserByIdThrowNotFound() {
        User user = new User();
        user.setId(1L);
        user.setName("christian");
        user.setDeleted(true);
        Optional<User> userOptional = Optional.of(user);
        Mockito.when(userRepository.findById(1L)).thenReturn(userOptional);

        userService.deleteUserById(1L);
    }

    @Test
    public void deleteUserById() {
        User user = new User();
        user.setId(1L);
        user.setName("christian");
        user.setDeleted(false);
        Optional<User> userOptional = Optional.of(user);
        Mockito.when(userRepository.findById(1L)).thenReturn(userOptional);

        Object userObject = userService.deleteUserById(1L);
        User actual = (User) userObject;
        Assert.assertTrue(actual.isDeleted());
    }

    @Test(expected = InvalidRequestUserException.class)
    public void updateUserByIdThrowInvalid() {
        User user = new User();
        user.setId(1L);
        user.setName("c");
        user.setDeleted(false);
        Optional<User> userOptional = Optional.of(user);
        Mockito.when(userRepository.findById(1L)).thenReturn(userOptional);
        userService.updateUserById(user, 1L);
    }

    @Test(expected = UserNotFoundException.class)
    public void updateUserByIdThrowNotFound() {
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.empty());
        userService.updateUserById(new User(), 1L);
    }

}
