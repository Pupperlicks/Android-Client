package com.example.cooperpellaton.pupperlicks;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.DatePicker;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DatabaseReference;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, OnMarkerClickListener, DatePickerDialog.OnDateSetListener {

    private GoogleMap mMap;
    private DatabaseReference mDatabase;
    private Adapter mAdapter;
    private HashMap<Date, LinkedList<RatSighting>> sightings;

    private List<RatSighting> sightingsList;

    private DatePicker startDatePicker;
    private DatePicker endDatePicker;
    private Date startDate;
    private Date endDate;

    private DateFormat df;

    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

//        GoogleMap mMap = MapsActivity.this.mMap;

        sightings = new HashMap<Date, LinkedList<RatSighting>>();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);

        // set up Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar5);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // enable back button
        getSupportActionBar().setTitle("Sightings Map");

        // set context var
        context = getApplicationContext();

        df = new SimpleDateFormat("M/d/yyyy H:mm");

        // start asynchronous task that asks the server for sightings
        new MapSightingsTask().execute(this);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        // the "correct" way to set a date object (old constructor was deprecated)
        Calendar cal = Calendar.getInstance(); // create calendar object
        cal.set(Calendar.YEAR, year); // set year
        cal.set(Calendar.MONTH, month); // set month (starting at Jan = 0)
        cal.set(Calendar.DAY_OF_MONTH, day); // set day
        Date date = cal.getTime(); // convert to Date object

        // determine which date picker was just used: the one for the starting date, or the one
        // for the ending date?
        if(view == startDatePicker) {
            startDate = date; // set the instance variable
            // place date in debug log
            Log.d("PupperLicks/DatePicker", "startDate: " + df.format(startDate));


            // now that we have the start date, get the end date
            DatePickerDialog endDatePickerDialog = new DatePickerDialog(MapsActivity.this, MapsActivity.this, 2017, 0, 1);
            // store the reference to the internal DatePicker so we can determine which one was pressed later
            endDatePickerDialog.setTitle("End Date");
            endDatePicker = endDatePickerDialog.getDatePicker();
            endDatePickerDialog.show();
            // display a helpful toast indicating what we need to be selecting
            Toast.makeText(context, "Select end date", Toast.LENGTH_LONG).show();

        } else if (view == endDatePicker) {
            endDate = date; // set the instance variable
            // place date in debug log
            Log.d("PupperLicks/DatePicker", "endDate: " + df.format(endDate));

            // TODO: this is where the item retrieval/filtering needs to be done
            // start asynchronous task that asks the server for sightings (again)
            new MapSightingsTask().execute(this);

        // this should never happen (famous last words...)
        } else {
            throw new IllegalArgumentException("Invalid View passed, cannot parse date.");
        }
    }

    // toolbar items
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_maps, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.actions_maps_datefilter) {

            // get the start date
            DatePickerDialog startDatePickerDialog = new DatePickerDialog(MapsActivity.this, MapsActivity.this, 2015, 0, 1);

            // store the reference to the internal DatePicker so we can determine which one was pressed later
            startDatePicker = startDatePickerDialog.getDatePicker();
            startDatePickerDialog.show();

            // display a helpful toast indicating what we need to be selecting
            Toast.makeText(context, "Select start date", Toast.LENGTH_LONG).show();

            return true; // consume the click event
        }

        return super.onOptionsItemSelected(item);
    }


    public class MapSightingsTask extends AsyncTask<Context, Context, Context> {

        @Override
        protected Context doInBackground(Context... contexts) {

            if((startDate == null) || (endDate == null)) { // if date ranges haven't been set
                sightingsList = ServerPortal.getFifty(); // get fifty earliest sightings
            } else { // otherwise, use the date ranges that have been set
                sightingsList = ServerPortal.getRange(startDate, endDate);
            }

            return contexts[0];
        }

        @Override
        protected void onPostExecute(final Context context) {

            Marker ratMarker;

            mMap.clear(); // clear any existing markers

            for (final RatSighting sighting: sightingsList) {
                try {
                    // attempt to get the date from the sighting
                    Date date = df.parse(sighting.getCreatedDate());

                    if (!MapsActivity.this.sightings.containsKey(date)) {
                        LinkedList<RatSighting> dateList = new LinkedList<>();
                        MapsActivity.this.sightings.put(date, dateList);
                    }

                    MapsActivity.this.sightings.get(date).add(sighting);
                } catch (java.text.ParseException e) {
                    Log.e("INFO", "Problem parsing info: " + sighting.getCreatedDate());
                }

                // evidently some rat sightings don't have latitude/longitude
                if ((sighting.getLatitude().length() != 0) && (sighting.getLongitude().length() != 0)) {

                    // parse the lat and long
                    LatLng rat = new LatLng(
                            Double.parseDouble(sighting.getLatitude()),
                            Double.parseDouble(sighting.getLongitude())
                    );

                    ratMarker = mMap.addMarker(new MarkerOptions().position(rat).title(sighting.getUniqueKey()));
                    ratMarker.setTag(sighting);
                }
            }

            mMap.setOnMarkerClickListener(MapsActivity.this); // listener handle is in this class
        }
    }

    private void addRangeMarkers() {

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
        // set location

        //TODO: get location from phone
        mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(40.7128,-74.0060))); // move the camera to NYC
        mMap.moveCamera(CameraUpdateFactory.zoomTo(10)); // zoom camera in
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