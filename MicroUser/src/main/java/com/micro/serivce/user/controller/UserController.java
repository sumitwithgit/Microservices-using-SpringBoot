package com.micro.serivce.user.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.micro.serivce.user.entities.User;
import com.micro.serivce.user.exception.UserException;
import com.micro.serivce.user.response.ApiResponse;
import com.micro.serivce.user.service.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/user")
public class UserController {
	
	private Logger logger=LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/")
	public ResponseEntity<User> saveUser(@RequestBody User user) throws UserException{
		User createUser = this.userService.createUser(user);
		return new ResponseEntity<User>(createUser,HttpStatus.CREATED);
	}
//	int retryCount=1;
	
	@GetMapping("/{userId}")
	@CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
//	@Retry(name = "ratingHotelService", fallbackMethod = "ratingHotelFallback")
	@RateLimiter(name = "userRateLimiter", fallbackMethod = "ratingHotelFallback")
	public ResponseEntity<User> getUserById(@PathVariable("userId") String userId) throws UserException{
//		logger.info("Retry Count {}",retryCount);
//		retryCount++;
		User user = this.userService.getUserById(userId);
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	public ResponseEntity<User> ratingHotelFallback(String userId, Exception ex){
		logger.info("Fallback is executed beacuse service is down.");
		User user=new User();
		user.setName("dummy");
		user.setAbout("dummy text");
		user.setEmail("dummy@gmail.com");
		user.setId("dummy1");
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	
	@GetMapping("/")
	@CircuitBreaker(name = "ratingHotelBreakerForAllUsers", fallbackMethod = "ratingHotelFallbackForAllUsers")
	public ResponseEntity<List<User>> getAllUsers() throws UserException{
		List<User> users = this.userService.getAllUser();
		return new ResponseEntity<List<User>>(users,HttpStatus.OK);
	}
	
	public ResponseEntity<User> ratingHotelFallbackForAllUsers(Exception ex){
		System.out.println("Fallback is executed beacuse service is down.");
		User user=new User();
		user.setName("dummy");
		user.setAbout("dummy text");
		user.setEmail("dummy@gmail.com");
		user.setId("dummy1");
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteUserById(@PathVariable("userId") String userId) throws UserException{
		this.userService.deleteUserById(userId);
		ApiResponse res=new ApiResponse();
		res.setMessage("User Deleted Successfully With UserId : "+userId);
		res.setStatusCode("204");
		return new ResponseEntity<ApiResponse>(res,HttpStatus.OK);
	}
	
	@PutMapping("/{userId}")
	public ResponseEntity<User> updateUserByUserId(@PathVariable("userId") String userId, @RequestBody User user) throws UserException{
		User user2 = this.userService.updateUserById(userId, user);
		return new ResponseEntity<User>(user2, HttpStatus.OK);
	}
}
