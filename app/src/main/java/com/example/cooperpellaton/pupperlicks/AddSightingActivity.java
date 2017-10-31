package com.example.cooperpellaton.pupperlicks;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddSightingActivity extends AppCompatActivity {

    private EditText editTextUniqueKey, editTextCreatedDate, editTextLocationType,
            editTextIncidentZip, editTextIncidentAddress, editTextCity, editTextBorough,
            editTextLatitude, editTextLongitude;

    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sighting);

        // set up ToolBar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);

        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // set up textedits
        editTextUniqueKey = (EditText) findViewById(R.id.editTextUniqueKey);
        editTextCreatedDate = (EditText) findViewById(R.id.editTextCreatedDate);
        editTextLocationType = (EditText) findViewById(R.id.editTextLocationType);
        editTextIncidentZip = (EditText) findViewById(R.id.editTextIncidentZip);
        editTextIncidentAddress = (EditText) findViewById(R.id.editTextIncidentAddress);
        editTextCity = (EditText) findViewById(R.id.editTextCity);
        editTextBorough = (EditText) findViewById(R.id.editTextBorough);
        editTextLatitude = (EditText) findViewById(R.id.editTextLatitude);
        editTextLongitude = (EditText) findViewById(R.id.editTextLongitude);

        // set up button
        btnSave = (Button) findViewById(R.id.button2);

        // set up location services so we can get current lat and long
        LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        try {
            Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            if(location != null) {

                double longitude = location.getLongitude();
                double latitude = location.getLatitude();

                editTextLatitude.setText(String.valueOf(latitude));
                editTextLongitude.setText(String.valueOf(longitude));
            }

        } catch (SecurityException e) {

        }


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // verify inputted data is alright
                // TODO: add more than just rudimentary input checking

                // check if fields aren't empty
                if ((editTextUniqueKey.getText().length() != 0)
                        && (editTextLocationType.getText().length() != 0)
                        && (editTextIncidentZip.getText().length() != 0)
                        && (editTextIncidentAddress.getText().length() != 0)
                        && (editTextCity.getText().length() != 0)
                        && (editTextBorough.getText().length() != 0)
                        && (editTextLatitude.getText().length() != 0)
                        && (editTextLongitude.getText().length() != 0)
                        ) {

                    ServerPortal sp = new ServerPortal(); // instantiate server access object

                    // take care of considerations for timestamps
                    Calendar c = Calendar.getInstance();
                    // in the CSV file, they're sorted like so: 9/4/2015 0:00
                    SimpleDateFormat df = new SimpleDateFormat("M/d/yyyy H:mm");

                    // build rat sighting object and send server add request

                    sp.addReport(new RatSighting(
                            editTextUniqueKey.getText().toString(),
                            df.format(c.getTime()), // gets current time and formats it
                            editTextLocationType.getText().toString(),
                            editTextIncidentZip.getText().toString(),
                            editTextIncidentAddress.getText().toString(),
                            editTextCity.getText().toString(),
                            editTextBorough.getText().toString(),
                            editTextLatitude.getText().toString(),
                            editTextLongitude.getText().toString())
                    );
                } else {
                    // construct toast and display it
                    Toast.makeText(getApplicationContext(), "No fields must be left blank", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // get bundle (if any)
        Bundle b = getIntent().getExtras();
        RatSighting sighting;

        // if there's a bundle we're editing, if not we're adding
        boolean isEditing = (b != null);

        // if we are in fact editing, populate the editTexts with the existing data
        if (isEditing) {
            sighting = (RatSighting) b.getSerializable("details"); // de-serialize the RatSighting object

            // populate textedits with values
            editTextUniqueKey.setText(sighting.getUniqueKey());
            editTextCreatedDate.setText(sighting.getCreatedDate());
            editTextLocationType.setText(sighting.getLocationType());
            editTextIncidentZip.setText(sighting.getIncidentZip());
            editTextIncidentAddress.setText(sighting.getIncidentAddress());
            editTextCity.setText(sighting.getCity());
            editTextBorough.setText(sighting.getBorough());
            editTextLatitude.setText(sighting.getLatitude());
            editTextLongitude.setText(sighting.getLongitude());
        }
    }

}
