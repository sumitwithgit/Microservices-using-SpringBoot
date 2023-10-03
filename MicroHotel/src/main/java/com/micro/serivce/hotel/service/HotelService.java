package com.micro.serivce.hotel.service;

import java.util.List;

import com.micro.serivce.hotel.entities.Hotel;
import com.micro.serivce.hotel.exception.HotelException;

public interface HotelService {

	Hotel saveHotel(Hotel hotel) throws HotelException;
	
	List<Hotel> getAllHotel() throws HotelException;
	
	Hotel gethotelById(String hotelId) throws HotelException;
	
	void deleteHotelById(String hotelId) throws HotelException;
	
	Hotel updateHotelById(String hotelId, Hotel hotel) throws HotelException;
}
