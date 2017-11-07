package com.example.cooperpellaton.pupperlicks;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.DatePicker;
import android.widget.TextView;
import android.view.*;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.jjoe64.graphview.*;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *  This class creates the functionality behind Rat Tracker's Graph View.
 *  Created by Victoria Joh
 *  Oktober 26, 2017
 *  v. 1
 *
 */

    //TODO: make an async task - working on it
    //TODO: pull the 50 rat sightings for the graph - working on it

    //number of sightings on specific date

public class GraphViewActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    // sightings date range selection stuff
    private DatePickerDialog endDatePickerDialog; // UI
    private DatePickerDialog startDatePickerDialog; // UI
    private DatePicker startDatePicker;
    private DatePicker endDatePicker;
    private Date startDate;
    private Date endDate;

    GraphView graph;

    Context context;
    private List<RatSighting> sightingsList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // make sure to process async first

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        Bundle b = getIntent().getExtras();

        // set up Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar6);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // enable back button
        getSupportActionBar().setTitle("Sightings Graph");

        // set context var
        context = getApplicationContext();

        new GraphViewTask().execute(this);

        // instantiate graph view
        graph = (GraphView) findViewById(R.id.graph);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        // the "correct" way to set a date object (old constructor was deprecated)
        Calendar cal = Calendar.getInstance(); // create calendar object
        cal.set(Calendar.YEAR, year); // set year
        cal.set(Calendar.MONTH, month); // set month (starting at Jan = 0)
        cal.set(Calendar.DAY_OF_MONTH, day); // set day
        Date date = cal.getTime(); // convert to Date object

        // set up dateformatter for debug purposes
        SimpleDateFormat df = new SimpleDateFormat("M/d/yyyy H:mm");

        // determine which date picker was just used: the one for the starting date, or the one
        // for the ending date?
        if(datePicker == startDatePicker) {
            startDate = date; // set the instance variable
            // place date in debug log
            Log.d("PupperLicks/DatePicker", "startDate: " + df.format(startDate));


            // now that we have the start date, get the end date
            endDatePickerDialog = new DatePickerDialog(GraphViewActivity.this, GraphViewActivity.this, 2017, 0, 1);
            // store the reference to the internal DatePicker so we can determine which one was pressed later
            endDatePickerDialog.setTitle("End Date");
            endDatePicker = endDatePickerDialog.getDatePicker();
            endDatePickerDialog.show();
            // display a helpful toast indicating what we need to be selecting
            Toast.makeText(context, "Select end date", Toast.LENGTH_LONG).show();

        } else if (datePicker == endDatePicker) {
            endDate = date; // set the instance variable
            // place date in debug log
            Log.d("PupperLicks/DatePicker", "endDate: " + df.format(endDate));

            // TODO: this is where the item retrieval/filtering needs to be done

            // this should never happen (famous last words...)
        } else {
            throw new IllegalArgumentException("Invalid View passed, cannot parse date.");
        }

        //gets time difference in milliseconds
        long diff = endDate.getTime() - startDate.getTime();
        int daysNumber = (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

        //Might need to try/catch an exception
//        try {
//           if (daysNumber == 0) {
//                Toast.makeText(context, "The date values you have chosen need to span at least 2 days.", Toast.LENGTH_LONG).show();
//
//            }
//        } catch (java.te) {
//
//        }


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.actions_maps_datefilter) {

            // get the start date
            startDatePickerDialog = new DatePickerDialog(GraphViewActivity.this, GraphViewActivity.this, 2015, 0, 1);

            // store the reference to the internal DatePicker so we can determine which one was pressed later
            startDatePicker = startDatePickerDialog.getDatePicker();
            startDatePickerDialog.show();

            // display a helpful toast indicating what we need to be selecting
            Toast.makeText(context, "Select start date", Toast.LENGTH_LONG).show();

            return true; // consume the click event
        }

        return super.onOptionsItemSelected(item);
    }


    public class GraphViewTask extends AsyncTask<Context, Context, Context> {

        @Override
        protected Context doInBackground(Context... contexts) {

            // request list of sightings from the server
            sightingsList = ServerPortal.getFifty();

            // set up placeholder data series... for now
            LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                    new DataPoint(0, 1),
                    new DataPoint(1, 5),
                    new DataPoint(2, 3),
                    new DataPoint(3, 2),
                    new DataPoint(4, 6)
            });

            graph.addSeries(series); // set data set for graph

            return contexts[0];
        }

        @Override
        protected void onPostExecute(final Context context) {
            DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            for (final RatSighting sighting: sightingsList) {
                try {
                    // attempt to get the date from the sighting
                    Date date = format.parse(sighting.getCreatedDate());



                    } catch (java.text.ParseException e) {
                    Log.e("INFO", "Problem parsing info: " + sighting.getCreatedDate());
                }


            }


        }
    }


}