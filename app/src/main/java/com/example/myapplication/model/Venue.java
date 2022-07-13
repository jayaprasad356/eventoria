package com.example.myapplication.model;

public class Venue {
    String id,name,address,cover_image,price,pincode;
    public Venue(){

    }

    public Venue(String id, String name, String address, String cover_image, String price, String pincode) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.cover_image = cover_image;
        this.price = price;
        this.pincode = pincode;
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
}
