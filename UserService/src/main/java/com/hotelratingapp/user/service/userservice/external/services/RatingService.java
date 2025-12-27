package com.hotelratingapp.user.service.userservice.external.services;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.hotelratingapp.user.service.userservice.entities.Rating;

@FeignClient(name="RatingService")
public interface RatingService {
	
	//get
	@GetMapping("/ratings/")
	public List<Rating> getRatings();	
	//post
	@PostMapping("/ratings/create")
	public Rating cretaeRating(Rating ratings);
	
	//put
	@PutMapping("/ratings/{ratingId}")
	public Rating updateRating(@PathVariable String ratingId, @RequestBody Rating rating);
	
	//delete
	@DeleteMapping("/ratings/{ratingId}")
	public void deleteRating(@PathVariable String ratingId);
	
	//get all ratings by user id
	@GetMapping("/ratings/byUserId/{userId}")
	public List<Rating> getRatingsByUserId(@PathVariable String userId);
	
	//get all rating by hotel id
	@GetMapping("/ratings/byHotelId/{hotelId}")
	public List<Rating> getRatingsByHotelId(@PathVariable String hotelId);

}
