package com.hotelmanagement.Models;

public class Room {
    private int id;
    private double price;
    private int typeRoomId;

    public Room(int id, double price, int typeRoomId) {
        this.id = id;
        this.price = price;
        this.typeRoomId = typeRoomId;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getTypeRoomId() { return typeRoomId; }
    public void setTypeRoomId(int typeRoomId) { this.typeRoomId = typeRoomId; }

    @Override
    public String toString() {
        return "Room [id=" + id + ", price=" + price + ", typeRoomId=" + typeRoomId + "]";
    }
}
