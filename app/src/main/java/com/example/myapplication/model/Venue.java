package com.example.myapplication.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Venue implements Serializable {
    String id,name,address,cover_image,price,pincode,image1,image2,image3,image4;
    ArrayList<TimeSlots> timeslots;
    public Venue(){

    }

    public Venue(String id, String name, String address, String cover_image, String price, String pincode, String image1, String image2, String image3, String image4, ArrayList<TimeSlots> timeslots) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.cover_image = cover_image;
        this.price = price;
        this.pincode = pincode;
        this.image1 = image1;
        this.image2 = image2;
        this.image3 = image3;
        this.image4 = image4;
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

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getImage3() {
        return image3;
    }

    public void setImage3(String image3) {
        this.image3 = image3;
    }

    public String getImage4() {
        return image4;
    }

    public void setImage4(String image4) {
        this.image4 = image4;
    }

    public ArrayList<TimeSlots> getTimeslots() {
        return timeslots;
    }

    public void setTimeslots(ArrayList<TimeSlots> timeslots) {
        this.timeslots = timeslots;
    }
}
