package com.pandamnapp.ifound;

import android.graphics.Bitmap;

/**
 * Created by Kristina Lee on 1/15/2016.
 */
public class ITuneSearchObject {
    String trackName;
    Bitmap artworkUrl30;
    String shortDescription;
    String longDescription;
    String kind;
    String trackPrice;

    ITuneSearchObject(String trackName, Bitmap artworkUrl30,
                      String shortDescription, String longDescription) {
        this.trackName = trackName;
        this.artworkUrl30 = artworkUrl30;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.kind = kind;
        this.trackPrice = trackPrice;

    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public Bitmap getArtworkUrl30() {
        return artworkUrl30;
    }

    public void setArtworkUrl30(Bitmap artworkUrl30) {
        this.artworkUrl30 = artworkUrl30;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getTrackPrice() {
        return trackPrice;
    }

    public void setTrackPrice(String trackPrice) {
        this.trackPrice = trackPrice;
    }
}
