package com.hotelratingapp.hotel.service.HotelService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotelratingapp.hotel.service.HotelService.entites.Hotel;
import com.hotelratingapp.hotel.service.HotelService.services.HotelServices;



@RestController
@RequestMapping("/hotels")
public class HotelController {
    
    @Autowired
    private HotelServices hotelService;

    @PostMapping("/")
    public ResponseEntity<Hotel> cretaeHotel(@RequestBody Hotel hotel){
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.createHotel(hotel));
    }

    @GetMapping("/{hotelId}")
    public ResponseEntity<Hotel> getHotel(@PathVariable String hotelId){
        return ResponseEntity.status(HttpStatus.OK).body(hotelService.getHotelById(hotelId));
    }

    @GetMapping("")
    public ResponseEntity<List<Hotel>> getAllHotel(){
        return ResponseEntity.ok(hotelService.getAllHotels());
    }
    
    @PutMapping("/update/{hotelId}")
    public ResponseEntity<Hotel> updateHotel(@PathVariable String hotelId, @RequestBody Hotel hotel){
        return ResponseEntity.ok(hotelService.updateHotel(hotelId, hotel));
    }
    
    @DeleteMapping("/delete/{hotelId}")
    public ResponseEntity<Boolean> deleteHotel(@PathVariable String hotelId){
    	return ResponseEntity.ok(hotelService.deleteHotel(hotelId));
    }


}
