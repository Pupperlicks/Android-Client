package com.example.cooperpellaton.pupperlicks;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.firebase.FirebaseApp;
import com.google.firebase.firebase_core.*;
import com.google.firebase.database.*; // TODO: only import what's needed

import java.util.ArrayList;
import java.util.List;

/**
 * Activity designed to retrieve rat sightings from the backend and display them as a list to the
 * user.
 */

public class SightingsListActivity extends AppCompatActivity {

//   private DatabaseReference database;
    private Firebase mFirebaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sightings_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                // TODO: should launch add sighting activity ( > M6)
            }
        });

        // TODO: get ratSighting objects by calling firebase
        // set up Firebase database reference

        // TODO: set up list item object/thing
        List<SightingDetails> = new ArrayList<>(); // set up container for entries

        // TODO: iterate through objects and add to list view
        // retrieve entries from server

        // TODO: hood up list view items to go to corresponding detailView
    }
}
