package com.micro.serivce.rating.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.micro.serivce.rating.entities.Rating;
import com.micro.serivce.rating.exception.RatingException;
import com.micro.serivce.rating.response.ApiResponse;
import com.micro.serivce.rating.service.RatingService;

@RestController
@RequestMapping("/rating")
public class RatingController {

	@Autowired
	private RatingService ratingService;
	
	@PostMapping("/")
	@PreAuthorize("hasAuthority('Admin')")
	public ResponseEntity<Rating> saveRating(@RequestBody Rating rating) throws RatingException{
		Rating createRating = this.ratingService.createRating(rating);
		return new ResponseEntity<Rating>(createRating, HttpStatus.CREATED);
	}
	
	@PutMapping("/{ratingId}")
	public ResponseEntity<Rating> updateRatingById(@RequestBody Rating rating,@PathVariable("ratingId") String ratingId) throws RatingException{
		Rating updateRating = this.ratingService.updateRatingById(rating, ratingId);
		return new ResponseEntity<Rating>(updateRating, HttpStatus.OK);
	}
	
	@GetMapping("/{ratingId}")
	public ResponseEntity<Rating> getRatingById(@PathVariable("ratingId") String ratingId) throws RatingException{
		Rating rating = this.ratingService.getRatingById(ratingId);
		return new ResponseEntity<Rating>(rating, HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Rating>> getAllRating() throws RatingException{
		List<Rating> allRating = this.ratingService.getAllRating();
		return new ResponseEntity<List<Rating>>(allRating,HttpStatus.OK);
	}
	
	@GetMapping("/users/{userId}")
	@PreAuthorize("hasAuthority('SCOPE_INTERNAL') || hasAuthority('Admin')")
	public ResponseEntity<List<Rating>> getRatingByUserId(@PathVariable("userId") String userId) throws RatingException{
		List<Rating> allRating = this.ratingService.getRatingByUserId(userId);
		return new ResponseEntity<List<Rating>>(allRating, HttpStatus.OK);
	}
	
	@GetMapping("/hotels/{hotelId}")
	@PreAuthorize("hasAuthority('SCOPE_INTERNAL') || hasAuthority('Admin')")
	public ResponseEntity<List<Rating>> getRatingByHotelId(@PathVariable("hotelId") String hotelId) throws RatingException{
		List<Rating> allRating = this.ratingService.getRatingByHotelId(hotelId);
		return new ResponseEntity<List<Rating>>(allRating, HttpStatus.OK);
	}
	
	@DeleteMapping("/{ratingId}")
	public ResponseEntity<ApiResponse> deleteRatingById(@PathVariable("ratingId") String ratingId) throws RatingException{
		this.ratingService.deleteRating(ratingId);
		ApiResponse res=new ApiResponse();
		res.setMessage("Rating deleted Successfully.");
		res.setStatusCode("204");
		return new ResponseEntity<ApiResponse>(res, HttpStatus.OK);
	}
}
