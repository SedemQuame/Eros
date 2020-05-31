package com.visionarytech.eros.Models;

import java.io.Serializable;

public class Preferences implements Serializable {
    private String _id;
    private String gender;
    private String lookingFor;
    private String ageRange;

    public Preferences() {
    }

//    Constructor
    public Preferences(String _id, String gender, String lookingFor, String ageRange) {
        this._id = _id;
        this.gender = gender;
        this.lookingFor = lookingFor;
        this.ageRange = ageRange;
    }

//    Getters
    public String get_id() {
        return _id;
    }

    public String getGender() {
        return gender;
    }

    public String getLookingFor() {
        return lookingFor;
    }

    public String getAgeRange() {
        return ageRange;
    }

//    Setters
    public void set_id(String _id) {
        this._id = _id;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setLookingFor(String lookingFor) {
        this.lookingFor = lookingFor;
    }

    public void setAgeRange(String ageRange) {
        this.ageRange = ageRange;
    }
}
