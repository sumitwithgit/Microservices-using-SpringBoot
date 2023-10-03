package com.micro.serivce.user.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import lombok.Data;

@Data
@Entity
public class User {
	
	@Id
	private String id;
	private String name;
	private String email;
	private String about;
	
	@Transient
	List<Rating> ratings=new ArrayList<Rating>();
}
