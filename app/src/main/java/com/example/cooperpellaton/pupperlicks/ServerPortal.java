package com.example.cooperpellaton.pupperlicks;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by Cooper Pellaton on 10/24/2017.
 */

public class ServerPortal {
    private static final String HOST = "http://54.158.72.38:5000/";
    private static final String FIFTY = "50_sightings";
    private static final String SIGHTINGS = "sightings";

    // Get all sightings from the server.
    public static JSONArray getAllSightings() {
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(HOST + SIGHTINGS)
                    .build();
            Response response = client.newCall(request).execute();
            return new JSONArray(response.body().string());
        } catch (IOException exception) {}
        catch (JSONException exception) {}

        return new JSONArray();
    }

    public static JSONArray getFifty() {
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(HOST + FIFTY)
                    .build();
            Response response = client.newCall(request).execute();
            JSONArray obj = new JSONObject(response.body().string()).getJSONArray("sightings");
            Log.e("JSON", response.toString());
            return obj;
        } catch (IOException exception) {}
        catch (JSONException exception) {
            Log.e("error",  exception.toString());
        }

        return new JSONArray();
    }

}
