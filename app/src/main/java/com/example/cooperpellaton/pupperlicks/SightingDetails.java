package com.example.cooperpellaton.pupperlicks;

/**
 * Created by Ronnie on 10/10/2017.
 *
 */
public class SightingDetails {
    private String key;
    private String date;
    private String location;
    private String zip;
    private String address;
    private String city;
    private String borough;
    private String latitude;
    private String longitude;

    /**
     * Constructor that takes in an array of length 9 containing strings
     *
     * @param details the details pertaining to the sighting
     */
    public SightingDetails(String[] details) {
        if (details.length != 9) {
            throw new IllegalArgumentException("String[] must be of length 9.");
        }
        details[0] = key;
        details[1] = date;
        details[2] = location;
        details[3] = zip;
        details[4] = address;
        details[5] = city;
        details[6] = borough;
        details[7] = latitude;
        details[8] = longitude;
    }
    String getKey(){
        return this.key;
    }
    String getDate() {
        return this.date;
    }
    String getLocation() {
        return this.location;
    }
    String getZip() {
        return this.zip;
    }
    String getAddress() {
        return this.address;
    }
    String getCity() {
        return this.city;
    }
    String getBorough() {
        return this.borough;
    }
    String getLatitude() {
        return this.latitude;
    }
    String getLongitude() {
        return this.longitude;
    }
}