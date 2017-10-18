package com.example.cooperpellaton.pupperlicks;

/**
 * Created by Cooper Pellaton on 10/11/2017.
 * Model object for a single rat sighting
 */

public class RatSighting {
    // minimum fields that need to be handled:
    private String uniqueKey;
    private String createdDate;
    private String locationType;
    private String incidentZip;
    private String incidentAddress;
    private String city;
    private String borough;
    private String latitude;
    private String longitude;
    // end minimum fields

    // main constructor
    public RatSighting(String uniqueKey, String createdDate, String locationType,
                       String incidentZip, String incidentAddress, String city, String borough,
                       String latitude, String longitude) {
        this.uniqueKey = uniqueKey;
        this.createdDate = createdDate;
        this.locationType = locationType;
        this.incidentZip = incidentZip;
        this.incidentAddress = incidentAddress;
        this.city = city;
        this.borough = borough;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // getters and setters

    public String getUniqueKey() {
        return uniqueKey;
    }

    public void setUniqueKey(String uniqueKey) {
        this.uniqueKey = uniqueKey;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getLocationType() {
        return locationType;
    }

    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }

    public String getIncidentZip() {
        return incidentZip;
    }

    public void setIncidentZip(String incidentZip) {
        this.incidentZip = incidentZip;
    }

    public String getIncidentAddress() {
        return incidentAddress;
    }

    public void setIncidentAddress(String incidentAddress) {
        this.incidentAddress = incidentAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBorough() {
        return borough;
    }

    public void setBorough(String borough) {
        this.borough = borough;
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

    // basic toString method
    @Override
    public String toString() {
        return "RatSighting{}"
                + "\n uniqueKey: " + uniqueKey
                + "\n createdDate: " + createdDate
                + "\n locationType: " + locationType
                + "\n incidentZip: " + incidentZip
                + "\n incidentAddress: " + incidentAddress
                + "\n city: " + city
                + "\n borough: " + borough
                + "\n latitude: " + latitude
                + "\n longitude: " + longitude;
    }
}
