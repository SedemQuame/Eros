package com.visionarytech.eros.Models;

public class Notifications {
    private String message;
    private String time;
    private int requesterProfilePhoto;

    public Notifications() {
    }

    public Notifications(String message, String time, int requesterProfilePhoto) {
        this.message = message;
        this.time = time;
        this.requesterProfilePhoto = requesterProfilePhoto;
    }

    public String getMessage() {
        return message;
    }

    public String getTime() {
        return time;
    }

    public int getRequesterProfilePhoto() {
        return requesterProfilePhoto;
    }
}
