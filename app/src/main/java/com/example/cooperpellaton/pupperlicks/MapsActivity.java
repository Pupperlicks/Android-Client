package com.example.cooperpellaton.pupperlicks;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Adapter;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DatabaseReference;

import java.util.LinkedList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private DatabaseReference mDatabase;
    private Adapter mAdapter;
    private List<RatSighting> rats;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // Place Holder
        rats = new LinkedList<RatSighting>();
        rats.add(new RatSighting("","","","","","","","",""));
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //TODO: get individual LatLng for each sighting
        for (final RatSighting sighting: rats) {
            LatLng rat = new LatLng(40.70777155363643, -74.01296309970473); // PlaceHolder
            mMap.addMarker(new MarkerOptions().position(rat).title("Place Holder"));
//            LatLng rat = new LatLng(Double.parseDouble(sighting.getLatitude()), Double.parseDouble(sighting.getLongitude()));
//            mMap.addMarker(new MarkerOptions().position(rat).title(sighting.getUniqueKey()));
        }
        LatLng camera = new LatLng(40.70777155363643,-74.01296309970473); //TODO: get location from phone
        mMap.moveCamera(CameraUpdateFactory.newLatLng(camera));
    }
}