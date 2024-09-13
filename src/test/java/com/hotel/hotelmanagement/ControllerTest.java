package com.hotel.hotelmanagement;

import com.hotel.hotelmanagement.controller.HotelController;
import com.hotel.hotelmanagement.model.Amenity;
import com.hotel.hotelmanagement.model.Hotel;
import com.hotel.hotelmanagement.model.RoomType;
import com.hotel.hotelmanagement.service.HotelService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

class ControllerTest {

    @Mock
    private HotelService hotelService;

    @InjectMocks
    private HotelController hotelController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllHotels_Positive() {
        List<Hotel> hotels = Arrays.asList(new Hotel("1", "Hotel A"), new Hotel("2", "Hotel B"));
        when(hotelService.getAllHotels()).thenReturn(hotels);

        ResponseEntity<?> response = hotelController.getAllHotels();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(hotels, response.getBody());
       System.out.println("response"+response.getBody());

        verify(hotelService, times(1)).getAllHotels();
    }

    @Test
    void testGetAllHotels_Negative_NoHotelsFound() {
        when(hotelService.getAllHotels()).thenReturn(Collections.emptyList());

        ResponseEntity<?> response = hotelController.getAllHotels();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(Map.of("message", "No hotels found."), response.getBody());

        verify(hotelService, times(1)).getAllHotels();
    }

    @Test
    void testGetHotelsByName_Positive() {
        List<Hotel> hotels = Collections.singletonList(new Hotel("1", "Hotel A"));
        when(hotelService.getHotelsByName("Hotel A")).thenReturn(hotels);

        ResponseEntity<?> response = hotelController.getHotelsByName("Hotel A");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(Map.of("message", "1 hotel(s) found", "data", hotels), response.getBody());

        verify(hotelService, times(1)).getHotelsByName("Hotel A");
    }

    @Test
    void testGetHotelsByName_Negative_NoHotelsFound() {
        when(hotelService.getHotelsByName("Unknown")).thenReturn(Collections.emptyList());

        ResponseEntity<?> response = hotelController.getHotelsByName("Unknown");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(Map.of("message", "No hotels found"), response.getBody());

        verify(hotelService, times(1)).getHotelsByName("Unknown");
    }

    @Test
    void testGetHotelById_Positive() {
        Hotel hotel = new Hotel("1", "Hotel A");
        when(hotelService.getHotelById("1")).thenReturn(hotel);

        ResponseEntity<?> response = hotelController.getHotelById("1");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(Map.of("message", "Hotel details found", "data", hotel), response.getBody());

        verify(hotelService, times(1)).getHotelById("1");
    }

    @Test
    void testGetHotelById_Negative_HotelNotFound() {
        when(hotelService.getHotelById("99")).thenReturn(null);

        ResponseEntity<?> response = hotelController.getHotelById("99");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(Map.of("message", "Hotel not found"), response.getBody());

        verify(hotelService, times(1)).getHotelById("99");
    }

    @Test
    void testAddHotel_Positive() {
        Hotel hotel = new Hotel("1", "Hotel A");
        when(hotelService.saveHotel(any(Hotel.class))).thenReturn(hotel);

        ResponseEntity<Hotel> response = hotelController.addHotel(new Hotel("1", "Hotel A"));

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(hotel, response.getBody());

        verify(hotelService, times(1)).saveHotel(any(Hotel.class));
    }

    @Test
    void testAddHotels_Positive() {
        List<Hotel> hotels = Arrays.asList(new Hotel("1", "Hotel A"), new Hotel("2", "Hotel B"));
        when(hotelService.saveHotels(anyList())).thenReturn(hotels);

        ResponseEntity<List<Hotel>> response = hotelController.addHotels(hotels);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(hotels, response.getBody());

        verify(hotelService, times(1)).saveHotels(anyList());
    }

    @Test
    void testGetAllAmenitiesList_Positive() {
        List<Amenity> amenities = Collections.singletonList(new Amenity("1", "WiFi"));
        when(hotelService.getAllAmenitiesList()).thenReturn(amenities);

        ResponseEntity<?> response = hotelController.getAllAmenitiesList();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(amenities, response.getBody());

        verify(hotelService, times(1)).getAllAmenitiesList();
    }

    @Test
    void testGetAllRoomTypesList_Positive() {
        List<RoomType> roomTypes = Collections.singletonList(new RoomType("1", "Deluxe"));
        when(hotelService.getAllRoomTypesList()).thenReturn(roomTypes);

        ResponseEntity<?> response = hotelController.getAllRoomTypesList();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(roomTypes, response.getBody());

        verify(hotelService, times(1)).getAllRoomTypesList();
    }

    @Test
    void testUpdateHotel_Positive() {
        Hotel hotel = new Hotel("1", "Hotel A");
        when(hotelService.getHotelById("1")).thenReturn(hotel);
        when(hotelService.updateHotel(any(Hotel.class))).thenReturn(hotel);

        ResponseEntity<Hotel> response = hotelController.updateHotel(new Hotel("1", "Hotel A"));

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(hotel, response.getBody());

        verify(hotelService, times(1)).getHotelById("1");
        verify(hotelService, times(1)).updateHotel(any(Hotel.class));
    }

    @Test
    void testUpdateHotel_Negative_HotelNotFound() {
        when(hotelService.getHotelById("99")).thenReturn(null);

        ResponseEntity<Hotel> response = hotelController.updateHotel(new Hotel("99", "Hotel Z"));

        assertEquals(404, response.getStatusCodeValue());

        verify(hotelService, times(1)).getHotelById("99");
        verify(hotelService, times(0)).updateHotel(any(Hotel.class));
    }

    @Test
    void testDeleteHotel_Positive() {
        doNothing().when(hotelService).deleteHotel("1");

        ResponseEntity<Map<String, String>> response = hotelController.deleteHotel("1");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(Map.of("message", "Successfully Deleted."), response.getBody());

        verify(hotelService, times(1)).deleteHotel("1");
    }

    @Test
    void testDeleteHotels_Positive() {
        doNothing().when(hotelService).deleteHotels(anyList());

        ResponseEntity<Map<String, String>> response = hotelController.deleteHotels(Arrays.asList("1", "2"));

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(Map.of("message", "Successfully Bulk Deleted."), response.getBody());

        verify(hotelService, times(1)).deleteHotels(anyList());
    }
}
