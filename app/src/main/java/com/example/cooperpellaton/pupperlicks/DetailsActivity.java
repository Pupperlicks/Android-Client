package com.example.cooperpellaton.pupperlicks;

import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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

        Bundle b = getIntent().getExtras();
        RatSighting sighting;

        AppBarLayout appBarLayout = findViewById(R.id.appbar);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        // get references to the TextViews in the layout
        TextView tvCreatedDate = findViewById(R.id.textViewCreatedDate);
        TextView tvLocationType = findViewById(R.id.textViewLocationType);
        TextView tvIncidentZip = findViewById(R.id.textViewZip);
        TextView tvIncidentAddress = findViewById(R.id.textViewAddress);
        TextView tvCity = findViewById(R.id.textViewCity);
        TextView tvBorough = findViewById(R.id.textViewBorough);
        TextView tvCoords = findViewById(R.id.textViewCoords);

//        TextView detailsView = findViewById(R.id.details_text_view);

        if (b != null) {
            // retrieve the sighting from the bundle
            sighting = (RatSighting) b.getSerializable("details"); // de-serialize the object

            tvCreatedDate.setText(sighting.getCreatedDate());
            tvLocationType.setText(sighting.getLocationType());
            tvIncidentZip.setText(sighting.getIncidentZip());
            tvIncidentAddress.setText(sighting.getIncidentAddress());
            tvCity.setText(sighting.getCity());
            tvBorough.setText(sighting.getBorough());
            tvCoords.setText(sighting.getLatitude() + ", " + sighting.getLongitude());

            this.getSupportActionBar().setTitle("Sighting " + sighting.getUniqueKey());

        } else {
//            detailsView.setText("Sighting not Found.");
            Log.e("error", "A RatSIghting object wasn't passed in the bundle.");
        }
    }
}
