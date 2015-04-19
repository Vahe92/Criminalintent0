package com.bignerdranch.android.criminalintent;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Vahe on 4/19/2015.
 */
public class TimePickerFragment extends DialogFragment {
    public static String EXTRA_TIME =
    "com.bignerdranch.android.criminalintent.time";
    private Date mTime;

    public static TimePickerFragment newInstance(Date time){
        Bundle b = new Bundle();
        b.putSerializable(EXTRA_TIME, time);
        TimePickerFragment tpf = new TimePickerFragment();
        tpf.setArguments(b);
        return  tpf;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mTime = (Date)getArguments().getSerializable(EXTRA_TIME);
        Calendar c = Calendar.getInstance();
        c.setTime(mTime);
        final int hour = c.get(Calendar.HOUR);
        int min = c.get(Calendar.MINUTE);
        final int year = c.get(Calendar.YEAR);
        final int month = c.get(Calendar.MONTH);
        final int day = c.get(Calendar.DAY_OF_MONTH);


        View v = getActivity().getLayoutInflater()
                .inflate(R.layout.dialog_time, null);

        TimePicker tp = (TimePicker)v.findViewById(R.id.dialog_time_timePicker);
        tp.setCurrentHour(hour);
        tp.setCurrentMinute(min);
        tp.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                mTime = new GregorianCalendar(year,month,day,hourOfDay,minute).getTime();
                getArguments().putSerializable(EXTRA_TIME,mTime);
            }
        });
        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(R.string.time_picker_title)
                .setPositiveButton(
                        android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                setResult(Activity.RESULT_OK);
                            }
                        }
                )
                .create();
    }

    private void setResult(int resultCode){
        if(getTargetFragment() == null)
            return;
        Intent i = new Intent();
        i.putExtra(EXTRA_TIME,mTime);
        getTargetFragment().onActivityResult(getTargetRequestCode(),resultCode,i);
    }
}
