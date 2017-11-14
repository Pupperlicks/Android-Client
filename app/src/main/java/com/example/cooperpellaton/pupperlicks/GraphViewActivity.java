package com.example.cooperpellaton.pupperlicks;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

/**
 *  This class creates the functionality behind Rat Tracker's Graph View.
 *  Created by Victoria Joh
 *  October 26, 2017
 *  v. 1
 *
 */

    //number of sightings on specific date

public class GraphViewActivity extends AppCompatActivity
        implements DatePickerDialog.OnDateSetListener {

    private DatePicker startDatePicker;
    private DatePicker endDatePicker;
    private Date startDate;
    private Date endDate;

    private GraphView graph;

    private Context context;
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

        new GraphViewTask().execute(this); // call AsyncTask

        // instantiate graph view
        graph = findViewById(R.id.graph);

        // the "correct" way to set a date object (old constructor was deprecated)
        Calendar cal = Calendar.getInstance(); // create calendar object

        int year = 2015;
        cal.set(Calendar.YEAR, year); // set to current year
        cal.set(Calendar.MONTH, 0); // set month (starting at Jan = 0)
        int day = 31;
        cal.set(Calendar.DAY_OF_MONTH, day); // set day

        endDate = cal.getTime(); // now

        cal.add(Calendar.MONTH, -1);
        startDate = cal.getTime();
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
            DatePickerDialog endDatePickerDialog = new DatePickerDialog(GraphViewActivity.this,
                    GraphViewActivity.this, year, 0, 1);
            // store the reference to the internal DatePicker so we
            // can determine which one was pressed later
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
            new GraphViewTask().execute(this); // call AsyncTask

            // this should never happen (famous last words...)
        } else {
            throw new IllegalArgumentException("Invalid View passed, cannot parse date.");
        }

        //gets time difference in milliseconds
//        long diff = endDate.getTime() - startDate.getTime();
//        int daysNumber = (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

        //Might need to try/catch an exception
//        try {
//           if (daysNumber == 0) {
//                Toast.makeText(context,
// "The date values you have chosen need to span at least 2 days.", Toast.LENGTH_LONG).show();
//
//            }
//        } catch (java.te) {
//
//        }


    }


    public class GraphViewTask extends AsyncTask<Context, Context, Context> {

        @Override
        protected Context doInBackground(Context... contexts) {

            if((startDate == null) || (endDate == null)) { // if date ranges haven't been set

                sightingsList = ServerPortalUtilites.getFifty(); // get fifty earliest sightings

            } else { // otherwise, use the date ranges that have been set to query the server

                sightingsList = ServerPortalUtilites.getRange(startDate, endDate);
            }
            return contexts[0];
        }

        @Override
        protected void onPostExecute(final Context context) {

            if (sightingsList == null) {
                return;
            }

            DateFormat format = new SimpleDateFormat("M/d/yyyy");

            // note: start date should be inclusive, end date exclusive
//            int numColumns = (int) TimeUnit.DAYS.convert(startDate.getTime() - endDate.getTime(),
//                  TimeUnit.MILLISECONDS);

            // create an array of data points with the size as the number of days in the range
            Map<Date, Integer> map = new HashMap<>();

            // main loop that will convert the list of sightings to graph points
            for (final RatSighting sighting: sightingsList) {
                try {
                    // attempt to get the date from the sighting
                    Date date = format.parse(sighting.getCreatedDate());

                    if (map.containsKey(date)) {
                        int oldCount = map.get(date);
                        map.put(date, oldCount + 1);
                    } else {
                        map.put(date, 1);
                    }

                } catch (java.text.ParseException e) {
                    Log.e("INFO", "Problem parsing info: " + sighting.getCreatedDate());
                }
            }

            // set up placeholder data series... for now
//            LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
//                    new DataPoint(0, 1),
//                    new DataPoint(1, 5),
//                    new DataPoint(2, 3),
//                    new DataPoint(3, 2),
//                    new DataPoint(4, 6)
//            });

            DataPoint[] dataPoints = new DataPoint[map.size()];

            int counter = 0;

            for (Date entry : map.keySet()) {
                dataPoints[counter] = new DataPoint(entry.getTime(), map.get(entry));
                counter++;
            }

            LineGraphSeries<DataPoint> series = new LineGraphSeries<>(dataPoints);

            graph.removeAllSeries();
            graph.addSeries(series); // set data set for graph

            // set date label formatter
           // graph.getGridLabelRenderer().setLabelFormatter(
            // new DateAsXAxisLabelFormatter(context));
            graph.getGridLabelRenderer().setNumHorizontalLabels(3); // only 4 because of the space
            series.setTitle("Sightings per day");

//            // set manual x bounds to have nice steps
//            graph.getViewport().setMinX(startDate.getTime());
//            graph.getViewport().setMaxX(endDate.getTime());
//            graph.getViewport().setXAxisBoundsManual(true);

            // as we use dates as labels, the human rounding to nice readable numbers
            // is not necessary
            graph.getGridLabelRenderer().setHumanRounding(false);

        }
    }


}