package com.hotelratingapp.rating.service.RatingService.services;

import java.util.List;

import com.hotelratingapp.rating.service.RatingService.entities.Rating;
public interface RatingService {

    //create rating
    Rating createRating(Rating rating);

    //get all rating
    List<Rating> getAllRatings();

    //get rating by user id
    List<Rating> getRatingByUserId(String userId);

    //get rating by hotel id
    List<Rating> getRatingByHotelId(String hotelId);



} 
