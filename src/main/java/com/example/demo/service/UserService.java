package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        String salt = SecurityUtil.generateSalt();
        String encryptedPassword = SecurityUtil.encryptPBKDF2(user.getPassword(), salt);
        user.setPassword(encryptedPassword);
        user.setSalt(salt);
        userRepository.insertUser(user);
        return user;
    }

    public List<User> getAllUsers() {
        return userRepository.selectAllUsers();
    }

    public User getUserById(Long id) {
        return userRepository.selectUserById(id);
    }

    public User updateUser(Long id, User userDetails) {
        User user = userRepository.selectUserById(id);
        if (user != null) {
            user.setUsername(userDetails.getUsername());
            user.setEmail(userDetails.getEmail());
            if (userDetails.getPassword() != null) {
                String salt = SecurityUtil.generateSalt();
                String encryptedPassword = SecurityUtil.encryptPBKDF2(userDetails.getPassword(), salt);
                user.setPassword(encryptedPassword);
                user.setSalt(salt);
            }
            userRepository.updateUser(user);
        }
        return user;
    }

    public void deleteUser(Long id) {
        userRepository.deleteUser(id);
    }
}