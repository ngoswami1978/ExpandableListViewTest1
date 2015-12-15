package com.neerajweb.expandablelistviewtest;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.neerajweb.expandablelistviewtest.DateTimePicker.DateTime;
import com.neerajweb.expandablelistviewtest.DateTimePicker.DateTimePicker;
import com.neerajweb.expandablelistviewtest.DateTimePicker.SimpleDateTimePicker;
import com.neerajweb.expandablelistviewtest.JSONfunctions.memberJSON;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


/**
 * Created by Admin on 06/12/2015.
 */

public class maintainance extends ActionBarActivity implements DateTimePicker.OnDateTimeSetListener {

    ImageView imageView1;
    TextView textview;

    JSONObject jsonobject;
    JSONArray jsonarray;
    ProgressDialog mProgressDialog;
    ArrayList<String> Arraylst_Spinner_Member;
    ArrayList<memberJSON> member;
    public static final int DIALOG_DOWNLOAD_PROGRESS = 0;

    Spinner mySpinner;
    TextView txtownername;
    TextView txtrentername;
    TextView txtflatnumber;
    TextView txtlast_timeamountdeposited_datetime;
    TextView txtlast_timeamountdeposited ;

    String Error = null;
    boolean isSpinnerInitial ;
    View dialogView=null;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maintainance);

        imageView1 = (ImageView) findViewById(R.id.imageView1);
        ImageView imageView2 = (ImageView) findViewById(R.id.imageView2);
        ImageView imageView3 = (ImageView) findViewById(R.id.imageView3);
        ImageView imageView4 = (ImageView) findViewById(R.id.imageView4);
        ImageView imageView5 = (ImageView) findViewById(R.id.imageView5);
        ImageView imageView6 = (ImageView) findViewById(R.id.imageView6);
        ImageView imageView7 = (ImageView) findViewById(R.id.imageView7);
        ImageView imagePay = (ImageView) findViewById(R.id.imagePay);

        TextView textview1 = (TextView) findViewById(R.id.tvDispMaint1);

        textview1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                funpayMaintainance();
            }
        });


        imageView2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        imageView3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        imageView4.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        imageView5.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        imageView2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                funImage2Click();
            }
        });
        imageView6.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        imageView7.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                funImage7Click();
            }
        });
        imagePay.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                funpayMaintainance();
            }
        });

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void funImage2Click() {
        // Create a SimpleDateTimePicker and Show it
        SimpleDateTimePicker simpleDateTimePicker = SimpleDateTimePicker.make(
                "Select  Date & Time",
                new Date(),
                this,
                getSupportFragmentManager()
        );
        // Show It
        simpleDateTimePicker.show();
    }

    private void funImage7Click() {
        // Create a SimpleDateTimePicker and Show it
        SimpleDateTimePicker simpleDateTimePicker = SimpleDateTimePicker.make(
                "Select  Date & Time",
                new Date(),
                this,
                getSupportFragmentManager()
        );
        // Show It
        simpleDateTimePicker.show();
    }

    private void funpayMaintainance() {
        // Or we can chain it to simplify
        SimpleDateTimePicker.make(
                "Select  Date & Time",
                new Date(),
                this,
                getSupportFragmentManager()
        ).show();
    }

    @Override
    public void DateTimeSet(Date date) {
        // This is the DateTime class we created earlier to handle the conversion
        // of Date to String Format of Date String Format to Date object
        DateTime mDateTime = new DateTime(date);
        // Show in the LOGCAT the selected Date and Time
        Log.v("TEST_TAG", "Date and Time selected: " + mDateTime.getDateString());
        showMaintainanceEntryDialog(mDateTime.getMonthOfYear(), mDateTime.getYear());
    }

    public void showMaintainanceEntryDialog(int intMonth, int intYear) {

        try {

            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
            LayoutInflater inflater = this.getLayoutInflater();
            dialogView = inflater.inflate(R.layout.maintainance_entry_dialog, null);
            dialogBuilder.setView(dialogView);

            switch (intMonth) {
                case 0:
                    dialogBuilder.setIcon(R.drawable.jan);
                    break;
                case 1:
                    dialogBuilder.setIcon(R.drawable.feb);
                    break;
                case 2:
                    dialogBuilder.setIcon(R.drawable.mar);
                    break;
                case 3:
                    dialogBuilder.setIcon(R.drawable.apr);
                    break;
                case 4:
                    dialogBuilder.setIcon(R.drawable.may);
                    break;
                case 5:
                    dialogBuilder.setIcon(R.drawable.jun);
                    break;
                case 6:
                    dialogBuilder.setIcon(R.drawable.jul);
                    break;
                case 7:
                    dialogBuilder.setIcon(R.drawable.aug);
                    break;
                case 8:
                    dialogBuilder.setIcon(R.drawable.sep);
                    break;
                case 9:
                    dialogBuilder.setIcon(R.drawable.oct);
                    break;
                case 10:
                    dialogBuilder.setIcon(R.drawable.nov);
                    break;
                case 11:
                    dialogBuilder.setIcon(R.drawable.dec);
                    break;
            }

            final EditText edtAmount = (EditText) dialogView.findViewById(R.id.editAmt);

            // Locate the spinner and other controls in maintainance_entry_dialog.xml
            mySpinner = (Spinner) dialogView.findViewById(R.id.my_spinner);
            txtownername = (TextView) dialogView.findViewById(R.id.ownername);
            txtrentername = (TextView) dialogView.findViewById(R.id.rentername);
            txtflatnumber = (TextView) dialogView.findViewById(R.id.flatnumber);
            txtlast_timeamountdeposited_datetime = (TextView) dialogView.findViewById(R.id.last_timeamountdeposited_datetime);
            txtlast_timeamountdeposited = (TextView) dialogView.findViewById(R.id.last_timeamountdeposited);
            //--------------------------------------------------------------------------------------

            String chars = getResources().getString(R.string.PayMaintaincence);
            SpannableString str = new SpannableString(chars);
            str.setSpan(new ForegroundColorSpan(Color.WHITE), 0, chars.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

            dialogBuilder.setTitle(String.valueOf(intYear) + " " + str); //getMonthShortName(intMonth)

            dialogBuilder.setMessage("Fill Maintenance Amount");
            dialogBuilder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    //do something with edtAmount.getText().toString();
                }
            });
            dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    //pass
                }
            });

            AlertDialog b = dialogBuilder.create();
            b.show();
            // Download JSON file AsyncTask
            new DownloadJSON().execute();
        } catch (Exception e) {
            Error = e.getMessage();
        }
    }

    /**
     * @param monthNumber Month Number starts with 0. For January it is 0 and for December it is 11.
     * @return
     */
    public static String getMonthShortName(int monthNumber) {
        String monthName = "";

        if (monthNumber >= 0 && monthNumber < 12)
            try {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.MONTH, monthNumber);

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM");
                simpleDateFormat.setCalendar(calendar);
                monthName = simpleDateFormat.format(calendar.getTime());
            } catch (Exception e) {
                if (e != null)
                    e.printStackTrace();
            }
        return monthName.toUpperCase();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "maintainance Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.neerajweb.expandablelistviewtest/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "maintainance Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.neerajweb.expandablelistviewtest/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    // Download JSON file AsyncTask
    private class DownloadJSON extends AsyncTask<Void, Void, Void> {
        ProgressDialog progressDialog;
        @Override
        protected Void doInBackground(Void... params) {
            // Locate the memberJSON Class
            member = new ArrayList<memberJSON>();
            // Create an array to populate the spinner
            Arraylst_Spinner_Member = new ArrayList<String>();

            // JSON file URL address
            jsonobject = memberJSON
                    .getJSONfromURL("http://myandroidng.com/member_detail.php");

            try {
                // Locate the NodeList name
                jsonarray = jsonobject.getJSONArray("register_member");

                // Set Initial Value in Spinner
                memberJSON Initialmemberpop = new memberJSON();
                Initialmemberpop.setownername("");
                Initialmemberpop.setrentername("");
                Initialmemberpop.setflatnumber("");
                Initialmemberpop.setFlag("");
                member.add(Initialmemberpop);
                Arraylst_Spinner_Member.add("Choose Flat....");
                //

                for (int i = 0; i < jsonarray.length(); i++) {
                    jsonobject = jsonarray.getJSONObject(i);

                    memberJSON memberpop = new memberJSON();

                    memberpop.setownername(jsonobject.optString("Owner_name"));
                    memberpop.setrentername(jsonobject.optString("Renter_name"));
                    memberpop.setflatnumber(jsonobject.optString("flt_no"));
                    memberpop.setFlag(jsonobject.optString("flt_type"));
                    member.add(memberpop);

                    // Populate spinner with flats names
                    Arraylst_Spinner_Member.add(jsonobject.optString("flt_no") + "      " +jsonobject.optString("flt_type"));
                }
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(maintainance.this);
            progressDialog.setTitle("Processing...");
            progressDialog.setMessage("Please wait...");
            progressDialog.setCancelable(true);
            progressDialog.show();

        }

        @Override
        protected void onPostExecute(Void args) {
            try
            {
                //progressDialog.dismiss();
                if (!(Arraylst_Spinner_Member.isEmpty())) {
                // Spinner adapter
                mySpinner.setAdapter(new ArrayAdapter<String>(maintainance.this , android.R.layout.simple_spinner_dropdown_item, Arraylst_Spinner_Member));

                    isSpinnerInitial=true;
                    mySpinner.setSelection(-1);
                // Spinner on item click listener
                mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> arg0,
                                                   View arg1, int position, long arg3) {
                            // TODO Auto-generated method stub
                            // Locate the textviews in activity_main.xml
                            if (isSpinnerInitial){
                                isSpinnerInitial = false;
                                return;
                            }
                            else {
                                // Set the text followed by the position
                                txtownername.setText("Owner  : "
                                        + member.get(position).getownername());
                                txtrentername.setText("Renter : "
                                        + member.get(position).getrentername());
                                txtflatnumber.setText("Flat : "
                                        + member.get(position).getflatnumber());
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> arg0) {
                            // TODO Auto-generated method stub
                        }
                    });
                }
                progressDialog.cancel();
            } catch (Exception e) {
               Error = e.getMessage();
               cancel(true);
            }
        }
    }
}
