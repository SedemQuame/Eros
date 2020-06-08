package com.visionarytech.eros.Models;

import java.io.Serializable;

public class Media implements Serializable {
    private int numberOfLikes;
    private String assetType;
    private String assetUrl;

    public Media() {
    }

    public Media(int numberOfLikes, String assetType, String assetUrl) {
        this.numberOfLikes = numberOfLikes;
        this.assetType = assetType;
        this.assetUrl = assetUrl;
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

//      Setters
    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public void setNumberOfLikes(int numberOfLikes) {
        this.numberOfLikes = numberOfLikes;
    }

    public void setAssetUrl(String assetUrl) {
        this.assetUrl = assetUrl;
    }
}
