package com.example.cooperpellaton.pupperlicks;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DatabaseReference;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private DatabaseReference mDatabase;
    private Adapter mAdapter;
    private List<RatSighting> sightings;
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
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        sightings = readCSVFromAssetFolder();
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //TODO: get individual Latlong for each sighting
        for (final RatSighting sighting: sightings) {
            LatLng rat = new LatLng(40.70777155363643, -74.01296309970473); // PlaceHolder
//            LatLng rat = new LatLng(Double.parseDouble(sighting.getLatitude()), Double.parseDouble(sighting.getLongitude()));
            mMap.addMarker(new MarkerOptions().position(rat).title(sighting.getUniqueKey())); //Currently just says "Unique Key"
        }
        LatLng camera = new LatLng(40.70777155363643,-74.01296309970473);   //TODO: get location from phone
        mMap.moveCamera(CameraUpdateFactory.newLatLng(camera));
    }
}
