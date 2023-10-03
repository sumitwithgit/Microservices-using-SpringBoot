package com.micro.serivce.rating.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.micro.serivce.rating.entities.Rating;
import com.micro.serivce.rating.exception.RatingException;
import com.micro.serivce.rating.repository.RatingRepository;
import com.micro.serivce.rating.service.RatingService;

@Service
public class RatingServiceImpl implements RatingService{

	@Autowired
	private RatingRepository ratingRepository;
	
	@Override
	public Rating createRating(Rating rating) throws RatingException {
		rating.setRatingId(UUID.randomUUID().toString());
		Rating saveRating = this.ratingRepository.save(rating);
		if(saveRating==null) {
			throw new RatingException("Rating not saved, Something went Wrong.");
		}
		return saveRating;
	}

	@Override
	public Rating updateRatingById(Rating rating, String ratingId) throws RatingException {
		Rating rating2 = this.getRatingById(ratingId);
		if(rating.getFeedback()!=null) {
			rating2.setFeedback(rating.getFeedback());			
		}
		if(rating.getRating()!=null) {
			rating2.setRating(rating.getRating());			
		}
		Rating updateRating = this.ratingRepository.save(rating2);
		return updateRating;
	}

	@Override
	public Rating getRatingById(String ratingId) throws RatingException {
		Rating rating = this.ratingRepository.findById(ratingId).orElseThrow(()-> new RatingException("Rating not found With Id : "+ratingId));
		return rating;
	}

	@Override
	public List<Rating> getRatingByUserId(String userId) throws RatingException {
		List<Rating> rating = new ArrayList<Rating>();
		rating=this.ratingRepository.findByUserId(userId);
		return rating;
	}

	@Override
	public List<Rating> getRatingByHotelId(String hotelId) throws RatingException {
		List<Rating> rating = this.ratingRepository.findByHotelId(hotelId);
		if(rating.isEmpty()) {
			throw new RatingException("No Rating Found");
		}
		return rating;
	}

	@Override
	public List<Rating> getAllRating() throws RatingException {
		List<Rating> allRating = this.ratingRepository.findAll();
		if(allRating.isEmpty()) {
			throw new RatingException("No Rating Found.");
		}
		return allRating;
	}

	@Override
	public void deleteRating(String ratingId) throws RatingException {
		Rating rating = this.getRatingById(ratingId);
		this.ratingRepository.delete(rating);
	}

}
