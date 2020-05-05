package com.visionarytech.eros.Models;

public class Dates {
    private String userId;
    private String userName;
    private String userLocation;
    private int userProfile;
    private String userInformation;

    public Dates() {
    }

    public Dates(String userId, String userName, String userLocation, int userProfile, String userInformation) {
        this.userId = userId;
        this.userName = userName;
        this.userLocation = userLocation;
        this.userProfile = userProfile;
        this.userInformation = userInformation;
    }

    //    Getters
    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserLocation() {
        return userLocation;
    }

    public int getUserProfile() {
        return userProfile;
    }

    public String getUserInformation() {
        return userInformation;
    }


    //    Setters
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserLocation(String userLocation) {
        this.userLocation = userLocation;
    }

    public void setUserProfile(int userProfile) {
        this.userProfile = userProfile;
    }

    public void setUserInformation(String userInformation) {
        this.userInformation = userInformation;
    }
}