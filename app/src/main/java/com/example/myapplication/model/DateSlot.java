package com.example.myapplication.model;

public class DateSlot {
    String id,slotDate,dateft;
    public DateSlot(){

    }

    public DateSlot(String id, String slotDate, String dateft) {
        this.id = id;
        this.slotDate = slotDate;
        this.dateft = dateft;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSlotDate() {
        return slotDate;
    }

    public void setSlotDate(String slotDate) {
        this.slotDate = slotDate;
    }

    public String getDateft() {
        return dateft;
    }

    public void setDateft(String dateft) {
        this.dateft = dateft;
    }
}
