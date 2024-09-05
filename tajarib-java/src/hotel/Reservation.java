package hotel;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;

public class Reservation  {
    private int reservationId;
    private String customerName;
    private String roomType;
    private LocalDate  checkInDate;
    private LocalDate checkOutDate;


    public  Reservation (int reservationID , String customerName , String roomType , String checkInDate , String checkOutDate){
        this.reservationId = reservationID;
        this.customerName = customerName;
        this.roomType = roomType;
        this.checkInDate = LocalDate.parse(checkInDate);
        this.checkOutDate = LocalDate.parse(checkOutDate);
    }

    public int getReservationId(){
        return reservationId;
    }

    public String getCustomerName(){
        return customerName;
    }

    public String getRoomType(){
        return roomType;
    }

    public LocalDate getCheckInDate(){
        return checkInDate;
    }

    public LocalDate getCheckOutDate(){
        return checkOutDate;
    }

    public void setRoomType(String roomType){
        this.roomType = roomType;
    }

    public void setCheckInDate(LocalDate checkInDate){
        this.checkInDate = checkInDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate){
        this.checkOutDate = checkOutDate;
    }

    @Override
    public String toString(){
        return "Reservation Id : " + reservationId + " , Customer : " + customerName + ", Room: " + roomType + " , Check-In: " + checkInDate + " , Check-Out: " + checkOutDate;
    }
}


