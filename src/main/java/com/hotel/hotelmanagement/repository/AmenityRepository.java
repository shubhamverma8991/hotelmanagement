package com.hotel.hotelmanagement.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.hotel.hotelmanagement.model.Amenity;

public interface AmenityRepository extends MongoRepository<Amenity, String> {

    @Query(value = "{}", fields = "{ 'name' : 1 }")
    List<String> findAllAmenities();

    List<Amenity> findAll();
}
