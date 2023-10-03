package com.micro.serivce.user.externalService;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.micro.serivce.user.entities.Rating;

@Service
@FeignClient(name = "RATING-SERVICE")
public interface RatingService {

	@PostMapping("/rating/")
	Rating createRating(Rating rating);
	
	@PutMapping("/rating/{ratingId}")
	Rating updateRatingById(Rating rating,@PathVariable("ratingId") String ratingId);
	
	@GetMapping("/rating/{ratingId}")
	Rating getRatingById(@PathVariable("ratingId") String ratingId);
	
	@GetMapping("/rating/users/{userId}")
	List<Rating> getRatingByUserId(@PathVariable("userId") String userId);
	
	@GetMapping("/rating/hotels/{hotelId}")
	List<Rating> getRatingByHotelId(@PathVariable("hotelId") String hotelId);
	
	@GetMapping("/rating/")
	List<Rating> getAllRating();
	
	@DeleteMapping("/rating/{ratingId}")
	void deleteRating(@PathVariable("ratingId") String ratingId);
}
