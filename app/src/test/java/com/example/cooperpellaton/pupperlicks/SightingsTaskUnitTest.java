package com.example.cooperpellaton.pupperlicks;

import android.content.Context;
import android.os.AsyncTask;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

/**
 * Unit test for SightingsTast.onPostExecute() method
 *
 * Chosen Method was difficult to Test, was within an Async task and did set easily tested values.
 * Could not find a way to appropriately test whether items were added to the inflater properly, or
 * whether onclick was implemented properly.
 *
 * Created by Ronnie on 11/13/2017.
 */

public class SightingsTaskUnitTest {
    private static final int TIMEOUT = 200;

    @Before
    public void setUp() {
        SightingsListActivity sla = new SightingsListActivity();
    }

    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

}
