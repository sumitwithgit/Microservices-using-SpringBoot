package com.micro.serivce.rating.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Rating {

	@Id
	private String ratingId;
	private String userId;
	private String hotelId;
	private String rating;
	private String feedback;
}
