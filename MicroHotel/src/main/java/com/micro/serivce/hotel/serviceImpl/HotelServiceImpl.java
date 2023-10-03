package com.micro.serivce.hotel.serviceImpl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.micro.serivce.hotel.entities.Hotel;
import com.micro.serivce.hotel.exception.HotelException;
import com.micro.serivce.hotel.repository.HotelRepository;
import com.micro.serivce.hotel.service.HotelService;

@Service
public class HotelServiceImpl implements HotelService{

	@Autowired
	private HotelRepository hotelRepository;
	
	@Override
	public Hotel saveHotel(Hotel hotel) throws HotelException {
		hotel.setId(UUID.randomUUID().toString());
		Hotel saveHotel = this.hotelRepository.save(hotel);
		if(saveHotel==null) {
			throw new HotelException("Hotel Not Saved, Something went wrong.");
		}
		return saveHotel;
	}

	@Override
	public List<Hotel> getAllHotel() throws HotelException {
		List<Hotel> allHotels = this.hotelRepository.findAll();
		if(allHotels.isEmpty()) {
			throw new HotelException("No hotel Found");
		}
		return allHotels;
	}

	@Override
	public Hotel gethotelById(String hotelId) throws HotelException {
		Hotel hotel = this.hotelRepository.findById(hotelId).orElseThrow(()-> new HotelException("Hotel Not Found With id :  "+hotelId));
		return hotel;
	}

	@Override
	public void deleteHotelById(String hotelId) throws HotelException {
		Hotel hotel = this.hotelRepository.findById(hotelId).orElseThrow(()-> new HotelException("Hotel Not Found With id :  "+hotelId));
		this.hotelRepository.delete(hotel);
	}

	@Override
	public Hotel updateHotelById(String hotelId, Hotel updatedHotel) throws HotelException {
		Hotel hotel = this.hotelRepository.findById(hotelId).orElseThrow(()-> new HotelException("Hotel Not Found With id :  "+hotelId));
		if(updatedHotel.getName()!=null) {
			hotel.setName(updatedHotel.getName());
		}
		if(updatedHotel.getAbout()!=null) {
			hotel.setAbout(updatedHotel.getAbout());			
		}
		if(updatedHotel.getLocation()!=null) {
			hotel.setLocation(updatedHotel.getLocation());			
		}
		Hotel saveupdatedHotel = this.hotelRepository.save(hotel);
		return saveupdatedHotel;
	}

}
