package com.micro.serivce.hotel.controller;

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

import com.micro.serivce.hotel.entities.Hotel;
import com.micro.serivce.hotel.exception.HotelException;
import com.micro.serivce.hotel.response.ApiResponse;
import com.micro.serivce.hotel.service.HotelService;

@RestController
@RequestMapping("/hotel")
public class HotelController {

	@Autowired
	private HotelService hotelService;
	
	@PostMapping("/")
	@PreAuthorize("hasAuthority('Admin')")
	public ResponseEntity<Hotel> savehotel(@RequestBody Hotel hotel) throws HotelException{
		Hotel saveHotel = this.hotelService.saveHotel(hotel);
		return new ResponseEntity<Hotel>(saveHotel,HttpStatus.CREATED);
	}
	
	@PutMapping("/{hotelId}")
	public ResponseEntity<Hotel> updateHotelById(@PathVariable("hotelId") String hotelId, @RequestBody Hotel hotel) throws HotelException{
		Hotel updateHotelById = this.hotelService.updateHotelById(hotelId, hotel);
		return new ResponseEntity<Hotel>(updateHotelById, HttpStatus.OK);
	}
	
	@GetMapping("/{hotelId}")
	@PreAuthorize("hasAuthority('SCOPE_INTERNAL') || hasAuthority('Admin') || hasAuthority('Normal')")
	public ResponseEntity<Hotel> getHotelById(@PathVariable("hotelId") String hotelId) throws HotelException{
		Hotel hotel = this.hotelService.gethotelById(hotelId);
		return new ResponseEntity<Hotel>(hotel,HttpStatus.OK);
	}
	
	@GetMapping("/")
	@PreAuthorize("hasAuthority('SCOPE_INTERNAL') || hasAuthority('Admin')")
	public ResponseEntity<List<Hotel>> getAllhotel() throws HotelException{
		List<Hotel> allHotel = this.hotelService.getAllHotel();
		return new ResponseEntity<List<Hotel>>(allHotel,HttpStatus.OK);
	}
	
	@DeleteMapping("/{hotelId}")
	public ResponseEntity<ApiResponse> deleteHotelById(@PathVariable("hotelId") String hotelId) throws HotelException{
		this.hotelService.deleteHotelById(hotelId);
		ApiResponse res=new ApiResponse();
		res.setMessage("Hotel Deleted Successfully : "+hotelId);
		res.setStatusCode("204");
		return new ResponseEntity<ApiResponse>(res,HttpStatus.OK);
	}
}
