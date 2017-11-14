package com.example.cooperpellaton.pupperlicks;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.os.AsyncTask;
import android.view.LayoutInflater;


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
    protected void onResume() { // when activity is visible again
        super.onResume();
        new SightingsTask().execute(this); // create SightingsTask() class
    }

    public class SightingsTask extends AsyncTask<Context, Context, Context> {

        List<RatSighting> sightingsList;

        @Override
        protected Context doInBackground(Context... contexts) {

            sightingsList = ServerPortalUtilites.getFifty();

            return contexts[0];
        }

        @Override
        protected void onPostExecute(final Context context) {

            LinearLayout listView = findViewById(R.id.linlay);
            listView.removeAllViews();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE );
            for (final RatSighting sighting: sightingsList) {
                TextView t = new TextView(context);

                t.setText(sighting.getCreatedDate() + ":" + sighting.getUniqueKey());

                t.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // set the information for the detail view here
                        Intent intent = new Intent(SightingsListActivity.this,
                                DetailsActivity.class);
                        Bundle b = new Bundle();
                        b.putSerializable("details", sighting);
                        intent.putExtras(b);
                        startActivity(intent);
                    }
                });
                listView.addView(t);
            }

            // instantiate floating action button
            FloatingActionButton fab = new FloatingActionButton(context);

            // set listener
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //
                    Intent intent = new Intent(SightingsListActivity.this,
                            AddSightingActivity.class);
                    startActivity(intent);
                }
            });
        }

    }

}