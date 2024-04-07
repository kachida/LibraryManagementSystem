package com.librarymanagementsystem.services;

import com.librarymanagementsystem.models.User;
import com.librarymanagementsystem.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigInteger;
import java.util.Optional;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    private static User user;

    private static BigInteger userId;

    @BeforeAll
    public static void setUp() {
        userId = BigInteger.valueOf(123);
        user = new User.Builder().userId(userId).email("test@test.com").name("kanna").build();
    }

    @Test
    public void createUser() {
        when(userRepository.save(user)).thenReturn(user);
        User newUser = userService.createUser(user);
        Assertions.assertNotNull(newUser);
        Assertions.assertEquals(user, newUser);
        Mockito.verify(userRepository, times(1)).save(user);
    }

    @Test
    public void searchUser_WhenExists() {
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        User existingUser = userService.getUser(userId);
        Assertions.assertNotNull(existingUser);
        Assertions.assertEquals(user, existingUser);
        Mockito.verify(userRepository, times(1)).findById(userId);
    }

    @Test
    public void searchUser_WhenNotExists() {
        when(userRepository.findById(userId)).thenReturn(Optional.empty());
        User existingUser = userService.getUser(userId);
        Assertions.assertNull(existingUser);
        Mockito.verify(userRepository, times(1)).findById(userId);
    }
}
