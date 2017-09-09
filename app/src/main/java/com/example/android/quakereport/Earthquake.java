package com.example.android.quakereport;

import android.widget.ListView;

/**
 * Created by JOSH on 12-04-2017.
 */



public class Earthquake {

    private String mMagnitude;

    private String mLocation;

    private String mOffsetlocation;

    private   String mPrimarylocation;

    private long mTimeInMIlliseconds;

    private ListView mListview ;

    private String mUrl;

    public Earthquake(String magnitude, String location, long time,String url) {

        mMagnitude = magnitude;
        mLocation = location;
        mTimeInMIlliseconds = time;
        mUrl = url;
    }

    public String getUrl() {
        return mUrl;
    }

    private void splitString(){

        String string = mLocation;
        String[] parts = string.split("(?<=of)",2);
        mOffsetlocation = parts[0];
        mPrimarylocation = parts[1];
    }

    public String getMagnitude() { return mMagnitude;}

    public  String getLocation(){
        splitString();
        return mPrimarylocation;}
    public String getmOffsetlocation(){
        splitString();
        return mOffsetlocation;
    }

    public long getmTimeInMIlliseconds(){return mTimeInMIlliseconds;}


}
