package com.neerajweb.expandablelistviewtest.DateTimePicker;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import java.util.Date;

/**
 * Created by Admin on 04/12/2015.
 */

public class SimpleDateTimePicker {
    private CharSequence mDialogTitle;
    private Date mInitDate;
    private DateTimePicker.OnDateTimeSetListener mOnDateTimeSetListener;
    private FragmentManager mFragmentManager;

    /**
     * Private constructor that can only be access using the make method
     * @param dialogTitle Title of the Date Time Picker Dialog
     * @param initDate Date object to use to set the initial Date and Time
     * @param onDateTimeSetListener OnDateTimeSetListener interface
     * @param fragmentManager Fragment Manager from the activity
     */
    private SimpleDateTimePicker(CharSequence dialogTitle, Date initDate,
                                 DateTimePicker.OnDateTimeSetListener onDateTimeSetListener,
                                 FragmentManager fragmentManager) {

        // Find if there are any DialogFragments from the FragmentManager
        FragmentTransaction mFragmentTransaction = fragmentManager.beginTransaction();
        Fragment mDateTimeDialogFrag = fragmentManager.findFragmentByTag(
                DateTimePicker.TAG_FRAG_DATE_TIME
        );

        // Remove it if found
        if(mDateTimeDialogFrag != null) {
            mFragmentTransaction.remove(mDateTimeDialogFrag);
        }
        mFragmentTransaction.addToBackStack(null);

        mDialogTitle = dialogTitle;
        mInitDate = initDate;
        mOnDateTimeSetListener = onDateTimeSetListener;
        mFragmentManager = fragmentManager;

    }

    /**
     * Creates a new instance of the SimpleDateTimePicker
     * @param dialogTitle Title of the Date Time Picker Dialog
     * @param initDate Date object to use to set the initial Date and Time
     * @param onDateTimeSetListener OnDateTimeSetListener interface
     * @param fragmentManager Fragment Manager from the activity
     * @return Returns a SimpleDateTimePicker object
     */
    public static SimpleDateTimePicker make(CharSequence dialogTitle, Date initDate,
                                            DateTimePicker.OnDateTimeSetListener onDateTimeSetListener,
                                            FragmentManager fragmentManager) {

        return new SimpleDateTimePicker(dialogTitle, initDate,
                onDateTimeSetListener, fragmentManager);

    }

    /**
     * Shows the DateTimePicker Dialog
     */
    public void show(Boolean isShowCurrentDateOnly,String mOnth) {

        // Create new DateTimePicker
        DateTimePicker mDateTimePicker = DateTimePicker.newInstance(mDialogTitle,mInitDate);
        mDateTimePicker.isShowCurrentDate=isShowCurrentDateOnly;
        mDateTimePicker.monthCode=getMonthInt(mOnth);
        mDateTimePicker.setOnDateTimeSetListener(mOnDateTimeSetListener);
        mDateTimePicker.show(mFragmentManager, DateTimePicker.TAG_FRAG_DATE_TIME);
    }

    private int getMonthInt(String month) {
        if (month.equalsIgnoreCase("JAN")) return 0;
        if (month.equalsIgnoreCase("FEB")) return 1;
        if (month.equalsIgnoreCase("MAR")) return 2;
        if (month.equalsIgnoreCase("APR")) return 3;
        if (month.equalsIgnoreCase("MAY")) return 4;
        if (month.equalsIgnoreCase("JUN")) return 5;
        if (month.equalsIgnoreCase("JUL")) return 6;
        if (month.equalsIgnoreCase("AUG")) return 7;
        if (month.equalsIgnoreCase("SEP")) return 8;
        if (month.equalsIgnoreCase("OCT")) return 9;
        if (month.equalsIgnoreCase("NOV")) return 10;
        if (month.equalsIgnoreCase("DEC")) return 11;

        //return -1 if month code not found
        return -1;
    }

}
