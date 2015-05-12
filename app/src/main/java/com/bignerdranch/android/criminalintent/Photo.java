package com.bignerdranch.android.criminalintent;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Tigran on 5/12/2015.
 */
public class Photo {
    public static final String JSON_FILENAME = "filename";

    private String mFilename;

    public Photo(String filename){
        mFilename = filename;
    }

    public Photo(JSONObject jsoin) throws JSONException {
        mFilename = jsoin.getString(JSON_FILENAME);
    }

    public JSONObject toJSON() throws JSONException {
        JSONObject json = new JSONObject();
        json.put(JSON_FILENAME, mFilename);
        return json;
    }
    public String getFilename() {
        return mFilename;
    }
}
