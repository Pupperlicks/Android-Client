package com.example.cooperpellaton.pupperlicks;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.DatePicker;

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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, OnMarkerClickListener, DatePickerDialog.OnDateSetListener {

    private GoogleMap mMap;
    private DatabaseReference mDatabase;
    private Adapter mAdapter;
    private HashMap<Date, LinkedList<RatSighting>> sightings;
    private LinkedList chosenDates;
    private Button selectDateBtn;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        sightings = new HashMap<Date, LinkedList<RatSighting>>();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        new MapSightingsTask().execute(this);
        selectDateBtn = (Button) findViewById(R.id.select_date_btn);
    }
    public void onDateSet(DatePicker view, int year, int month, int day) {
        chosenDates.add(new Date(year, month, day));
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
            DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            for (final RatSighting sighting: this.rats) {
                try {
                    Date date = format.parse(sighting.getCreatedDate());
                    if (!MapsActivity.this.sightings.containsKey(date)) {
                        LinkedList<RatSighting> dateList = new LinkedList<>();
                        MapsActivity.this.sightings.put(date, dateList);
                    }
                    MapsActivity.this.sightings.get(date).add(sighting);
                } catch (java.text.ParseException e) {
                    Log.e("INFO", "Problem parsing info: " + sighting.getCreatedDate());
                }

                LatLng rat = new LatLng(Double.parseDouble(sighting.getLatitude()), Double.parseDouble(sighting.getLongitude()));
                ratMarker = mMap.addMarker(new MarkerOptions().position(rat).title(sighting.getUniqueKey()));
                ratMarker.setTag(sighting);
            }
            // TODO: Retrieve dates, filter using hashmap, clear map, display new markers.
            mMap.setOnMarkerClickListener(MapsActivity.this);
            selectDateBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MapsActivity.this.chosenDates = new LinkedList();
                    DatePickerDialog startDay = new DatePickerDialog(context, MapsActivity.this, 2017, 0, 1);
                    startDay.show();
                    DatePickerDialog endDay = new DatePickerDialog(context, MapsActivity.this, 2017, 0, 1);
                    endDay.show();
                };
            });
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