package com.example.cooperpellaton.pupperlicks;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * This displays the details of a rat sighting
 * Created by Ronnie on 10/10/2017.
 */

class DetailsActivity extends AppCompatActivity {
    SightingDetails details;

    /**
     * this takes in a SightingDetails object.
     * @param details details about sighting
     */
    public DetailsActivity(SightingDetails details) {
        super();
        this.details = details;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_view);
        TextView detailsView = (TextView) findViewById(R.id.details_text_view);
        detailsView.setText("Unique Key: " + details.getKey() + "\nCreated Date: " + details.getDate()
                + "\nLocation Type: " + details.getLocation()
                + "\nIncident Zip: " + details.getZip()
                + "\nIncident Address: " + details.getAddress()
                + "\nCity: " + details.getCity()
                + "\nBorough: " + details.getBorough()
                + "\nLatitude: " + details.getLatitude()
                + "\nLongitude: " + details.getLongitude()
        );
    }
}
