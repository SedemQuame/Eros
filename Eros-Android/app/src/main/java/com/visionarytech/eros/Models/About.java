package com.visionarytech.eros.Models;

import java.io.Serializable;

public class About implements Serializable {
    private String bio;
    private String views;

    //    Constructor
    public About(String _id, String bio, String views) {
        this.bio = bio;
        this.views = views;
    }

//    Getter
    public String getBio() {
        return bio;
    }

    public String getViews() {
        return views;
    }
}


