package com.hotel.hotelmanagement;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import com.hotel.hotelmanagement.model.Hotel;
import com.hotel.hotelmanagement.repository.AmenityRepository;
import com.hotel.hotelmanagement.repository.HotelRepository;
import com.hotel.hotelmanagement.repository.RoomTypeRepository;
import com.hotel.hotelmanagement.service.HotelService;

public class ServiceTest {

    @Mock
    private HotelRepository hotelRepository;

    @Mock
    private AmenityRepository amenitiesRepository;

    @Mock
    private RoomTypeRepository roomTypeRepository;

    @InjectMocks
    private HotelService hotelService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveHotel() {
        // Hotel hotel = new Hotel();
        Hotel hotel2 = new Hotel("1", "Mayfair");
        when(hotelRepository.save(hotel2)).thenReturn(hotel2);
        Hotel savedHotel = hotelService.saveHotel(hotel2);

        assertEquals(hotel2, savedHotel);
        verify(hotelRepository, times(1)).save(hotel2);
    }

    @Test
    public void testSaveHotels() {
        List<Hotel> hotels = Arrays.asList(new Hotel(), new Hotel());
        when(hotelRepository.saveAll(hotels)).thenReturn(hotels);

        List<Hotel> savedHotels = hotelService.saveHotels(hotels);
        assertEquals(hotels, savedHotels);
        verify(hotelRepository, times(1)).saveAll(hotels);
    }

    @Test
    public void testUpdateHotel() {
        Hotel hotel = new Hotel();
        when(hotelRepository.save(hotel)).thenReturn(hotel);

        Hotel updatedHotel = hotelService.updateHotel(hotel);
        assertEquals(hotel, updatedHotel);
        verify(hotelRepository, times(1)).save(hotel);
    }

    @Test
    public void testDeleteHotel() {
        String hotelId = "1";
        doNothing().when(hotelRepository).deleteById(hotelId);

        hotelService.deleteHotel(hotelId);
        verify(hotelRepository, times(1)).deleteById(hotelId);
    }

    @Test
    public void testGetHotelByIdFound() {
        String hotelId = "1";
        Hotel hotel = new Hotel();
        when(hotelRepository.findById(hotelId)).thenReturn(Optional.of(hotel));

        Hotel foundHotel = hotelService.getHotelById(hotelId);
        assertEquals(hotel, foundHotel);
        verify(hotelRepository, times(1)).findById(hotelId);
    }

    @Test
    public void testGetHotelByIdNotFound() {
        String hotelId = "1";
        when(hotelRepository.findById(hotelId)).thenReturn(Optional.empty());

        Hotel foundHotel = hotelService.getHotelById(hotelId);
        assertNull(foundHotel);
        verify(hotelRepository, times(1)).findById(hotelId);
    }

    @Test
    public void testGetAllHotels() {
        List<Hotel> hotels = Arrays.asList(new Hotel(), new Hotel());
        when(hotelRepository.findAll()).thenReturn(hotels);

        List<Hotel> allHotels = hotelService.getAllHotels();
        assertEquals(hotels, allHotels);
        verify(hotelRepository, times(1)).findAll();
    }

    @Test
    public void testGetHotelsByName() {
        String name = "Hotel";
        List<Hotel> hotels = Arrays.asList(new Hotel());
        when(hotelRepository.findByNameContainingIgnoreCase(name)).thenReturn(hotels);

        List<Hotel> foundHotels = hotelService.getHotelsByName(name);
        assertEquals(hotels, foundHotels);
        verify(hotelRepository, times(1)).findByNameContainingIgnoreCase(name);
    }

    // Additional tests for amenities and room types can be added similarly
    public AmenityRepository getAmenitiesRepository() {
        return amenitiesRepository;
    }

    public void setAmenitiesRepository(AmenityRepository amenitiesRepository) {
        this.amenitiesRepository = amenitiesRepository;
    }

    public RoomTypeRepository getRoomTypeRepository() {
        return roomTypeRepository;
    }

    public void setRoomTypeRepository(RoomTypeRepository roomTypeRepository) {
        this.roomTypeRepository = roomTypeRepository;
    }
}
