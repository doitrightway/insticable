package com.example.ankit.insticable;

import java.util.List;

/**
 * Created by ankit on 3/10/17.
 */

public class events {
    private String name;
    private String date;
    private String time;
    private String venue;
    private String description;
    private List<String>interests;
    private String photoUrl;


    public events() {
    }


    public events(String date, String time, String venue, String description, String name,List<String> interests) {
        this.date = date;
        this.time = time;
        this.venue = venue;
        this.description = description;
        this.name=name;
        this.interests=interests;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getInterests() {
        return interests;
    }

    public void setInterests(List<String> interests) {
        this.interests = interests;
    }

    public String getDate() {

        return date;
    }
    public String getPhotoUrl(){ return photoUrl;}
//
    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
