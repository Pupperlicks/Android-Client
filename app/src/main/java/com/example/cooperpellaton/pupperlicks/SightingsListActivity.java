package com.example.cooperpellaton.pupperlicks;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Adapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.FirebaseApp;
//import com.google.firebase.firebase_core.*;
import com.google.firebase.database.*;
import com.firebase.ui.*;
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


    private List<String[]> readCVSFromAssetFolder(){
        List<String[]> csvLine = new ArrayList<>();
        String[] content;
        try {
            InputStream inputStream = getAssets().open("Rat_Sightings_less.csv");
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String line; // sets up empty string
            while((line = br.readLine()) != null){
                content = line.split(",");
                csvLine.add(content);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("Error", e.toString());
        }
        return csvLine;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_sightings_list);
        List<String[]> csvContent = readCVSFromAssetFolder();
        Log.e("CSVContent", csvContent.toString());
        LinearLayout listView = findViewById(R.id.linlay);
        for (final String[] array: csvContent) {
            TextView t = new TextView(this);
            // TODO: this should not be calling toString on the raw array objects
            t.setText(csvContent.get(0).toString() + ":" + csvContent.get(1).toString());
            t.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    set the information for the detail view here
                    Intent intent = new Intent(SightingsListActivity.this, DetailsActivity.class);
                    Bundle b = new Bundle();
                    b.putStringArray("details", array);
                    intent.putExtras(b);
                    startActivity(intent);
                }
            });
            listView.addView(t);
        }


    }

}