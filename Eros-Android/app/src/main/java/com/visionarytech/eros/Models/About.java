package com.visionarytech.eros.Models;

import java.io.Serializable;

public class About implements Serializable {
    private String _id;
    private String bio;
    private String views;

    public About() {
    }

//    Constructor
    public About(String _id, String bio, String views) {
        this._id = _id;
        this.bio = bio;
        this.views = views;
    }

//    Getters
    public String get_id() {
        return _id;
    }

    public String getBio() {
        return bio;
    }

    public String getViews() {
        return views;
    }

//    Setters
    public void set_id(String _id) {
        this._id = _id;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setViews(String views) {
        this.views = views;
    }
}


