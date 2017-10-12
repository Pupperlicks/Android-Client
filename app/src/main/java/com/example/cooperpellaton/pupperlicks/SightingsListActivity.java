package com.example.cooperpellaton.pupperlicks;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Adapter;
import android.widget.ListView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firebase_core.*;
import com.google.firebase.database.*;
import com.firebase.ui.*;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mListView = (ListView) findViewById(R.id.ListView);

        Query query = FirebaseDatabase.getInstance()
                .getReference()
                .child("rats")
                .limitToLast(50);

        FirebaseListOptions<RatSighting> options = new FirebaseListOptions.Builder<RatSighting>()
                .setQuery(query, RatSighting.class)
                .build();

        FirebaseListAdapter<RatSighting> adapter = new FirebaseListAdapter<RatSighting>(options) {
            @Override
            protected void populateView(View v, RatSighting model, int position) {
                LayoutInflater inflater =
                        (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View newView = inflater.inflate(R.layout.sightings_list, (ListView)v, true);
                ListView castV = (ListView) v;
                castV.addView(newView);
            }
        };



    }
}
