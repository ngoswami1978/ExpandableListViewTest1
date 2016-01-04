package com.neerajweb.expandablelistviewtest;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import com.neerajweb.expandablelistviewtest.DateTimePicker.DateTime;
import com.neerajweb.expandablelistviewtest.DateTimePicker.DateTimePicker;
import com.neerajweb.expandablelistviewtest.DateTimePicker.SimpleDateTimePicker;

import java.util.Date;

/**
 * Created by Admin on 04/12/2015.
 */
public class date_time_picker_MainActivity extends ActionBarActivity implements DateTimePicker.OnDateTimeSetListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create a SimpleDateTimePicker and Show it
        SimpleDateTimePicker simpleDateTimePicker = SimpleDateTimePicker.make(
                "Set Date & Time Title",
                new Date(),
                this,
                getSupportFragmentManager()
        );
        // Show It baby!
        simpleDateTimePicker.show(false,"");

        // Or we can chain it to simplify
        SimpleDateTimePicker.make(
                "Set Date & Time Title",
                new Date(),
                this,
                getSupportFragmentManager()
        ).show(false,"");
    }

    @Override
    public void DateTimeSet(Date date) {

        // This is the DateTime class we created earlier to handle the conversion
        // of Date to String Format of Date String Format to Date object
        DateTime mDateTime = new DateTime(date);
        // Show in the LOGCAT the selected Date and Time
        Log.v("TEST_TAG","Date and Time selected: " + mDateTime.getDateString());

    }

}
