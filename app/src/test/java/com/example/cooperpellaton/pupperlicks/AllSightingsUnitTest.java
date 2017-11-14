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
 * A suite of tests to cover ServerPortal.getAllSightings().
 * Tests that:
 *  Return isn't null.
 *  Return is bigger than 50 items.
 *  Return is bigger than 1000 items.
 *  The base url for the request is the server.
 *  The query parameter used is to get all the sightings.
 *  The content returned is different from ServerPortal.getFifty().
 */

public class AllSightingsUnitTest extends InstrumentationTestCase {
    RatSighting sighting = new RatSighting("31464015", "11/13/2017 0:00", "3+ Family Mixed Use Building", "30332", "921 Hemphill Avenue", "Atlanta", "Midtown", "33.779823", "-84.402131");
    ServerPortal portal = new ServerPortal();
    private List<RatSighting> sightingsList;
    private List<RatSighting> comparisonList;

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
                myTask.execute("Executing notNull JUnit.");
            }
        });

        signal.await(30, TimeUnit.SECONDS);
        assertNotNull("The array is not null.", sightingsList);
    }

    /**
     * Tests the size of the array is bigger than fifty.
     * @throws Throwable
     */
    @Test
    public void testBiggerThanFifty() throws Throwable {
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
                myTask.execute("Executing biggerThanFifty JUnit.");
            }
        });

        signal.await(30, TimeUnit.SECONDS);
        assertEquals("The array is of size greater than fifty.", sightingsList.size()>0, true);
        assertEquals("The array contains more than 1000 entries", sightingsList.size()>1000, true);
    }

    /**
     * Tests the size of the array is bigger than fifty.
     * @throws Throwable
     */
    @Test
    public void testBiggerThanOneThousand() throws Throwable {
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
                myTask.execute("Executing biggerThanOneThousand JUnit.");
            }
        });

        signal.await(30, TimeUnit.SECONDS);
        assertEquals("The array contains more than 1000 entries", sightingsList.size()>1000, true);
    }

    /**
     * Tests the endpoint of the server call.
     * @throws Throwable
     */
    @Test
    public void testEndpointBase() throws Throwable {
        assertEquals("Check that the HOST is the expected IP.", portal.getHOST().equals("http://54.158.72.38:5000/") , true);
    }

    /**
     * Tests the query parameter of the server call.
     * @throws Throwable
     */
    @Test
    public void testQueryParameter() throws Throwable {
        assertEquals("Check that the query parameter is correct for all sightings.", portal.getSIGHTINGS().equals("sightings"), true);
    }

    /**
     * Tests that the return will be different from getFifty().
     * @throws Throwable
     */
    @Test
    public void testDifferentThanGetFifty() throws Throwable {
        // create  a signal to let us know when our task is done.
        final CountDownLatch signal = new CountDownLatch(1);

        final AsyncTask<String, Void, String> myTask = new AsyncTask<String, Void, String>() {

            @Override
            protected String doInBackground(String... arg0) {
                sightingsList = portal.getAllSightings();
                comparisonList = portal.getFifty();
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
                myTask.execute("Executing differentThanGetFifty JUnit.");
            }
        });

        signal.await(30, TimeUnit.SECONDS);
        assertTrue("Check that the lists aren't the same.", !sightingsList.equals(comparisonList));
        assertTrue("Check that the sizes aren't the same.", !(sightingsList.size() == comparisonList.size()));
        assertTrue("Check that both lists aren't null.", !(sightingsList == null) && !(comparisonList == null));
    }

}
