package com.example.cooperpellaton.pupperlicks;

import android.os.AsyncTask;
import android.test.InstrumentationTestCase;
import org.junit.Test;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by Warren Waleed on 11/13/2017.
 *  Tests ServerPortal.GetFifty()
 *  Tests that:
 *  Return isn't greater than 50.
 *  Return isn't null.
 *  The base url for the request is the server.
 *  The query parameter used is to get all the sightings.
 *  Return isn't equal to getAllSightings()
 */

public class GetFiftyUnitTest extends InstrumentationTestCase {
    RatSighting sighting = new RatSighting("31464015", "11/13/2017 0:00", "3+ Family Mixed Use Building", "30332", "921 Hemphill Avenue", "Atlanta", "Midtown", "33.779823", "-84.402131");
    ServerPortalUtilites portal = new ServerPortalUtilites();
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
     * Tests if the size of the array is equal to fifty.
     * @throws Throwable
     */
    @Test
    public void testEqualToFifty() throws Throwable {
        // create  a signal to let us know when our task is done.
        final CountDownLatch signal = new CountDownLatch(1);

        final AsyncTask<String, Void, String> myTask = new AsyncTask<String, Void, String>() {

            @Override
            protected String doInBackground(String... arg0) {
                sightingsList = portal.getFifty();
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
        assertEquals("The array is of size equal to fifty.", sightingsList.size()==0, true);
        assertEquals("The array contains more than 1000 entries", sightingsList.size()<50, false);
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
    public void testDifferentThanGetAllSightings() throws Throwable {
        // create  a signal to let us know when our task is done.
        final CountDownLatch signal = new CountDownLatch(1);

        final AsyncTask<String, Void, String> myTask = new AsyncTask<String, Void, String>() {

            @Override
            protected String doInBackground(String... arg0) {
                sightingsList = portal.getFifty();
                comparisonList = portal.getAllSightings();
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
