package com.example.cooperpellaton.pupperlicks;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 *
 * Created by Blane on 11/13/2017
 */

@RunWith(AndroidJUnit4.class)
public class DetailsActivityTest {
    @Rule
    public ActivityTestRule<DetailsActivity> mActivityRule = new ActivityTestRule<>(
            DetailsActivity.class,
            false,
            false); // so that intent can be customized

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.cooperpellaton.pupperlicks", appContext.getPackageName());
    }

    @Test
    public void validBundleShouldResultInValidSighting() throws Exception {
        RatSighting sighting = new RatSighting(
                "0000000001",
                "10/01/1993 0:00",
                "Bathroom",
                "30189",
                "950 Rose Creek Run",
                "Haroldsvill",
                "Test Borough",
                "+00000.0000000",
                "-00000.0000000"
        );

        Bundle b = new Bundle();
        b.putSerializable("details", sighting);

        Intent intent = new Intent();
        intent.putExtras(b);

        // get the activity we're trying to inspect
        DetailsActivity activity = mActivityRule.launchActivity(intent);

        // internal ratsighting fields should match those of the sighting we constructed
        assertEquals(sighting.getUniqueKey(), activity.getSighting().getUniqueKey());
        assertEquals(sighting.getCreatedDate(), activity.getSighting().getCreatedDate());
        assertEquals(sighting.getLocationType(), activity.getSighting().getLocationType());
        assertEquals(sighting.getIncidentZip(), activity.getSighting().getIncidentZip());
        assertEquals(sighting.getIncidentAddress(), activity.getSighting().getIncidentAddress());
        assertEquals(sighting.getCity(), activity.getSighting().getCity());
        assertEquals(sighting.getBorough(), activity.getSighting().getBorough());
        assertEquals(sighting.getLatitude(), activity.getSighting().getLatitude());
        assertEquals(sighting.getLongitude(), activity.getSighting().getLongitude());
    }

    @Test
    public void nullBundleShouldResultInNullSighting() throws Exception {
        // get the activity we're trying to inspect
        Activity activity = mActivityRule.launchActivity(new Intent());

        // activity started without an intent should have a null internal RatSighting
        assertNull(((DetailsActivity)activity).getSighting());
    }
}
