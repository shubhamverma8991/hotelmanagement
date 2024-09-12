package com.hotel.hotelmanagement.model;

import java.util.List;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.data.mongodb.core.mapping.Document;

//@Entity
@Document(collection = "hotels")
public class Hotel {

    @Id
    // @GeneratedValue(strategy = GenerationType.AUTO)
    private String id; 
    private String name;
    private String location;
    private double rating;
    private List<String> images;
    // @DBRef
    @ManyToOne
    @JoinColumn(name = "id")
    private List<RoomType> roomTypes;
    // @DBRef
    // @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    // @JoinTable(
    //         name = "room_type_amenities",
    //         joinColumns = @JoinColumn(name = "room_type_id"),
    //         inverseJoinColumns = @JoinColumn(name = "amenity_id"))
    // private List<Amenity> amenities;
    public Hotel(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Hotel() {
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public List<RoomType> getRoomTypes() {
        return roomTypes;
    }

    public void setRoomTypes(List<RoomType> roomTypes) {
        this.roomTypes = roomTypes;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", rating=" + rating +
                ", images=" + images +
                ", roomTypes=" + roomTypes +
                '}';
    }
}
