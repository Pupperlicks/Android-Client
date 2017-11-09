package com.example.cooperpellaton.pupperlicks;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Class designed to be an adapter from a a list of RatSightings to an Android ListView
 * Created by blane on 10/26/2017.
 */

class SightingsListAdapter extends ArrayAdapter<RatSighting> {

    Context context;

    // constructor
    SightingsListAdapter(Context context, List<RatSighting> sightingArrayList) {
        super(context, 0, sightingArrayList);
        this.context = context;
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
        ImageView identiconView = convertView.findViewById(R.id.list_identiconview);

        ConstraintLayout llSightingItem = convertView.findViewById(R.id.llSightingItem);

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

        // calculate the amount of pixels
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int convertedPixels = (int)((64 * displayMetrics.density) + 0.5);

        // generate and set identicon
        identiconView.setImageDrawable(new NoBgClassicIdenticonDrawable(
                convertedPixels,
                convertedPixels,
                sighting.hashCode() + 1)
        );

        // Return the completed view to render on screen
        return convertView;
    }

}
