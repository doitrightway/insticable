package com.example.ankit.insticable;

import java.util.List;

/**
 * Event class for handling event'sinfo
 */
public class events {
    private String name;
    private String date;
    private String time;
    private String venue;
    private String description;
    private List<String>interests;
    private String photoUrl;


    /**
     * Instantiates a new Event.
     */
    public events() {
    }


    /**
     * Instantiates a new Event.
     *
     * @param date        the date
     * @param time        the time
     * @param venue       the venue
     * @param description the description
     * @param name        the name
     * @param interests   the interests
     */
    public events(String date, String time, String venue, String description, String name,List<String> interests) {
        this.date = date;
        this.time = time;
        this.venue = venue;
        this.description = description;
        this.name=name;
        this.interests=interests;

    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets interests.
     *
     * @return the interests
     */
    public List<String> getInterests() {
        return interests;
    }

    /**
     * Sets interests.
     *
     * @param interests the interests
     */
    public void setInterests(List<String> interests) {
        this.interests = interests;
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public String getDate() {

        return date;
    }

    /**
     * Get photo url string.
     *
     * @return the string
     */
    public String getPhotoUrl(){ return photoUrl;}

    /**
     * Sets photo url.
     *
     * @param photoUrl the photo url
     */
//
    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    /**
     * Sets date.
     *
     * @param date the date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Gets time.
     *
     * @return the time
     */
    public String getTime() {
        return time;
    }

    /**
     * Sets time.
     *
     * @param time the time
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * Gets venue.
     *
     * @return the venue
     */
    public String getVenue() {
        return venue;
    }

    /**
     * Sets venue.
     *
     * @param venue the venue
     */
    public void setVenue(String venue) {
        this.venue = venue;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
