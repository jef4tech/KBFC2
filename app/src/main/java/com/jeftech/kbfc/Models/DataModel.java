package com.jeftech.kbfc.Models;

public class DataModel {

    private String name, rooms, phone, place, imgURL;

    public DataModel(String name, String rooms, String phone, String place, String imgURL) {
        this.name = name;
        this.rooms = rooms;
        this.phone = phone;
        this.place = place;
        this.imgURL = imgURL;
    }

    public String getImgURL(){
        return imgURL;
    }

    public void setImgURL(String imgURL){
        this.imgURL = imgURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRooms() {
        return rooms;
    }

    public void setRooms(String rooms) {
        this.rooms = rooms;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }



}
