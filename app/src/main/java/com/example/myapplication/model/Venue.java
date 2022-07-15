package com.example.myapplication.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Venue implements Serializable {
    String id,name,address,cover_image,price,pincode;
    ArrayList<TimeSlots> timeslots;
    public Venue(){

    }

    public Venue(String id, String name, String address, String cover_image, String price, String pincode, ArrayList<TimeSlots> timeslots) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.cover_image = cover_image;
        this.price = price;
        this.pincode = pincode;
        this.timeslots = timeslots;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCover_image() {
        return cover_image;
    }

    public void setCover_image(String cover_image) {
        this.cover_image = cover_image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public ArrayList<TimeSlots> getTimeslots() {
        return timeslots;
    }

    public void setTimeslots(ArrayList<TimeSlots> timeslots) {
        this.timeslots = timeslots;
    }
}
