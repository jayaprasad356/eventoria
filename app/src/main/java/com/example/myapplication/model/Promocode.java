package com.example.myapplication.model;

public class Promocode {
    String id,promo_code,message,start_date,end_date,no_of_users,minimum_order_amount,discount,max_discount_amount,no_of_repeat_usage,date_created,status,discount_type;
    public Promocode(){

    }

    public Promocode(String id, String promo_code, String message, String start_date, String end_date, String no_of_users, String minimum_order_amount, String discount, String max_discount_amount, String no_of_repeat_usage, String date_created, String status, String discount_type) {
        this.id = id;
        this.promo_code = promo_code;
        this.message = message;
        this.start_date = start_date;
        this.end_date = end_date;
        this.no_of_users = no_of_users;
        this.minimum_order_amount = minimum_order_amount;
        this.discount = discount;
        this.max_discount_amount = max_discount_amount;
        this.no_of_repeat_usage = no_of_repeat_usage;
        this.date_created = date_created;
        this.status = status;
        this.discount_type = discount_type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPromo_code() {
        return promo_code;
    }

    public void setPromo_code(String promo_code) {
        this.promo_code = promo_code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getNo_of_users() {
        return no_of_users;
    }

    public void setNo_of_users(String no_of_users) {
        this.no_of_users = no_of_users;
    }

    public String getMinimum_order_amount() {
        return minimum_order_amount;
    }

    public void setMinimum_order_amount(String minimum_order_amount) {
        this.minimum_order_amount = minimum_order_amount;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getMax_discount_amount() {
        return max_discount_amount;
    }

    public void setMax_discount_amount(String max_discount_amount) {
        this.max_discount_amount = max_discount_amount;
    }

    public String getNo_of_repeat_usage() {
        return no_of_repeat_usage;
    }

    public void setNo_of_repeat_usage(String no_of_repeat_usage) {
        this.no_of_repeat_usage = no_of_repeat_usage;
    }

    public String getDate_created() {
        return date_created;
    }

    public void setDate_created(String date_created) {
        this.date_created = date_created;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDiscount_type() {
        return discount_type;
    }

    public void setDiscount_type(String discount_type) {
        this.discount_type = discount_type;
    }
}
