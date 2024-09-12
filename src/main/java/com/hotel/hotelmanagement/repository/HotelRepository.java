package com.hotel.hotelmanagement.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.hotel.hotelmanagement.model.Hotel;

public interface HotelRepository extends MongoRepository<Hotel, String> {

    List<Hotel> findByNameContainingIgnoreCase(String name);
}
