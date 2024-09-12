package com.hotel.hotelmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.hotelmanagement.model.Amenity;
import com.hotel.hotelmanagement.model.Hotel;
import com.hotel.hotelmanagement.model.RoomType;
import com.hotel.hotelmanagement.repository.AmenityRepository;
import com.hotel.hotelmanagement.repository.HotelRepository;
import com.hotel.hotelmanagement.repository.RoomTypeRepository;

@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private AmenityRepository amenitiesRepository;

    @Autowired
    private RoomTypeRepository roomTypeRepository;

    public Hotel saveHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    public List<Hotel> saveHotels(List<Hotel> hotels) {
        return hotelRepository.saveAll(hotels);
    }

    public Hotel updateHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    public void deleteHotel(String id) {
        hotelRepository.deleteById(id);
    }

    public void deleteHotels(List<String> ids) {
        hotelRepository.deleteAllById(ids);
    }

    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    public List<Hotel> getHotelsByName(String name) {
        return hotelRepository.findByNameContainingIgnoreCase(name);
    }

    public Hotel getHotelById(String id) {
        return hotelRepository.findById(id).orElse(null);
    }

    public List<String> getAllAmenities() {
        return amenitiesRepository.findAllAmenities();
    }

    public List<String> getAllRoomTypes() {
        return roomTypeRepository.findAllRoomTypes();
    }

    public List<Amenity> getAllAmenitiesList() {
        return amenitiesRepository.findAll();
    }

    public List<RoomType> getAllRoomTypesList() {
        return roomTypeRepository.findAll();
    }
}
