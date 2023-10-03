package com.micro.serivce.rating.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.micro.serivce.rating.entities.Rating;

public interface RatingRepository extends JpaRepository<Rating, String>{

	List<Rating> findByUserId(String userId);
	
	List<Rating> findByHotelId(String hotelId);
}
