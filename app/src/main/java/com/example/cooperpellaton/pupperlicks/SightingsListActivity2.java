package com.example.cooperpellaton.pupperlicks;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

/**
 * The sightings list which shows a list of all sightings after querying the server.
 */
public class SightingsListActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sightings_list2);
        // getActionBar().setDisplayHomeAsUpEnabled(true);

        // set up ToolBar
        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Rat Sightings");
        // enable "up" button
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // set up Floating Action Button
        FloatingActionButton fab = findViewById(R.id.floatingActionButton2);

        // set FAB listener
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SightingsListActivity2.this, AddSightingActivity.class));
            }
        });
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

            sightingsList = ServerPortalUtilites.getFifty();

            return contexts[0]; // return the context we need
        }

        @Override
        protected void onPostExecute(final Context context) {

//            LinearLayout listView = (LinearLayout) findViewById(R.id.linlay);

            ProgressBar progressBar = findViewById(R.id.progressBar2);

            if(sightingsList != null) {
                ListAdapter sightingsAdapter =
                        new SightingsListAdapter(context, sightingsList);

                ListView listView = findViewById(R.id.sightingsList);
//            listView.removeAllViews(); // TODO: ask why this is needed
                listView.setAdapter(sightingsAdapter);
            } else {
                Toast.makeText(context,
                        "There was an error retrieve the rat sightings.",
                        Toast.LENGTH_LONG).show();
            }

            progressBar.setVisibility(View.INVISIBLE); // make the progress spinner disappear
        }

    }
}
