package com.example.cooperpellaton.pupperlicks;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Class designed to be an adapter from a a list of RatSightings to an Android ListView
 * Created by blane on 10/26/2017.
 */

class SightingsListAdapter extends ArrayAdapter<RatSighting> {

    // constructor
    SightingsListAdapter(Context context, ArrayList<RatSighting> sightingArrayList) {
        super(context, 0, sightingArrayList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        RatSighting sighting = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.rat_sighting_list_item, parent, false);
        }
        // Lookup view for data population
        TextView tvUniqueKey = (TextView) convertView.findViewById(R.id.tvUniqueKey);
        TextView tvCreatedDate = (TextView) convertView.findViewById(R.id.tvCreatedDate);

        LinearLayout llSightingItem = (LinearLayout) convertView.findViewById(R.id.llSightingItem);

        // Cache user object inside the button using `setTag`
        llSightingItem.setTag(sighting);

        // Attach the click event handler
        llSightingItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Access user from within the tag
                RatSighting sighting = (RatSighting) view.getTag();
                // Do what you want here...
                // set the information for the detail view here
                Intent intent = new Intent(getContext(), DetailsActivity.class);
                Bundle b = new Bundle();
                b.putSerializable("details", sighting);
                intent.putExtras(b);

                // start the details activity
                getContext().startActivity(intent);
            }
        });

        // Populate the data into the template view using the data object
        tvUniqueKey.setText(sighting.getUniqueKey());
        tvCreatedDate.setText(sighting.getCreatedDate());
        // Return the completed view to render on screen
        return convertView;
    }

}
