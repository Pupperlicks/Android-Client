package com.example.cooperpellaton.pupperlicks;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

//import com.google.firebase.firebase_core.*;
import com.google.firebase.database.*;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

/**
 * Activity designed to retrieve rat sightings from the backend and display them as a list to the
 * user.
 */

public class SightingsListActivity extends AppCompatActivity {

    private ListView mListView;
    private DatabaseReference mDatabase;
    private Adapter mAdapter;
    Context context;


    /**
     * @return a list string arrays that represent each sighting entry
     */
    private List<RatSighting> readCSVFromAssetFolder(){

        List<RatSighting> entries = new ArrayList<>();
        String[] rawSightingData;

        try {
            // open the file
            InputStream inputStream = getAssets().open("Rat_Sightings_less.csv");
            // set up our file reader
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

            String line; // temp string that we'll read into

            // while there are still entries to read
            while ((line = br.readLine()) != null){
                // TODO: skip over first (header) line
                rawSightingData = line.split(","); // splits the line into an array of strings

                entries.add(CSVToRatSighting(rawSightingData)); // add the array of strings to our main list
            }

            br.close(); // finally, close the buffered reader

        } catch (IOException e) {
            e.printStackTrace();
            Log.i("Error", e.toString());
        }
        return entries; // return the entry
    }

    private RatSighting CSVToRatSighting(String[] rawSightingData) {
        return new RatSighting(
            rawSightingData[0], // uniqueKey
            rawSightingData[1], // createdDate
            rawSightingData[2], // locationType
            rawSightingData[3], // incidentZip
            rawSightingData[4], // incidentAddress
            rawSightingData[5], // city
            rawSightingData[6], // borough
            rawSightingData[7], // latitude
            rawSightingData[8]); // longitude
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_sightings_list);

        List<RatSighting> sightings = readCSVFromAssetFolder();

        Log.e("CSVContent", sightings.toString());

        // TODO: change to listview layout
        LinearLayout listView = findViewById(R.id.linlay);

        for (final RatSighting sighting: sightings) {

            TextView t = new TextView(this);

            // TODO: this should not be calling toString on the raw array objects
            t.setText(sighting.getCreatedDate() + ":" + sighting.getUniqueKey());

            t.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    set the information for the detail view here
                    Intent intent = new Intent(SightingsListActivity.this, DetailsActivity.class);
                    Bundle b = new Bundle();
                    b.putSerializable("details", sighting);
                    intent.putExtras(b);
                    startActivity(intent);
                }
            });
            listView.addView(t);
        }


    }

}