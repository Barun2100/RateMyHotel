package com.hotelratingapp.hotel.service.HotelService.repositories;

import com.hotelratingapp.hotel.service.HotelService.entites.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, String> {
    
}
