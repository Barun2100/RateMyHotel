package com.hotelratingapp.user.service.userservice.services.impl;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hotelratingapp.user.service.userservice.entities.Hotel;
import com.hotelratingapp.user.service.userservice.entities.Rating;
import com.hotelratingapp.user.service.userservice.entities.User;
import com.hotelratingapp.user.service.userservice.exception.ResourceNotFoundException;
import com.hotelratingapp.user.service.userservice.external.services.HotelService;
import com.hotelratingapp.user.service.userservice.external.services.RatingService;
import com.hotelratingapp.user.service.userservice.repositories.UserRepo;
import com.hotelratingapp.user.service.userservice.services.UserService;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepo userRepo;
    
    @Autowired
    private RestTemplate restTemplate;
    
    @Autowired
    private HotelService hotelService;
    
    @Autowired
    private RatingService ratingService;
    
    private Logger logger = LoggerFactory.getLogger(UserService.class);

    @Override
    public User saveUser(User user) {
    	//it will generate unique user id everytime
    	String randomUserId = UUID.randomUUID().toString();
    	user.setUserId(randomUserId);
        return userRepo.save(user);
    }

    @Override
    public List<User> getAllUsers() {
    	
    	List<User> users = userRepo.findAll();
    	
    	logger.info("users "+ users);
    	
    	List<User>userList = users.stream().map(user->{
    		//list of all ratings given by the user
    		List<Rating> ratingList = ratingService.getRatingsByUserId(user.getUserId());
    		
    		//final rating list of all users.
    		List<Rating> ratingListOfUser = ratingList.stream().map(rating->{
    			
    			//getting the hotel info for a particular rating
    			Hotel hotel = hotelService.getHotel(rating.getHotelId());
    			
    			//setting the hotel details to the specific hotel
    			rating.setHotel(hotel);
    			return rating;
    		}).collect(Collectors.toList());
    		
    		//setting the rating
    		user.setRatings(ratingListOfUser);
    		return user;
    	}).collect(Collectors.toList());
    	
        return userList;
    }

    //get single user by id
    @Override
    public User getUserById(String userId) {
        // TODO fetch user form database from user repo
        User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User with given id is not found on server !! "+userId));
        
        //get above user rating from RATING SERVICE using restTemplate
        //    http://localhost:8083/ratings/byUserId/274cf5c7-4802-47b9-8988-6bfa291af151
        Rating[] ratingOfUser = restTemplate.getForObject("http://RATINGSERVICE/ratings/byUserId/"+user.getUserId(), Rating[].class);
        logger.info("{} ",ratingOfUser);
        List<Rating> ratings = Arrays.stream(ratingOfUser).toList();
        
        List<Rating> ratingList = ratings.stream().map(rating ->{
        	//get above RATING's hotel from HOTELSERVICE using restTemplate
        	ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTELSERVICE/hotels/"+rating.getHotelId(), Hotel.class);
        	Hotel hotel = forEntity.getBody();
        	rating.setHotel(hotel);
        	return rating;
        	
        }).collect(Collectors.toList());
        
        user.setRatings(ratingList);
        return user;
    }

    @Override
    public boolean deleteUserById(String userId) {
        try {
            getUserById(userId); // Check if user exists
            userRepo.deleteById(userId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public User updateUser(String userId, User updatedUser) {
        User existingUser = getUserById(userId);
        existingUser.setUserName(updatedUser.getUserName());
        existingUser.setUserEmail(updatedUser.getUserEmail());
        existingUser.setUserBio(updatedUser.getUserBio());
        return userRepo.save(existingUser); 
    }
}

