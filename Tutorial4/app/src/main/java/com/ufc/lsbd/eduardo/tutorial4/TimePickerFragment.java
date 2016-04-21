package com.ufc.lsbd.eduardo.tutorial4;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.TimePicker;
import android.text.format.DateFormat;
import java.util.Calendar;

/**
 * Created by eduardo on 2016-04-15.
 */
public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener
{
    private TextView timeTextView;

    public void setTimeTextView(TextView textView) {
        this.timeTextView = textView;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute, DateFormat.is24HourFormat(getActivity()));
    }
    public void onTimeSet(TimePicker view, final int hourOfDay, final int minute) {
    // Do something with the time chosen by the user

        this.timeTextView.post(new Runnable() {
            @Override
            public void run() {
                int h=hourOfDay;
                int m=minute;
                String time=new String();
                time="   "+h+":"+m;
                timeTextView.setText(time);
            }
        });
    }
}
