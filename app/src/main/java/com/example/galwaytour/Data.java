package com.example.galwaytour;

/**
 * Created by Dave on 03/04/2015.
 */
public class Data {

    String Name = null;
    String latitude = null;
    String longitude = null;
    String category = null;
    String Rating_id = null;

    public Data(String name, String latitude, String longitude, String category, String rating_id) {
        Name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.category = category;
        Rating_id = rating_id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getRating_id() {
        return Rating_id;
    }

    public void setRating_id(String rating_id) {
        Rating_id = rating_id;
    }
}
