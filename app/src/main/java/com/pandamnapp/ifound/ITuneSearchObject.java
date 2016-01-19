package com.pandamnapp.ifound;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Kristina Lee on 1/15/2016.
 */
public class ITuneSearchObject {
    String trackName;
    String artworkUrl30;
    String shortDescription;
    String longDescription;
    String kind;
    String trackPrice;
    String artist;
    String genre;
    String artworkUrl100;

    ITuneSearchObject(String trackName, String artworkUrl30, String artworkUrl100,
                      String shortDescription, String longDescription, String kind, String trackPrice, String artist, String genre) {
        this.trackName = trackName;
        this.artworkUrl30 = artworkUrl30;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.kind = kind;
        this.trackPrice = trackPrice;
        this.genre = genre;
        this.artist = artist;
        this.artworkUrl100 = artworkUrl100;

    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getArtworkUrl30() {
        return artworkUrl30;
    }

    public void setArtworkUrl30(String artworkUrl30) {
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
        return getKind();
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

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getArtworkUrl100() {
        return artworkUrl100;
    }

    public void setArtworkUrl100(String artworkUrl100) {
        this.artworkUrl100 = artworkUrl100;
}


}


