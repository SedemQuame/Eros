package com.visionarytech.eros.Models;

import java.io.Serializable;

public class Dates implements Serializable {
    private String _id;
    private String name;
    private String age;
    private String location;
    private String profilePhoto;
    private About me;
    private Preferences preferences;
    private SocialBackGround background;
    private Contact contactInfo;
    private String media;

    //    Constructors
    public Dates(String _id, String name, String age, String location, String profilePhoto, About me, Preferences preferences, SocialBackGround background, Contact contactInfo, String media) {
        this._id = _id;
        this.name = name;
        this.age = age;
        this.location = location;
        this.profilePhoto = profilePhoto;
        this.me = me;
        this.preferences = preferences;
        this.background = background;
        this.contactInfo = contactInfo;
        this.media = media;
    }

    //    Getters
    public String get_id() {
        return _id;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getLocation() {
        return location;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public About getMe() {
        return me;
    }

    public Preferences getPreferences() {
        return preferences;
    }

    public SocialBackGround getBackground() {
        return background;
    }

    public Contact getContactInfo() {
        return contactInfo;
    }

    public String getMedia() {
        return media;
    }

//    Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setPreferences(Preferences preferences) {
        this.preferences = preferences;
    }

    public void setBackground(SocialBackGround background) {
        this.background = background;
    }
}