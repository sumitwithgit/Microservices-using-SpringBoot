package com.micro.serivce.hotel.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Hotel {

	@Id
	private String id;
	private String name;
	private String location;
	private String about;
	
}
