package com.micro.serivce.rating.service;

import java.util.List;

import com.micro.serivce.rating.entities.Rating;
import com.micro.serivce.rating.exception.RatingException;

public interface RatingService {

	Rating createRating(Rating rating) throws RatingException;
	
	Rating updateRatingById(Rating rating, String ratingId) throws RatingException;
	
	Rating getRatingById(String ratingId) throws RatingException;
	
	List<Rating> getRatingByUserId(String userId) throws RatingException;
	
	List<Rating> getRatingByHotelId(String hotelId) throws RatingException;
	
	List<Rating> getAllRating() throws RatingException;
	
	void deleteRating(String ratingId) throws RatingException;
}
