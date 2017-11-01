package com.sctech.falcon_e_book;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import static com.sctech.falcon_e_book.R.id.maturity;

/**
 * Created by miketsai on 10/31/2017.
 */

public class BooksAdapter extends ArrayAdapter<Books> {

    BooksAdapter(Context context, List<Books> booksList) {
        super (context, 0, booksList);
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item_book, parent, false);
        }

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.


        // Set the color on the magnitude circle
        Books currentBookList = getItem(position);

        TextView maturityView = (TextView) listItemView.findViewById(maturity);
        /*
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeView.getBackground();
        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getMagnitude());
        int rColor = ContextCompat.getColor(getContext(), magnitudeColor);
        magnitudeCircle.setColor(rColor);
        */
        maturityView.setText(currentBookList.getMaturity());

        // Get the original location string from the Earthquake object,
        // which can be in the format of "5km N of Cairo, Egypt" or "Pacific-Antarctic Ridge".
        /*
        String originalLocation = currentEarthquake.getLocation();

        // If the original location string (i.e. "5km N of Cairo, Egypt") contains
        // a primary location (Cairo, Egypt) and a location offset (5km N of that city)
        // then store the primary location separately from the location offset in 2 Strings,
        // so they can be displayed in 2 TextViews.
        String primaryLocation;
        String locationOffset;

        // Check whether the originalLocation string contains the " of " text
        if (originalLocation.contains(LOCATION_SEPARATOR)) {
            // Split the string into different parts (as an array of Strings)
            // based on the " of " text. We expect an array of 2 Strings, where
            // the first String will be "5km N" and the second String will be "Cairo, Egypt".
            String[] parts = originalLocation.split(LOCATION_SEPARATOR);
            // Location offset should be "5km N " + " of " --> "5km N of"
            locationOffset = parts[0] + LOCATION_SEPARATOR;
            // Primary location should be "Cairo, Egypt"
            primaryLocation = parts[1];
        } else {
            // Otherwise, there is no " of " text in the originalLocation string.
            // Hence, set the default location offset to say "Near the".
            locationOffset = getContext().getString(R.string.near_the);
            // The primary location will be the full location string "Pacific-Antarctic Ridge".
            primaryLocation = originalLocation;
        }

        // Find the TextView with view ID location
        TextView primaryLocationView = (TextView) listItemView.findViewById(R.id.primary_location);
        // Display the location of the current earthquake in that TextView
        primaryLocationView.setText(primaryLocation);

        // Find the TextView with view ID location offset
        TextView locationOffsetView = (TextView) listItemView.findViewById(R.id.location_offset);
        // Display the location offset of the current earthquake in that TextView
        locationOffsetView.setText(locationOffset);
        */

        TextView authorView = (TextView) listItemView.findViewById(R.id.author);
        /*
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeView.getBackground();
        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getMagnitude());
        int rColor = ContextCompat.getColor(getContext(), magnitudeColor);
        magnitudeCircle.setColor(rColor);
        */
        authorView.setText(currentBookList.getAuthor());

        TextView titleView = (TextView) listItemView.findViewById(R.id.title);
        titleView.setText(currentBookList.getTitle());

        TextView dateView = (TextView) listItemView.findViewById(R.id.date);
        dateView.setText(currentBookList.getDate());

        return listItemView;
    }
}
