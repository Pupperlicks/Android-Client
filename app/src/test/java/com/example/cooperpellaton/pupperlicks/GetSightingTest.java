package com.example.cooperpellaton.pupperlicks;


import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;


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

    public RatSighting setSighting(RatSighting sighting) {
        this.sighting = sighting;
        return sighting;
    }


    /**
     * Test whether a Rat Sighting toString has been created from it's values.
     */
    @Test
    public void ratSightingToStringTest() {

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
     * Test whether good values are fetched from the Rat Sighting properly.
     */
    @Test
    public void properValuesTest() {
        assertEquals("alolo", sighting.getUniqueKey());
        assertEquals("11/13/2017", sighting.getCreatedDate());
        assertEquals("locationType", sighting.getLocationType());
        assertEquals("incidentZip", sighting.getIncidentZip());
        assertEquals("city", sighting.getCity());
        assertEquals("borough", sighting.getBorough());
        assertEquals("latitude", sighting.getLatitude());
        assertEquals("longitude", sighting.getLongitude());
    }

    /**
     * Test whether a Rat Sighting has been created.
     */

    @Test
    public void getSightingTest() {

        RatSighting newSighting = setSighting(sighting);

        //RatSighting nullSighting = null;

        if (newSighting == null) {
            System.out.println("Sighting is NULL");
        } else {
            System.out.println("Sighting is valid object");
        }
        assertNotNull(activity.getSighting());


    }


}
