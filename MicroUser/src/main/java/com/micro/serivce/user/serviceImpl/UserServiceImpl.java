package com.micro.serivce.user.serviceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.micro.serivce.user.entities.Hotel;
import com.micro.serivce.user.entities.Rating;
import com.micro.serivce.user.entities.User;
import com.micro.serivce.user.exception.UserException;
import com.micro.serivce.user.externalService.HotelService;
import com.micro.serivce.user.externalService.RatingService;
import com.micro.serivce.user.repository.UserRepository;
import com.micro.serivce.user.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
//	@Autowired
//	private RestTemplate restTemplate;
	
	@Autowired
	private HotelService hotelService;
	
	@Autowired
	private RatingService ratingService;
	
	@Override
	public User createUser(User user) throws UserException {
		user.setId(UUID.randomUUID().toString());
		User saveUser = this.userRepository.save(user);
		if(saveUser==null) {
			throw new UserException("User Not Save, Something Went Wrong.");
		}
		return saveUser;
	}

	@Override
	public List<User> getAllUser() throws UserException {
		List<User> allUsers = this.userRepository.findAll();
		if(allUsers.isEmpty()) {
			throw new UserException("No User Found");
		}
		for (User user : allUsers) {
			List<Rating> ratings = this.ratingService.getRatingByUserId(user.getId());
			if(ratings.isEmpty()) {
				user.setRatings(new ArrayList<Rating>());
			}else {
				for (Rating rating : ratings) {
					Hotel hotel = this.hotelService.getHotelById(rating.getHotelId());
					rating.setHotel(hotel);
				}
				user.setRatings(ratings);
			}
		}
		return allUsers;
	}

	@Override
	public User getUserById(String userId) throws UserException{
		User user = this.userRepository.findById(userId).orElseThrow(()->new UserException("User Not Found With id : "+userId));
//		Rating[] ratings = this.restTemplate.getForObject("http://RATING-SERVICE/rating/users/"+user.getId(), Rating[].class);
		List<Rating> ratingList = this.ratingService.getRatingByUserId(userId);
		
//		List<Rating> ratingList = Arrays.stream(ratings).toList();
		for (Rating rating : ratingList) {
			/* this way is using restTemplate */
//				ResponseEntity<Hotel> entity = this.restTemplate.getForEntity("http://HOTEL-SERVICE/hotel/"+rating.getHotelId(), Hotel.class);
//				Hotel hotel = entity.getBody();
			/* this way is using Hotel Feign client */
				Hotel hotel = this.hotelService.getHotelById(rating.getHotelId());
			rating.setHotel(hotel);
		}
		user.setRatings(ratingList);
		return user;
	}

	@Override
	public void deleteUserById(String userId) throws UserException{
		User user = this.userRepository.findById(userId).orElseThrow(()->new UserException("User Not Found With id : "+userId));
		this.userRepository.delete(user);
	}

	@Override
	public User updateUserById(String userId, User updatedUser) throws UserException {
		User user = this.userRepository.findById(userId).orElseThrow(()->new UserException("User Not Found With id : "+userId));
		if(updatedUser.getName()!=null) {
			user.setName(updatedUser.getName());			
		}
		if(updatedUser.getEmail()!=null) {
			user.setEmail(updatedUser.getEmail());			
		}
		if(updatedUser.getAbout()!=null) {
			user.setAbout(updatedUser.getAbout());			
		}
		
		User updateUser= this.userRepository.save(user);
		return updateUser;
	}

}
