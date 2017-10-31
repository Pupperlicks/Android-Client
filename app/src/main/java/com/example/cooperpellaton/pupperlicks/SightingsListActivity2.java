package com.example.cooperpellaton.pupperlicks;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class SightingsListActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sightings_list2);
        // getActionBar().setDisplayHomeAsUpEnabled(true);

        // set up ToolBar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onResume() {
        super.onResume();
        new SightingsTask().execute(this); // create SightingsTask() class
    }

    public class SightingsTask extends AsyncTask<Context, Context, Context> {

        ArrayList<RatSighting> rats;

        @Override
        protected Context doInBackground(Context... contexts) {

            JSONArray ratsJSON = ServerPortal.getFifty();
            rats = new ArrayList<>();

            try {
                Log.e("TASK", ratsJSON.toString());
                for (int i = 0; i < ratsJSON.length(); i++) {

                    // extract JSON object from array of results
                    JSONObject task = ratsJSON.getJSONObject(i);
                    Log.e("TASK", task.toString());

                    // construct RatSighting object from retrieved JSON object
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

//            LinearLayout listView = (LinearLayout) findViewById(R.id.linlay);


            SightingsListAdapter sightingsAdapter =
                    new SightingsListAdapter(context, rats);

            ListView listView = (ListView) findViewById(R.id.sightingsList);
//            listView.removeAllViews(); // TODO: ask why this is needed
            listView.setAdapter(sightingsAdapter);

//            LayoutInflater inflater = (LayoutInflater) context.getSystemService(
//                    Context.LAYOUT_INFLATER_SERVICE );
//
//            for (final RatSighting sighting: rats) {
//                TextView t = new TextView(context);
//
//                t.setText(sighting.getCreatedDate() + ":" + sighting.getUniqueKey());
//
//                t.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        // set the information for the detail view here
//                        Intent intent = new Intent(SightingsListActivity.this, DetailsActivity.class);
//                        Bundle b = new Bundle();
//                        b.putSerializable("details", sighting);
//                        intent.putExtras(b);
//                        startActivity(intent);
//                    }
//                });
//                listView.addView(t);
//            }

            // set up Floating Action Button
            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floatingActionButton2);

            // set FAB listener
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //
                    Intent intent = new Intent(SightingsListActivity2.this, AddSightingActivity.class);
                    startActivity(intent);
                }
            });
        }

    }
}
