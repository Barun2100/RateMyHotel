package com.hotelratingapp.rating.service.RatingService.services.serviceImpl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelratingapp.rating.service.RatingService.entities.Rating;
import com.hotelratingapp.rating.service.RatingService.repository.RatingRepository;
import com.hotelratingapp.rating.service.RatingService.services.RatingService;

@Service
public class RatingServiceImpl implements RatingService{

    @Autowired
    private RatingRepository ratingRepo;

    @Override
    public Rating createRating(Rating rating) {
        // TODO Auto-generated method stub
    	String ratingId = UUID.randomUUID().toString();
        rating.setRatingId(ratingId);
        return ratingRepo.save(rating);
    }

    @Override
    public List<Rating> getAllRatings() {
        // TODO Auto-generated method stub
    	return ratingRepo.findAll();
    }

    @Override
    public List<Rating> getRatingByUserId(String userId) {
        // TODO Auto-generated method stub
    	return ratingRepo.findByUserId(userId);
        
    }

    @Override
    public List<Rating> getRatingByHotelId(String hotelId) {
        // TODO Auto-generated method stub
    	return ratingRepo.findByHotelId(hotelId);
    }

}
