package com.edufind.service;

import com.edufind.model.User;
import com.edufind.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public String registerUser(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return "Email already exists";
        }

        if (!user.getPassword().equals(user.getConfirmPassword())) {
            return "Passwords do not match";
        }

        userRepository.save(user);
        return "User registered successfully";
    }

    public String loginUser(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            return "Login successful";
        }
        return "Invalid credentials";
    }
}
