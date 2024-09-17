package com.hotelmanagement.Util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class InputValidation {

    public boolean custumerValidation(String name){
        return name != null && name.matches("[a-zA-Z\\s]+");
    }

    public boolean roomTypeValidation(String roomType){
        String[] validRoomTypes = {"Single" , "Double" , "Suite"};
        for(String type : validRoomTypes){
            if (type.equalsIgnoreCase(roomType)){
                return true;
            }
        }
        return false ;
    }

    public boolean  checkDateValidation(String dateStr) {
        try {
            LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ISO_DATE);
            return !date.isBefore(LocalDate.now());
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}