package com.visionarytech.eros.Models;

public class Notifications {
    private String message;
    private String time;
    private String requesterProfilePhoto;

    //      Constructor
    public Notifications(String message, String time, String requesterProfilePhoto) {
        this.message = message;
        this.time = time;
        this.requesterProfilePhoto = requesterProfilePhoto;
    }

    //      Getters
    public String getMessage() {
        return message;
    }

    public String getTime() {
        return time;
    }

    public String getRequesterProfilePhoto() {
        return requesterProfilePhoto;
    }
}
