package com.example.cooperpellaton.pupperlicks;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
/**
 *
 * To test if DetailsActivity.getSighting() populates a RatSighting.
 * Created by Victoria Joh on 11/13/17.
 */

public class GetSightingTest {

   DetailsActivity activity = new DetailsActivity();


    RatSighting sighting = new RatSighting("alolo", "11/13/2017", "locationType",
           "incidentZip", "incidentAddress", "city","borough",
           "latitude", "longitude");


    /**
     * Test whether a Rat Sighting has been created.
     */
    @Test
    public void sightingIsPopulated() {

        String testString = "RatSighting{}"
                + "\n uniqueKey: " + sighting.getUniqueKey()
                + "\n createdDate: " + sighting.getCreatedDate()
                + "\n locationType: " + sighting.getLocationType()
                + "\n incidentZip: " + sighting.getIncidentZip()
                + "\n incidentAddress: " + sighting.getIncidentAddress()
                + "\n city: " + sighting.getCity()
                + "\n borough: " + sighting.getBorough()
                + "\n latitude: " + sighting.getLatitude()
                + "\n longitude: " + sighting.getLongitude();


        assertEquals(sighting.toString(), testString);
    }

    /**
     * Test whether the good values are fetched from the Rat Sighting properly.
     */
    @Test
    public void properValuesTest() {
        assertEquals("alolo", sighting.getUniqueKey());
        assertEquals("alolo", sighting.getUniqueKey());
        assertEquals("11/13/2017", sighting.getCreatedDate());
        assertEquals("locationType", sighting.getLocationType());
        assertEquals("incidentZip", sighting.getIncidentZip());
        assertEquals("city", sighting.getCity());
        assertEquals("borough", sighting.getBorough());
        assertEquals("latitude", sighting.getLatitude());
        assertEquals("longitude", sighting.getLongitude());
    }





}
