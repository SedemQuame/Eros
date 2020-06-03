package com.visionarytech.eros.Models;

import java.io.Serializable;

public class Contact implements Serializable {
    private String _id;
    private String emailAddress;
    private String phoneNumber;

    public Contact() {
    }

    public Contact(String _id, String emailAddress, String phoneNumber) {
        this._id = _id;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
    }

//    Getters
    public String get_id() {
        return _id;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

//    Setters
    public void set_id(String _id) {
        this._id = _id;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
