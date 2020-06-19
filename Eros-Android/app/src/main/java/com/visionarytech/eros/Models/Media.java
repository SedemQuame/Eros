package com.visionarytech.eros.Models;

import java.io.Serializable;

public class Media implements Serializable {
    private String _id;
    private int numberOfLikes;
    private String assetType;
    private String assetUrl;
    private String viewerId;

    //      Constructor
    public Media(String _id, int numberOfLikes, String assetType, String assetUrl, String viewerId) {
        this._id = _id;
        this.numberOfLikes = numberOfLikes;
        this.assetType = assetType;
        this.assetUrl = assetUrl;
        this.viewerId = viewerId;
    }

    //      Getters
    public int getNumberOfLikes() {
        return numberOfLikes;
    }

    public String getAssetType() {
        return assetType;
    }

    public String getAssetUrl() {
        return assetUrl;
    }

    public String get_id() {
        return _id;
    }

    public String getViewerId() {
        return viewerId;
    }

    //      Setters
    public void setNumberOfLikes(int numberOfLikes) {
        this.numberOfLikes = numberOfLikes;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public void setAssetUrl(String assetUrl) {
        this.assetUrl = assetUrl;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public void setViewerId(String viewerId) {
        this.viewerId = viewerId;
    }
}
