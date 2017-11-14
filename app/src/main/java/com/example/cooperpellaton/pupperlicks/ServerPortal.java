package com.example.cooperpellaton.pupperlicks;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 * This is class is designed to be an easy-to-use way of accessing our backing server.
 *
 * Created by Cooper Pellaton on 10/24/2017.
 */

public class ServerPortal {
    private static final String HOST = "http://54.158.72.38:5000/"; // the mothership
    private static final String FIFTY = "50_sightings";
    private static final String SIGHTINGS = "sightings";
    private static final String RANGE = "range";
    private static final boolean useServer = true;

    public static String getHOST() {
        return HOST;
    }

    public static String getSIGHTINGS() {
        return SIGHTINGS;
    }

    /**
     * This method asks the server to return a list of all rat sightings in its database.
     *
     * @return a list of RatSightings, or null if there was an issue retrieving them
     */
    // Get all sightings from the server.
    static List<RatSighting> getAllSightings() {

        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(HOST + SIGHTINGS)
                    .build();

            // call the server and get the response
            Response response = client.newCall(request).execute();

            return JSONToRatSightings(new JSONArray(response.body().string()));

        } catch (IOException exception) {
            Log.e("SP:IO", exception.toString()); // print to debug log
            return null; // receiving end should understand 'null' means an error took place
        }
        catch (JSONException exception) {
            Log.e("SP:JSON", exception.toString());
            return null;
        }
    }

    /**
     * This method asks the server to return a list of the first 50 rat sightings in the database.
     *
     * @return a list of RatSightings, or null if there was an issue retrieving them
     */
    static List<RatSighting> getFifty() {

        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(HOST + FIFTY)
                    .build();

            Response response = client.newCall(request).execute();
            JSONArray obj = new JSONObject(response.body().string()).getJSONArray("sightings");
            Log.e("JSON", response.toString());

            return JSONToRatSightings(obj);

        } catch (IOException exception) {
            Log.e("SP:IO", exception.toString()); // print to debug log
            return null; // receiving end should understand 'null' means an error took place
        }
        catch (JSONException exception) {
            Log.e("SP:JSON", exception.toString());
            return null;
        }
    }

    /**
     * This method asks the server to return a list of the first 50 rat sightings in the database.
     *
     * @param startDate the earliest date we want sightings from, inclusive
     * @param endDate the last date we want sightings from, inclusive
     * @return a list of RatSightings, or null if there was an issue retrieving them
     */
    // TODO: figure out if server is returning items from exclusive or inclusive date ranges
    static List<RatSighting> getRange(Date startDate, Date endDate) {

        DateFormat df = new SimpleDateFormat("M/d/yyyy"); // TODO: figure out if we actually do need to use trailing zeros here

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");

        // create map to temporarily store the data
        Map<String, String> params = new HashMap<>();

        // convert RatSighting object fields to map which can be converted to JSON
        params.put("start_date", df.format(startDate));
        params.put("end_date", df.format(endDate));

        OkHttpClient client = new OkHttpClient();

        JSONObject outgoing = new JSONObject(params); // generate JSON object from map entries

        RequestBody body = RequestBody.create(JSON, outgoing.toString());

        try {
            // assembly the HTTP request
            Request request = new Request.Builder()
                    .url(HOST + RANGE)
                    .post(body)
                    .addHeader("content-type", "application/json; charset=utf-8")
                    .addHeader("cache-control", "no-cache")
                    .build();

            // call the server and (hopefully) get a response
            Response response = client.newCall(request).execute();

            // text response -> JSONArray
            JSONArray obj = new JSONObject(response.body().string()).getJSONArray("sightings");

            Log.d("JSON", response.toString());

            return JSONToRatSightings(obj);

        } catch (IOException exception) {
            Log.e("SP:IO", exception.toString()); // print to debug log
            return null; // receiving end should understand 'null' means an error took place
        }
        catch (JSONException exception) {
            Log.e("SP:JSON", exception.toString());
            return null;
        }
    }

    static void addReport(RatSighting sighting) {

            MediaType JSON = MediaType.parse("application/json; charset=utf-8");

            // create map to temporarily store the data
            Map<String, String> params = new HashMap<>();

            // convert RatSighting object fields to map which can be converted to JSON
            params.put("intersection_street_1", "");
            params.put("school_name", "");
            params.put("park_borough", "");
            params.put("borough", sighting.getBorough());
            params.put("intersection_street_2", "");
            params.put("bridge_highway_segment", "");
            params.put("school_city", "");
            params.put("descriptor", "");
            params.put("school_zip", "");
            params.put("complaint_type", "");
            params.put("incident_address", sighting.getIncidentAddress());
            params.put("due_date", "");
            params.put("vehicle_type", "");
            params.put("location_type", "");
            params.put("incident_zip", sighting.getIncidentZip());
            params.put("street_name", "");
            params.put("y_coord", "");
            params.put("agency_name", "");
            params.put("cross_street_1", "");
            params.put("school_region", "");
            params.put("school_or_citywide_complaint", "");
            params.put("garage_lot_name", "");
            params.put("resolution", "");
            params.put("school_state", "");
            params.put("school_number", "");
            params.put("taxi_company", "");
            params.put("facility_type", "");
            params.put("landmark", "");
            params.put("community_board", "");
            params.put("school_phone_number", "");
            params.put("cross_street_2", "");
            params.put("agency", "");
            params.put("road_ramp", "");
            params.put("ferry_direction", "");
            params.put("taxi_pick_up_location", "");
            params.put("status", "");
            params.put("x_coord", "");
            params.put("city", sighting.getCity());
            params.put("unique_key", sighting.getUniqueKey());
            params.put("latitude", sighting.getLatitude());
            params.put("location",
                    "(" + sighting.getLatitude() + ", " + sighting.getLongitude() + ")");
            params.put("address_type", "");
            params.put("bridge_highway_name", "");
            params.put("longitude", sighting.getLongitude());
            params.put("created_date", sighting.getCreatedDate());
            params.put("ferry_terminal_name", "");
            params.put("school_address", "");
            params.put("school_not_found", "");
            params.put("school_code", "");
            params.put("park_facility_name", "");
            params.put("bridge_highway_direction", "");

            OkHttpClient client = new OkHttpClient();

            JSONObject outgoing = new JSONObject(params);

            RequestBody body = RequestBody.create(JSON, outgoing.toString());


            Request request = new Request.Builder()
                    .url(HOST + SIGHTINGS)
                    .post(body) // special sauce to make this a POST request
                    .addHeader("content-type", "application/json; charset=utf-8")
                    .addHeader("cache-control", "no-cache")
                    .build();

            // make the request
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.e("response", call.request().body().toString());

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String serverResp = response.body().string();
                    Log.e("response", serverResp);
                }
            });
    }

    /**
     * This method takes care of converting an array of JSON objects into an equivalent list of
     * RatSighting objects, which can then be manipulated.
     *
     * @param arrayJson the JSONArray to be converted to a list of RatSighting objects
     * @return a list of converted RatSighting objects
     */
    private static List<RatSighting> JSONToRatSightings(JSONArray arrayJson) {

        // set up the list we will populate with RatSighting objects
        List<RatSighting> sightingsList = new ArrayList<>();

        try {
            Log.e("TASK", arrayJson.toString());
            for (int i = 0; i < arrayJson.length(); i++) {

                // extract JSON object from array of results
                JSONObject task = arrayJson.getJSONObject(i);
                Log.e("TASK", task.toString());

                // construct RatSighting object from retrieved JSON object
                sightingsList.add(new RatSighting(
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

            Log.e("SIGHTINGS", "Rat list: " + sightingsList.size());

        } catch (JSONException ignored) {
            Log.e("INFO", "Problem parsing info: " + ignored.toString());
        }

        return sightingsList;
    }
}
