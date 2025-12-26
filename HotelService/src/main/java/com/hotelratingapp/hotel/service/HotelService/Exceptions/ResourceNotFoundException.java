package com.hotelratingapp.hotel.service.HotelService.Exceptions;

public class ResourceNotFoundException extends RuntimeException{
    
    public ResourceNotFoundException(String ex){
        super(ex);
    }

    public ResourceNotFoundException(){
        super("Resource not found !!");
    }
}
