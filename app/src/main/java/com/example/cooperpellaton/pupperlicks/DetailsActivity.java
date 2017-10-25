package com.example.cooperpellaton.pupperlicks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * This displays the details of a rat sighting
 * Created by Ronnie on 10/10/2017.
 * updated 10/17/17
 */
public class DetailsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_view);

        Bundle b = getIntent().getExtras(); // Not entirely sure how to get the catalyst intent
        RatSighting sighting;

        TextView detailsView = findViewById(R.id.details_text_view);

        if (b != null) {
            // retrieve the sighting from the bundle
            sighting = (RatSighting) b.getSerializable("details"); // de-serialize the object
            detailsView.setText("Unique Key: "
                    + "\nCreated Date: " + sighting.getCreatedDate()
                    + "\nLocation Type: " + sighting.getLocationType()
                    + "\nIncident Zip: " + sighting.getIncidentZip()
                    + "\nIncident Address: " + sighting.getIncidentAddress()
                    + "\nCity: " + sighting.getCity()
                    + "\nBorough: " + sighting.getBorough()
                    + "\nLatitude: " + sighting.getLatitude()
                    + "\nLongitude: " + sighting.getLongitude()
            );
        } else {
            detailsView.setText("Sighting not Found.");
        }
    }
}
