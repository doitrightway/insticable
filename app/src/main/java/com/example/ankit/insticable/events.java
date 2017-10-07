package com.example.ankit.insticable;

/**
 * Created by ankit on 3/10/17.
 */

public class events {
    private String name;
    private String date;
    private String time;
    private String venue;
    private String description;
    private String interesttag;



    public events() {
    }


    public events(String date, String time, String venue, String description, String name) {
        this.date = date;
        this.time = time;
        this.venue = venue;
        this.description = description;
        this.name=name;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInteresttag() {
        return interesttag;
    }

    public void setInteresttag(String interesttag) {
        this.interesttag = interesttag;
    }


    public String getDate() {

        return date;
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
