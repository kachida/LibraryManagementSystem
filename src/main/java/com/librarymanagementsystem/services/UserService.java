package com.librarymanagementsystem.services;

import com.librarymanagementsystem.models.User;
import com.librarymanagementsystem.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User createUser(User user) {
        if(user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
       return userRepository.save(user);
    }

    public User getUser(BigInteger userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if(!userOptional.isPresent()) {
            return null;
        }
        return userOptional.get();
    }



}
