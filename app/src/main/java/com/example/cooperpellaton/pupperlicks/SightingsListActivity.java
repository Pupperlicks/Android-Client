package com.example.cooperpellaton.pupperlicks;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Activity designed to retrieve rat sightings from the backend and display them as a list to the
 * user.
 */

public class SightingsListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_sightings_list);
    }

    @Override
    protected void onResume() {
        super.onResume();
        new SightingsTask().execute(this);
    }

    public class SightingsTask extends AsyncTask<Context, Context, Context> {

        List<RatSighting> rats;

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

            LinearLayout listView = (LinearLayout) findViewById(R.id.linlay);
            listView.removeAllViews();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE );
            for (final RatSighting sighting: rats) {
                TextView t = new TextView(context);

                t.setText(sighting.getCreatedDate() + ":" + sighting.getUniqueKey());

                t.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // set the information for the detail view here
                        Intent intent = new Intent(SightingsListActivity.this, DetailsActivity.class);
                        Bundle b = new Bundle();
                        b.putSerializable("details", sighting);
                        intent.putExtras(b);
                        startActivity(intent);
                    }
                });
                listView.addView(t);
            }
        }

    }

}