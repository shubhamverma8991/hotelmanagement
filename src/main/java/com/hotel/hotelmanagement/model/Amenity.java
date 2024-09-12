package com.hotel.hotelmanagement.model;

// import javax.persistence.GeneratedValue;
// import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "amenities")
public class Amenity {

    @Id
    // @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String name;

    public Amenity(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Amenity() {
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

}
