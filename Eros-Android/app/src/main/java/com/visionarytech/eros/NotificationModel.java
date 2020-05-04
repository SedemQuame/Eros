package com.visionarytech.eros;

public class NotificationModel {
    private String message;
    private String time;
    private int requesterProfilePhoto;

    public NotificationModel() {
    }

    public NotificationModel(String message, String time, int requesterProfilePhoto) {
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
