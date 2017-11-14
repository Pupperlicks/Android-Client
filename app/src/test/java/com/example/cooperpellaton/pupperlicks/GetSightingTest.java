package com.example.cooperpellaton.pupperlicks;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Victoria Joh on 11/13/17.
 */




public class GetSightingTest {

   DetailsActivity activity = new DetailsActivity();

   RatSighting sighting = new RatSighting("alolo", "11/13/2017", "locationType",
           "incidentZip", "incidentAddress", "city","borough",
           "latitude", "longitude");

    @Test
    public void sightingIsPopulated() {


        assertEquals(sighting, activity.getSighting());
    }
}
