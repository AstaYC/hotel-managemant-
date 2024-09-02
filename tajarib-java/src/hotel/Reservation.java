package hotel;

public class Reservation  {
    private int reservationId;
    private String customerName;
    private String roomType;
    private String checkInDate;
    private String checkOutDate;


    public  Reservation (int reservationID , String customerName , String roomType , String checkInDate , String checkOutDate){
        this.reservationId = reservationID;
        this.customerName = customerName;
        this.roomType = roomType;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
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
    public String getCheckInDate(){
        return checkInDate;
    }
    public String getCheckOutDate(){
        return checkOutDate;
    }
    @Override
    public String toString(){
        return "Reservation Id : " + reservationId + " , Customer : " + customerName + ", Room: " + roomType + " , Check-In: " + checkInDate + " , Check-Out: " + checkOutDate;
    }
}


