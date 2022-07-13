package com.example.myapplication.model;

public class Recommend {
    String id,name,cover_photo,price,category_id,description,pincode;
    public Recommend(){

    }

    public Recommend(String id, String name, String cover_photo, String price, String category_id, String description, String pincode) {
        this.id = id;
        this.name = name;
        this.cover_photo = cover_photo;
        this.price = price;
        this.category_id = category_id;
        this.description = description;
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

    public String getCover_photo() {
        return cover_photo;
    }

    public void setCover_photo(String cover_photo) {
        this.cover_photo = cover_photo;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }
}
