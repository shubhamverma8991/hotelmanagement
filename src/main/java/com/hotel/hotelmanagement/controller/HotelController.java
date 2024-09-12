package com.hotel.hotelmanagement.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.hotelmanagement.model.Amenity;
import com.hotel.hotelmanagement.model.Hotel;
import com.hotel.hotelmanagement.model.RoomType;
import com.hotel.hotelmanagement.service.HotelService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    // Get All
    @GetMapping
    public ResponseEntity<?> getAllHotels() {
        List<Hotel> hotels = hotelService.getAllHotels();
        if (hotels.isEmpty()) {
            return ResponseEntity.ok(Map.of("message", "No hotels found."));
        }
        return ResponseEntity.ok(hotels);
    }

    // Get by Name
    @GetMapping("/search/{name}")
    public ResponseEntity<?> getHotelsByName(@PathVariable String name) {
        List<Hotel> hotels = hotelService.getHotelsByName(name);
        if (hotels.isEmpty()) {
            return ResponseEntity.ok(Map.of("message", "No hotels found"));
        }
        return ResponseEntity.ok(Map.of("message", hotels.size() + " hotel(s) found", "data", hotels));
    }

    // Get By ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getHotelById(@PathVariable String id) {
        Hotel hotel = hotelService.getHotelById(id);
        if (hotel == null) {
            return ResponseEntity.ok(Map.of("message", "Hotel not found"));
        }
        return ResponseEntity.ok(Map.of("message", "Hotel details found", "data", hotel));
    }

    // Add Single Hotel
    @PostMapping("/add")
    public ResponseEntity<Hotel> addHotel(@RequestBody Hotel hotel) {
        Hotel savedHotel = hotelService.saveHotel(hotel);
        return ResponseEntity.ok(savedHotel);
    }

    // Add multiple Hotel
    @PostMapping("/bulk")
    public ResponseEntity<List<Hotel>> addHotels(@RequestBody List<Hotel> hotels) {
        List<Hotel> savedHotels = hotelService.saveHotels(hotels);
        return ResponseEntity.ok(savedHotels);
    }

    // Get All Amenities
    @GetMapping("/amenities/all")
    public ResponseEntity<?> getAllAmenitiesList() {
        List<Amenity> amenities = hotelService.getAllAmenitiesList();
        if (amenities.isEmpty()) {
            return ResponseEntity.ok(Map.of("message", "No amenities found."));
        }
        return ResponseEntity.ok(amenities);
    }

    // Get All Room Types
    @GetMapping("/roomtypes/all")
    public ResponseEntity<?> getAllRoomTypesList() {
        List<RoomType> roomTypes = hotelService.getAllRoomTypesList();
        if (roomTypes.isEmpty()) {
            return ResponseEntity.ok(Map.of("message", "No room types found."));
        }
        return ResponseEntity.ok(roomTypes);
    }

    // Update Hotel Details
    @PutMapping("/update")
    public ResponseEntity<Hotel> updateHotel(@RequestBody Hotel hotel) {
        Hotel existingHotel = hotelService.getHotelById(hotel.getId());
        if (existingHotel == null) {
            return ResponseEntity.notFound().build();
        }
        Hotel updatedHotel = hotelService.updateHotel(hotel);
        return ResponseEntity.ok(updatedHotel);
    }

    // Delete
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Map<String, String>> deleteHotel(@PathVariable String id) {
        hotelService.deleteHotel(id);
        return ResponseEntity.ok(Map.of("message", "Successfully Deleted."));
    }

    // Delete Bulk
    @DeleteMapping("delete/bulk")
    public ResponseEntity<Map<String, String>> deleteHotels(@RequestBody List<String> ids) {
        hotelService.deleteHotels(ids);
        return ResponseEntity.ok(Map.of("message", "Successfully Bulk Deleted."));
    }

}
