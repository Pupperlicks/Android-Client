package com.example.cooperpellaton.pupperlicks;

import java.io.Serializable;
import java.util.Scanner;

/**
 * Created by Cooper Pellaton on 10/11/2017.
 * Model object for a single rat sighting
 */

public class RatSighting implements Serializable {
    // minimum fields that need to be handled:
    private final String uniqueKey;
    private final String createdDate;
    private final String locationType;
    private final String incidentZip;
    private final String incidentAddress;
    private final String city;
    private final String borough;
    private final String latitude;
    private final String longitude;
    // end minimum fields

    // main constructor

    /**
     * The constructor for a rat sighting.
     * @param uniqueKey The unique key.
     * @param createdDate The date the rat sighting was created.
     * @param locationType The type of location.
     * @param incidentZip Zip code of incident.
     * @param incidentAddress Address.
     * @param city City.
     * @param borough Borough.
     * @param latitude Latitude.
     * @param longitude Longitude.
     */
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

    /**
     * Get the unique key.
     * @return the unique key.
     */
    public String getUniqueKey() {
        return uniqueKey;
    }

    /**
     * Get the created date.
     * @return the created date.
     */
    public String getCreatedDate() {
        return createdDate;
    }

    /**
     * Get the location type.
     * @return the location type.
     */
    public CharSequence getLocationType() {
        return locationType;
    }

    /**
     * Get the zip code.
     * @return The zip code.
     */
    public String getIncidentZip() {
        return incidentZip;
    }

    /**
     * Get the incident address.
     * @return The address.
     */
    public String getIncidentAddress() {
        return incidentAddress;
    }

    /**
     * Get the city.
     * @return The city.
     */
    public String getCity() {
        return city;
    }

    /**
     * Get the borough.
     * @return The borough.
     */
    public String getBorough() {
        return borough;
    }

    /**
     * Get the latitude.
     * @return The latitude.
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * Get the longitude.
     * @return The longitude.
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * Validates that the unique key stored in the sighting is a properly-formed integer.
     * @param sighting the RatSighting object which unique key we want to validate
     * @return true if the key is a properly formed integer, false if it's not
     */
    public static boolean validateKey(RatSighting sighting) {
        if (sighting.getUniqueKey() == null)
            return false;

        // verify that unique key is an integer
        Scanner sc = new Scanner(sighting.uniqueKey);

        if(!sc.hasNextInt()) // make sure there's at least one int to grab
            return false;

        int integer = sc.nextInt(); // store the value

        if (sc.hasNext()) { // now check and make sure there's not a second int (or more)
            return false;
        }

        // check integer range, can't be less than 0
        if (integer < 0) {
            return false;
        }

        return true; // if we've gotten this far, the number has passed validation
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
