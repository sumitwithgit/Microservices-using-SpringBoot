package com.micro.serivce.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.micro.serivce.hotel.entities.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, String>{

}
