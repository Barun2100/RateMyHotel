package com.hotelratingapp.user.service.userservice.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hotelratingapp.user.service.userservice.entities.User;

@Service
public interface UserService {
    
    User saveUser(User user);

    List<User> getAllUsers();

    User getUserById(String userId);

    boolean deleteUserById(String userId);

    User updateUser(String userId, User user);
    
}
