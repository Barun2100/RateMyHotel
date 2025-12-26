package com.hotelratingapp.hotel.service.HotelService.services;

import java.util.List;


import com.hotelratingapp.hotel.service.HotelService.entites.Hotel;

public interface HotelServices {
	
    Hotel createHotel(Hotel hotel);

    List<Hotel> getAllHotels();

    Hotel getHotelById(String hotelId);

	Hotel updateHotel(String hotelId, Hotel hotel);
	
	Boolean deleteHotel(String hotelId);

    
}
