package com.example.cooperpellaton.pupperlicks;

import android.os.AsyncTask;
import android.support.v7.util.AsyncListUtil;
import android.test.InstrumentationTestCase;

import org.junit.Test;
import org.junit.Assert;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by Cooper Pellaton on 11/13/2017.
 */

public class AllSightingsUnitTest extends InstrumentationTestCase {
    RatSighting sighting = new RatSighting("31464015", "11/13/2017 0:00", "3+ Family Mixed Use Building", "30332", "921 Hemphill Avenue", "Atlanta", "Midtown", "33.779823", "-84.402131");
    ServerPortal portal = new ServerPortal();
    private List<RatSighting> sightingsList;

    /**
     * Tests whether the response from getting the sightings is null.
     * @throws Throwable
     */
    @Test
    public void testNotNull() throws Throwable {
        // create  a signal to let us know when our task is done.
        final CountDownLatch signal = new CountDownLatch(1);

        final AsyncTask<String, Void, String> myTask = new AsyncTask<String, Void, String>() {

            @Override
            protected String doInBackground(String... arg0) {
                sightingsList = portal.getAllSightings();
                return("Success.");
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);

                signal.countDown();
            }
        };

        runTestOnUiThread(new Runnable() {

            @Override
            public void run() {
                myTask.execute("Do something");
            }
        });

        signal.await(30, TimeUnit.SECONDS);
        assertNotNull("The array is not null.", sightingsList);
    }

    /**
     * Tests the size of the array to determine whether all the sightings are actually being returned.
     * @throws Throwable
     */
    @Test
    public void testBigList() throws Throwable {
        // create  a signal to let us know when our task is done.
        final CountDownLatch signal = new CountDownLatch(1);

        final AsyncTask<String, Void, String> myTask = new AsyncTask<String, Void, String>() {

            @Override
            protected String doInBackground(String... arg0) {
                sightingsList = portal.getAllSightings();
                return("Success.");
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);

                signal.countDown();
            }
        };

        runTestOnUiThread(new Runnable() {

            @Override
            public void run() {
                myTask.execute("Do something");
            }
        });

        signal.await(30, TimeUnit.SECONDS);
        assertEquals("The array is of size greater than fifty.", sightingsList.size()>0, true);
        assertEquals("The array contains more than 1000 entries", sightingsList.size()>1000, true);
    }

}
