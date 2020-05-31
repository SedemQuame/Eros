package com.visionarytech.eros.Models;

import java.io.Serializable;

public class SocialBackGround implements Serializable {
    private String _id;
    private String work;
    private String school;
    private String religion;

    public SocialBackGround() {
    }

//    Constructor
    public SocialBackGround(String _id, String work, String school, String religion) {
        this._id = _id;
        this.work = work;
        this.school = school;
        this.religion = religion;
    }

//    Getter
    public String get_id() {
        return _id;
    }

    public String getWork() {
        return work;
    }

    public String getSchool() {
        return school;
    }

    public String getReligion() {
        return religion;
    }

//    Setter
    public void set_id(String _id) {
        this._id = _id;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }
}
