package com.hotelratingapp.rating.service.RatingService.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotelratingapp.rating.service.RatingService.entities.Rating;

public interface RatingRepository extends JpaRepository<Rating, String>{

	//custom finder method
	
	List<Rating> findByUserId(String userId);
	
	List<Rating> findByHotelId(String hotelId);
	
	
}
