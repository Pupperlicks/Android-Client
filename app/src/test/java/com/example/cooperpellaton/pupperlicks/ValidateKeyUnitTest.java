package com.example.cooperpellaton.pupperlicks;

import android.content.Context;
import android.os.AsyncTask;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Unit test for RatSighting.onPostExecute() method
 * Tests that unique key is an integer, for now
 *
 * Created by blane on 11/20/2017.
 */

public class ValidateKeyUnitTest {
    private static final int TIMEOUT = 200;

    @Before
    public void setUp() {}

    @Test
    public void testUniqueKeyValidation() {

        // should return true: integer is correct
        RatSighting sighting = new RatSighting("12345678","","","","","","","","");
        assertTrue(RatSighting.validateKey(sighting));

        // should return false: integer is wrong
        sighting = new RatSighting("12345678 392922","","","","","","","","");
        assertFalse(RatSighting.validateKey(sighting));

        // should return true: integer is correct
        sighting = new RatSighting("flam","","","","","","","","");
        assertFalse(RatSighting.validateKey(sighting));

        // should return false: integer is less than 0
        sighting = new RatSighting("-1","","","","","","","","");
        assertFalse(RatSighting.validateKey(sighting));

        // should return false: integer string is null
        sighting = new RatSighting(null,"","","","","","","","");
        assertFalse(RatSighting.validateKey(sighting));
    }

}
