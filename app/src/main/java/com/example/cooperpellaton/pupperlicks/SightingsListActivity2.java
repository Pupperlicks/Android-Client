package com.example.cooperpellaton.pupperlicks;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import org.json.JSONArray;

import java.util.ArrayList;
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

        // TODO: change to something less silly
        getSupportActionBar().setTitle("Rat Sightings");
        // enable "up" button
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        new SightingsTask().execute(this); // create SightingsTask() class
    }

    public class SightingsTask extends AsyncTask<Context, Context, Context> {

        List<RatSighting> sightingsList;

        @Override
        protected Context doInBackground(Context... contexts) {

            sightingsList = ServerPortal.getFifty();

            return contexts[0]; // return the context we need
        }

        @Override
        protected void onPostExecute(final Context context) {

//            LinearLayout listView = (LinearLayout) findViewById(R.id.linlay);

            ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar2);
            SightingsListAdapter sightingsAdapter =
                    new SightingsListAdapter(context, sightingsList);

            ListView listView = (ListView) findViewById(R.id.sightingsList);
//            listView.removeAllViews(); // TODO: ask why this is needed
            listView.setAdapter(sightingsAdapter);
            progressBar.setVisibility(View.INVISIBLE);

//            LayoutInflater inflater = (LayoutInflater) context.getSystemService(
//                    Context.LAYOUT_INFLATER_SERVICE );
//
//            for (final RatSighting sighting: sightingsList) {
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
                    startActivity(new Intent(SightingsListActivity2.this, AddSightingActivity.class));
                }
            });
        }

    }
}
