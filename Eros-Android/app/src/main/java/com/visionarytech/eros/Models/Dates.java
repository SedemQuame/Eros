package com.visionarytech.eros.Models;

import java.io.Serializable;

public class Dates implements Serializable {
    private String _id;
    private String name;
    private String age;
//    private String location;
//    private String profilePhoto;
    private About me;
    private Preferences preferences;
    private SocialBackGround background;

    public Dates() {
    }

//    Constructors

    public Dates(String _id, String name, String age, About me, Preferences preferences, SocialBackGround background) {
        this._id = _id;
        this.name = name;
        this.age = age;
        this.me = me;
        this.preferences = preferences;
        this.background = background;
    }


//    public Dates(String _id, String name, String age, String location, String profilePhoto,
//                 About me, Preferences preferences, SocialBackGround background) {
//        this._id = _id;
//        this.name = name;
//        this.age = age;
//        this.location = location;
//        this.profilePhoto = profilePhoto;
//        this.me = me;
//        this.preferences = preferences;
//        this.background = background;
//    }

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

//    public String getLocation() {
//        return location;
//    }
//
//    public String getProfilePhoto() {
//        return profilePhoto;
//    }

    public About getMe() {
        return me;
    }

    public Preferences getPreferences() {
        return preferences;
    }

    public SocialBackGround getBackground() {
        return background;
    }


//    Setters

    public void set_id(String _id) {
        this._id = _id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(String age) {
        this.age = age;
    }

//    public void setLocation(String location) {
//        this.location = location;
//    }
//
//    public void setProfilePhoto(String profilePhoto) {
//        this.profilePhoto = profilePhoto;
//    }

    public void setMe(About me) {
        this.me = me;
    }

    public void setPreferences(Preferences preferences) {
        this.preferences = preferences;
    }

    public void setBackground(SocialBackGround background) {
        this.background = background;
    }
}