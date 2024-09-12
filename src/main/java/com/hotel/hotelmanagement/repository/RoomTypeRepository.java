package com.hotel.hotelmanagement.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.hotel.hotelmanagement.model.RoomType;

public interface RoomTypeRepository extends MongoRepository<RoomType, String> {

    @Query(value = "{}", fields = "{ 'type' : 1 }")
    List<String> findAllRoomTypes();
}
