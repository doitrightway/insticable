package com.example.ankit.insticable;

/**
 * Created by sharvik on 14/10/17.
 */
public class MessageInstance {
    private String text;
    private String name;
    private String photoUrl;

    /**
     * Instantiates a new Message instance.
     */
    public MessageInstance() {
    }

    /**
     * Instantiates a new Message instance.
     *
     * @param text the text
     * @param name the name
     */
    public MessageInstance(String text, String name) {
        this.text = text;
        this.name = name;
    }

    /**
     * Instantiates a new Message instance.
     *
     * @param text     the text
     * @param name     the name
     * @param photoUrl the photo url
     */
    public MessageInstance(String text, String name, String photoUrl) {
        this.text = text;
        this.name = name;
        this.photoUrl = photoUrl;
    }

    /**
     * Gets text.
     *
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * Sets text.
     *
     * @param text the text
     */
    public void setText(String text) {
        this.text = text;
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
     * Gets photo url.
     *
     * @return the photo url
     */
    public String getPhotoUrl() {
        return photoUrl;
    }

    /**
     * Sets photo url.
     *
     * @param photoUrl the photo url
     */
    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
