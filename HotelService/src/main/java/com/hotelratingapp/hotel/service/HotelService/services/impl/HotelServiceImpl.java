package com.hotelratingapp.hotel.service.HotelService.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelratingapp.hotel.service.HotelService.entites.Hotel;
import com.hotelratingapp.hotel.service.HotelService.repositories.HotelRepository;
import com.hotelratingapp.hotel.service.HotelService.services.HotelServices;
import com.hotelratingapp.hotel.service.HotelService.Exceptions.ResourceNotFoundException;

@Service
public class HotelServiceImpl implements HotelServices{

    @Autowired
    private HotelRepository hotelRepo;

    @Override
    public Hotel createHotel(Hotel hotel) {
        String hotelId = UUID.randomUUID().toString();
        hotel.setHotelId(hotelId);
        return hotelRepo.save(hotel);
    }

    @Override
    public List<Hotel> getAllHotels() {
        return hotelRepo.findAll();
    }

    @Override
    public Hotel getHotelById(String hotelId) {
        return hotelRepo.findById(hotelId).orElseThrow(()-> new ResourceNotFoundException("Hotel id not found !!"));
    }

	@Override
	public Hotel updateHotel(String hotelId, Hotel hotel) {
		Hotel hotelDetails = getHotelById(hotelId);
		hotelDetails.setHotelName(hotel.getHotelName());
		hotelDetails.setAbout(hotel.getAbout());
		hotelDetails.setLocation(hotel.getLocation());
		return hotelRepo.save(hotelDetails);
	}

	@Override
	public Boolean deleteHotel(String hotelId) {
		try {
			getHotelById(hotelId);
			hotelRepo.deleteById(hotelId);
			return true;
		}
		catch(Exception e){
			return false;
		}
	}
    
}
