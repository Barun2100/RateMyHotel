package com.hotelratingapp.user.service.userservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotelratingapp.user.service.userservice.entities.User;

public interface UserRepo extends JpaRepository<User, String>{

    
} 