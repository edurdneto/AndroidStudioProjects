package com.ufc.lsbd.eduardo.tutorial4;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

/**
 * Created by eduardo on 2016-04-14.
 */
public class DataPickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private TextView dateTextView;

    public void setDateTextView(TextView textView) {
        this.dateTextView = textView;
    }

    @Override
    public Dialog onCreateDialog(Bundle saveInstanceState){
        final Calendar calendar=Calendar.getInstance();
        int year=calendar.get(Calendar.YEAR);
        int month=calendar.get(Calendar.MONTH);
        int day=calendar.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(),this,year,month,day);
    }


    public void onDateSet(DatePicker view, final int year, final int monthOfYear, final int dayOfMonth) {

        this.dateTextView.post(new Runnable() {
            @Override
            public void run() {
                int y=year;
                int m=monthOfYear;
                int d=dayOfMonth;
                String time=new String();
                time=d+"/"+m+"/"+y;
                dateTextView.setText(time);
            }
        });
    }
}
