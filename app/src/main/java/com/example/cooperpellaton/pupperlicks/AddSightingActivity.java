package com.example.cooperpellaton.pupperlicks;

import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ServerPortal sp = new ServerPortal();
                // build rat sighting object and send server add request
                sp.addReport(new RatSighting(
                        editTextUniqueKey.getText().toString(),
                        editTextCreatedDate.getText().toString(),
                        editTextLocationType.getText().toString(),
                        editTextIncidentZip.getText().toString(),
                        editTextIncidentAddress.getText().toString(),
                        editTextCity.getText().toString(),
                        editTextBorough.getText().toString(),
                        editTextLatitude.getText().toString(),
                        editTextLongitude.getText().toString()));
            }
        });
    }

}
