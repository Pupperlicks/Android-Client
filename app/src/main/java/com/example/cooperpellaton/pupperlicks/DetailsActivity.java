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
        String[] details;
        TextView detailsView = (TextView) findViewById(R.id.details_text_view);
        if (savedInstanceState != null) {
            details = b.getStringArray("details");
            detailsView.setText("Unique Key: " + details[0] + "\nCreated Date: " + details[1]
                    + "\nLocation Type: " + details[2]
                    + "\nIncident Zip: " + details[3]
                    + "\nIncident Address: " + details[4]
                    + "\nCity: " + details[5]
                    + "\nBorough: " + details[6]
                    + "\nLatitude: " + details[7]
                    + "\nLongitude: " + details[8]
            );
        } else {
            detailsView.setText("Sighting not Found.");
        }
    }
}
