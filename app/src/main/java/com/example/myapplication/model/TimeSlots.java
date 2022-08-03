package com.example.myapplication.model;

import java.io.Serializable;

public class TimeSlots implements Serializable {
    String id,venue_id,start_time,end_time,prices;
    private boolean isSelected = false;
    public TimeSlots(){

    }

    public TimeSlots(String id, String venue_id, String start_time, String end_time, String prices, boolean isSelected) {
        this.id = id;
        this.venue_id = venue_id;
        this.start_time = start_time;
        this.end_time = end_time;
        this.prices = prices;
        this.isSelected = isSelected;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVenue_id() {
        return venue_id;
    }

    public void setVenue_id(String venue_id) {
        this.venue_id = venue_id;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getPrices() {
        return prices;
    }

    public void setPrices(String prices) {
        this.prices = prices;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
