package com.example.android.quakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.example.android.quakereport.R.id.magnitude;


/**
 * Created by JOSH on 12-04-2017.
 */

public class EarthquakeAdapter extends ArrayAdapter {


    private  static final String LOCATION_SEPARATOR =" of ";

    public EarthquakeAdapter( Context context, List<Earthquake> earthquakes) {
        super(context, 0, earthquakes);

    }

    private  String formatDate(Date dateObject){

        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);

    }

    private String formatTime(Date dateObject){

        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    private String formatMagnitude(double magnitude){
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);

    }

    private int getMagnitudeColor(double magnitude){
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
            case 6: 
                magnitudeColorResourceId = R.color.magnitude6;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
                        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);

    }



    @NonNull
    @Override
    public View getView(int position,  View convertView, @NonNull ViewGroup parent) {




        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.earthquake_list_item, parent, false);
        }

        Earthquake currentEarthquake = (Earthquake) getItem(position);



        TextView magnitudeView = (TextView) listItemView.findViewById(magnitude);

        String formattedMagnitude = formatMagnitude(Double.parseDouble(currentEarthquake.getMagnitude()));

        magnitudeView.setText(formattedMagnitude);

        GradientDrawable magnitudeCircle = null;
         magnitudeCircle = (GradientDrawable) magnitudeView.getBackground();

         int magnitudeColor = getMagnitudeColor(Double.parseDouble(currentEarthquake.getMagnitude()));

        magnitudeCircle.setColor(magnitudeColor);

        String originalLocation = currentEarthquake.getLocation();

        String primaryLocation;
        String locationOffset;

        if (originalLocation.contains(LOCATION_SEPARATOR)){

            String[] parts = originalLocation.split(LOCATION_SEPARATOR);

            locationOffset = parts[0] + LOCATION_SEPARATOR;

            primaryLocation = parts[1];
        }
        else{
            locationOffset = getContext().getString(R.string.near_the);

            primaryLocation = originalLocation;
        }

        TextView offsetLocationView = (TextView) listItemView.findViewById(R.id.location_offset);
        offsetLocationView.setText(locationOffset);

        TextView primarylocationView = (TextView) listItemView.findViewById(R.id.location);
        primarylocationView.setText(primaryLocation);


        Date dateObject = new Date(currentEarthquake.getmTimeInMIlliseconds());

        TextView dateView = (TextView) listItemView.findViewById(R.id.date);
        String formattedDate = formatDate(dateObject);
        dateView.setText(formattedDate);

        TextView timeView = (TextView) listItemView.findViewById(R.id.time);
        String formattedTime = formatTime(dateObject);
        timeView.setText(formattedTime);

        return listItemView;
    }
}
