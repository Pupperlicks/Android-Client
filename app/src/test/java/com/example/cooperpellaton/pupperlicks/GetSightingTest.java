package com.example.cooperpellaton.pupperlicks;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

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

        assertFalse(sighting.equals(activity.getSighting()));
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
