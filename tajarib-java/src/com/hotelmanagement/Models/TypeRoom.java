package com.hotelmanagement.Models;

public class TypeRoom {
    private int id ;
    private String type ;

    public TypeRoom(int id, String type) {
        this.id = id ;
        this.type = type;
    }

    public int getId(){
        return id;
    }

    public String getType(){
        return type;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setType(String type){
        this.type = type;
    }
}
