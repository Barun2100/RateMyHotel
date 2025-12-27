package com.hotelratingapp.user.service.userservice.external.services;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.hotelratingapp.user.service.userservice.entities.Hotel;

@FeignClient(name="HOTELSERVICE")
public interface HotelService {
	
	@PostMapping("/hotels/")
    public Hotel cretaeHotel(@RequestBody Hotel hotel);

    @GetMapping("/hotels/{hotelId}")
    public Hotel getHotel(@PathVariable String hotelId);

    @GetMapping("/hotels")
    public List<Hotel> getAllHotel();
    
    @PutMapping("/hotels/update/{hotelId}")
    public Hotel updateHotel(@PathVariable String hotelId, @RequestBody Hotel hotel);
    
    @DeleteMapping("/hotels/delete/{hotelId}")
    public Boolean deleteHotel(@PathVariable String hotelId);
	

}
