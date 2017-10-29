package com.example.cooperpellaton.pupperlicks;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DatabaseReference;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, OnMarkerClickListener {

    private GoogleMap mMap;
    private DatabaseReference mDatabase;
    private Adapter mAdapter;
    private HashMap<String, RatSighting> sightings;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        sightings = new HashMap<String, RatSighting>();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        new MapSightingsTask().execute(this);
    }


    public class MapSightingsTask extends AsyncTask<Context, Context, Context> {

        LinkedList<RatSighting> rats;

        @Override
        protected Context doInBackground(Context... contexts) {

            JSONArray ratsJSON = ServerPortal.getFifty();
            rats = new LinkedList<>();

            try {
                Log.e("TASK", ratsJSON.toString());
                for (int i = 0; i < ratsJSON.length(); i++) {

                    JSONObject task = ratsJSON.getJSONObject(i);
                    Log.e("TASK", task.toString());
                    rats.add(new RatSighting(
                            task.getString("unique_key"),
                            task.getString("created_date"),
                            task.getString("location_type"),
                            task.getString("incident_zip"),
                            task.getString("incident_address"),
                            task.getString("city"),
                            task.getString("borough"),
                            task.getString("latitude"),
                            task.getString("longitude")
                    ));
                }

                Log.e("SIGHTINGS", "Rat list: " + rats.size());

            } catch (JSONException ignored) {
                Log.e("INFO", "Problem parsing info: " + ignored.toString());
            }
            return contexts[0];
        }

        @Override
        protected void onPostExecute(final Context context) {
            GoogleMap mMap = MapsActivity.this.mMap;
            Marker ratMarker;
            for (final RatSighting sighting: this.rats) {
                MapsActivity.this.sightings.put(sighting.getCreatedDate(), sighting);
                LatLng rat = new LatLng(Double.parseDouble(sighting.getLatitude()), Double.parseDouble(sighting.getLongitude()));
                ratMarker = mMap.addMarker(new MarkerOptions().position(rat).title(sighting.getUniqueKey()));
                ratMarker.setTag(sighting);
            }
            mMap.setOnMarkerClickListener(MapsActivity.this);
        }
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
        LatLng camera = new LatLng(40.70777155363643,-74.01296309970473); //TODO: get location from phone
        mMap.moveCamera(CameraUpdateFactory.newLatLng(camera));
    }

    @Override
    public boolean onMarkerClick(final Marker marker) {
        Intent intent = new Intent(MapsActivity.this, DetailsActivity.class);
        Bundle b = new Bundle();
        RatSighting sighting = (RatSighting) marker.getTag();
        b.putSerializable("details", sighting);
        intent.putExtras(b);
        startActivity(intent);
        // Return false to indicate that we have not consumed the event and that we wish
        // for the default behavior to occur (which is for the camera to move such that the
        // marker is centered and for the marker's info window to open, if it has one).
        return false;
    }
}