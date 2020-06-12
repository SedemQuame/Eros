package com.visionarytech.eros.Models;

import java.io.Serializable;

public class SocialBackGround implements Serializable {
    private String work;
    private String school;
    private String religion;

    //    Constructor
    public SocialBackGround(String _id, String work, String school, String religion) {
        this.work = work;
        this.school = school;
        this.religion = religion;
    }

    //    Getter
    public String getWork() {
        return work;
    }

    public String getSchool() {
        return school;
    }

    public String getReligion() {
        return religion;
    }

}
