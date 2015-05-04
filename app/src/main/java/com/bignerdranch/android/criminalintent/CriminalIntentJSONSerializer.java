package com.bignerdranch.android.criminalintent;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.AbstractList;

/**
 * Created by Tigran on 5/4/2015.
 */
public class CriminalIntentJSONSerializer {
    private Context mContext;
    private String mFilename;

    public CriminalIntentJSONSerializer(Context c, String f){
        mContext = c;
        mFilename = f;
    }

    public  void saveCrimes(AbstractList<Crime> crimes)
        throws JSONException, IOException{
        JSONArray array = new JSONArray();
        for (Crime c : crimes)
            array.put(c.toJSON());

        Writer writer = null;
        try{
            OutputStream out = mContext
                    .openFileOutput(mFilename,Context.MODE_PRIVATE);
            writer = new OutputStreamWriter(out);
            writer.write(array.toString());
        }finally {
            if(writer != null)
                writer.close();
        }
    }
}
