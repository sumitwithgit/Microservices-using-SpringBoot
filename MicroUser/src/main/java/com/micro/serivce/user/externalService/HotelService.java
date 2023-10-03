package com.micro.serivce.user.externalService;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.micro.serivce.user.entities.Hotel;

@Service
@FeignClient(name = "HOTEL-SERVICE")
public interface HotelService {

	@GetMapping("/hotel/{hotelId}")
	Hotel getHotelById(@PathVariable("hotelId") String hotelId);
	
	@PostMapping("/hotel/")
	Hotel saveHotel(Hotel hotel);
	
	@GetMapping("/hotel/")
	List<Hotel> getAllHotel();
	
	@DeleteMapping("/hotel/{hotelId}")
	void deleteHotelById(@PathVariable("hotelId") String hotelId);
	
	@PutMapping("/hotel/{hotelId}")
	Hotel updateHotelById(@PathVariable("hotelId") String hotelId, Hotel hotel);
}
