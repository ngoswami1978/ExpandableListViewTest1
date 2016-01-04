package com.neerajweb.expandablelistviewtest;

import android.app.AlertDialog;
import android.app.Dialog;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.neerajweb.expandablelistviewtest.DateTimePicker.DateTime;
import com.neerajweb.expandablelistviewtest.DateTimePicker.DateTimePicker;
import com.neerajweb.expandablelistviewtest.DateTimePicker.SimpleDateTimePicker;
import com.neerajweb.expandablelistviewtest.JSONfunctions.memberJSON;
import com.neerajweb.expandablelistviewtest.Maintainance.GlobalClassMyApplication;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by Admin on 06/12/2015.
 */

public class maintainance extends ActionBarActivity implements DateTimePicker.OnDateTimeSetListener {

    TextView textview;

    JSONObject jsonobject;
    JSONArray jsonarray;
    ProgressDialog mProgressDialog;
    ArrayList<String> Arraylst_Spinner_Member;
    ArrayList<memberJSON> member;
    public static final int DIALOG_DOWNLOAD_PROGRESS = 0;

    EditText edtAmount;
    Spinner mySpinner;
    Spinner my_paymodespinner;
    TextView txtownername;
    TextView txtrentername;
    TextView txtflatnumber;

    TextView txtlastPaidMonth;
    TextView txtlastPaidYear;
    TextView txtlastPaidAmount;
    TextView txtlastPaidEntrydt;
    TextView txtfltdiscription;
    TextView txtlastPaidby;
    LinearLayout descSeperator;

    LinearLayout bubblecontentWithBackgrounddate;
    LinearLayout bubblecontentWithBackgroundcash;

    String Error = null;
    String flatDescription;
    String renterName;
    boolean isSpinnerInitial ;
    View dialogView=null;
    boolean blnIsValidation;

    //Maintainance update progress dialog
    ProgressDialog MPD;

    // create hash map
    HashMap<String, String> hashmapmaintaincneInput = new HashMap<String, String>();

    Date mCurrentDate;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maintainance);

        MPD = new ProgressDialog(this);
        MPD.setMessage("Updating please wait.....");
        MPD.setCancelable(false);

        mCurrentDate = new Date();
        int intCurrentMonth =mCurrentDate.getMonth();

        ImageView paymentcard = (ImageView) findViewById(R.id.imagePay);

        ImageView imageViewJAN = (ImageView) findViewById(R.id.imageViewJAN);
        imageViewJAN.setImageDrawable(getResources().getDrawable(R.drawable.jangray));
        enableDisableMonth(imageViewJAN ,intCurrentMonth,0 );

        ImageView imageViewFEB = (ImageView) findViewById(R.id.imageViewFEB);
        imageViewFEB.setImageDrawable(getResources().getDrawable(R.drawable.febgray));
        enableDisableMonth(imageViewFEB, intCurrentMonth, 1);

        ImageView imageViewMAR = (ImageView) findViewById(R.id.imageViewMAR);
        imageViewMAR.setImageDrawable(getResources().getDrawable(R.drawable.margray));
        enableDisableMonth(imageViewMAR, intCurrentMonth, 2);

        ImageView imageViewAPR = (ImageView) findViewById(R.id.imageViewAPR);
        imageViewAPR.setImageDrawable(getResources().getDrawable(R.drawable.aprgray));
        enableDisableMonth(imageViewAPR, intCurrentMonth, 3);

        ImageView imageViewMAY = (ImageView) findViewById(R.id.imageViewMAY);
        imageViewMAY.setImageDrawable(getResources().getDrawable(R.drawable.maygray));
        enableDisableMonth(imageViewMAY, intCurrentMonth, 4);

        ImageView imageViewJUN = (ImageView) findViewById(R.id.imageViewJUN);
        imageViewJUN.setImageDrawable(getResources().getDrawable(R.drawable.jungray));
        enableDisableMonth(imageViewJUN, intCurrentMonth, 5);

        ImageView imageViewJUL = (ImageView) findViewById(R.id.imageViewJUL);
        imageViewJUL.setImageDrawable(getResources().getDrawable(R.drawable.julgray));
        enableDisableMonth(imageViewJUL, intCurrentMonth, 6);

        ImageView imageViewAUG = (ImageView) findViewById(R.id.imageViewAUG);
        imageViewAUG.setImageDrawable(getResources().getDrawable(R.drawable.auggray));
        enableDisableMonth(imageViewAUG, intCurrentMonth, 7);

        ImageView imageViewSEP = (ImageView) findViewById(R.id.imageViewSEP);
        imageViewSEP.setImageDrawable(getResources().getDrawable(R.drawable.sepgray));
        enableDisableMonth(imageViewSEP, intCurrentMonth, 8);

        ImageView imageViewOCT = (ImageView) findViewById(R.id.imageViewOCT);
        imageViewOCT.setImageDrawable(getResources().getDrawable(R.drawable.octgray));
        enableDisableMonth(imageViewOCT, intCurrentMonth, 9);

        ImageView imageViewNOV = (ImageView) findViewById(R.id.imageViewNOV);
        imageViewNOV.setImageDrawable(getResources().getDrawable(R.drawable.novgray));
        enableDisableMonth(imageViewNOV, intCurrentMonth, 10);

        ImageView imageViewDEC = (ImageView) findViewById(R.id.imageViewDEC);
        imageViewDEC.setImageDrawable(getResources().getDrawable(R.drawable.decgray));
        enableDisableMonth(imageViewDEC, intCurrentMonth, 11);


        TextView textview1 = (TextView) findViewById(R.id.tvDispMaint1);
        textview1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                funpayMaintainance(false, "");
            }
        });

        paymentcard.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                paymentcard();
            }
        });



//        imageViewMAR.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                funpayMaintainance(true,"MAR");
//            }
//        });
//        imageViewAPR.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                funpayMaintainance(true,"APR");
//            }
//        });
//        imageViewMAY.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                funpayMaintainance(true,"MAY");
//            }
//        });
//        imageViewJUN.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                funpayMaintainance(true,"JUN");
//            }
//        });
//        imageViewJUL.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                funpayMaintainance(true,"JUL");
//            }
//        });
//        imageViewAUG.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                funpayMaintainance(true,"AUG");
//            }
//        });
//        imageViewSEP.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                funpayMaintainance(true,"SEP");
//            }
//        });
//        imageViewOCT.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                funpayMaintainance(true,"OCT");
//            }
//        });
//        imageViewNOV.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                funpayMaintainance(true,"NOV");
//            }
//        });
//        imageViewDEC.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                funpayMaintainance(true,"DEC");
//            }
//        });

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void enableDisableMonth(ImageView imgView, Integer intMth,int mthCode)
    {
        try
        {
            switch (mthCode) {
                case 0:
                    if (intMth== mthCode) {
                        imgView.setImageDrawable(getResources().getDrawable(R.drawable.jan));
                        imgView.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                funpayMaintainance(true, "JAN");
                            }
                        });
                    }
                    break;
                case 1:
                    if (intMth== mthCode){
                    imgView.setImageDrawable(getResources().getDrawable(R.drawable.feb));
                    imgView.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            funpayMaintainance(true,"FEB");
                        }
                    });
                    }
                    break;
                case 2:
                    if (intMth== mthCode) {
                        imgView.setImageDrawable(getResources().getDrawable(R.drawable.mar));
                        imgView.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                funpayMaintainance(true, "MAR");
                            }
                        });
                    }
                    break;
                case 3:
                    if (intMth== mthCode) {
                        imgView.setImageDrawable(getResources().getDrawable(R.drawable.apr));
                        imgView.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                funpayMaintainance(true, "APR");
                            }
                        });
                    }
                    break;
                case 4:
                    if (intMth== mthCode) {
                        imgView.setImageDrawable(getResources().getDrawable(R.drawable.may));
                        imgView.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                funpayMaintainance(true, "MAY");
                            }
                        });
                    }
                    break;
                case 5:
                    if (intMth== mthCode) {
                        imgView.setImageDrawable(getResources().getDrawable(R.drawable.jun));
                        imgView.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                funpayMaintainance(true, "JUN");
                            }
                        });
                        break;
                    }
                case 6:
                    if (intMth== mthCode) {
                        imgView.setImageDrawable(getResources().getDrawable(R.drawable.jul));
                        imgView.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                funpayMaintainance(true, "JUL");
                            }
                        });
                    }
                    break;
                case 7:
                    if (intMth== mthCode) {
                        imgView.setImageDrawable(getResources().getDrawable(R.drawable.aug));
                        imgView.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                funpayMaintainance(true, "AUG");
                            }
                        });
                    }
                    break;
                case 8:
                    if (intMth== mthCode) {
                        imgView.setImageDrawable(getResources().getDrawable(R.drawable.sep));
                        imgView.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                funpayMaintainance(true, "SEP");
                            }
                        });
                    }
                    break;
                case 9:
                    if (intMth== mthCode) {
                        imgView.setImageDrawable(getResources().getDrawable(R.drawable.oct));
                        imgView.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                funpayMaintainance(true, "OCT");
                            }
                        });
                    }
                    break;
                case 10:
                    if (intMth== mthCode) {
                        imgView.setImageDrawable(getResources().getDrawable(R.drawable.nov));
                        imgView.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                funpayMaintainance(true, "NOV");
                            }
                        });
                    }
                    break;
                case 11:
                    if (intMth== mthCode) {
                        imgView.setImageDrawable(getResources().getDrawable(R.drawable.dec));
                        imgView.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                funpayMaintainance(true, "DEC");
                            }
                        });
                    }
                    break;
            }
        }
        catch(Exception Ex)
        {

        }
    }

    private void paymentcard() {
        // Create a SimpleDateTimePicker and Show it
        SimpleDateTimePicker simpleDateTimePicker = SimpleDateTimePicker.make(
                "Select  Date & Time",
                new Date(),
                this,
                getSupportFragmentManager()
        );
        // Show It
        simpleDateTimePicker.show(false,"");
    }

    private void funpayMaintainance(boolean isCurentDateRequiredOnly,String mnth) {
        // Or we can chain it to simplify
        if (mnth!="")
        {
            SimpleDateTimePicker.make(
                    "Select  Date & Time",
                    new Date(),
                    this,
                    getSupportFragmentManager()
            ).show(isCurentDateRequiredOnly,mnth);
        }
        else {
            SimpleDateTimePicker.make(
                    "Select  Date & Time",
                    new Date(),
                    this,
                    getSupportFragmentManager()
            ).show(isCurentDateRequiredOnly,"");
        }
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

            edtAmount = (EditText) dialogView.findViewById(R.id.editAmt);

            // Locate the spinner and other controls in maintainance_entry_dialog.xml
            mySpinner = (Spinner) dialogView.findViewById(R.id.my_flatspinner);
            my_paymodespinner = (Spinner) dialogView.findViewById(R.id.my_paymodespinner);

            txtownername = (TextView) dialogView.findViewById(R.id.ownername);
            txtrentername = (TextView) dialogView.findViewById(R.id.rentername);
            txtflatnumber = (TextView) dialogView.findViewById(R.id.flatnumber);

            txtfltdiscription = (TextView) dialogView.findViewById(R.id.fltdiscription);
            txtlastPaidEntrydt= (TextView) dialogView.findViewById(R.id.lastPaidEntrydt);;
            txtlastPaidMonth=(TextView) dialogView.findViewById(R.id.lastPaidMonth);
            txtlastPaidYear=(TextView) dialogView.findViewById(R.id.lastPaidYear);
            txtlastPaidAmount=(TextView) dialogView.findViewById(R.id.lastPaidAmount);
            txtlastPaidby=(TextView) dialogView.findViewById(R.id.lastPaidby);

//              descSeperator =(LinearLayout)dialogView.findViewById(R.id.descSeperator);

            bubblecontentWithBackgrounddate=(LinearLayout)dialogView.findViewById(R.id.bubblecontentWithBackground);
            bubblecontentWithBackgroundcash=(LinearLayout)dialogView.findViewById(R.id.bubblecontentWithBackgroundcash);

            txtfltdiscription.setVisibility(View.INVISIBLE);
            txtlastPaidEntrydt.setVisibility(View.INVISIBLE);
            txtlastPaidMonth.setVisibility(View.INVISIBLE);
            txtlastPaidYear.setVisibility(View.INVISIBLE);
            txtlastPaidAmount.setVisibility(View.INVISIBLE);
            txtlastPaidby.setVisibility(View.INVISIBLE);
//              descSeperator.setVisibility(View.INVISIBLE);
            bubblecontentWithBackgrounddate.setVisibility(View.INVISIBLE);
            bubblecontentWithBackgroundcash.setVisibility(View.INVISIBLE);

            //--------------------------------------------------------------------------------------
            String chars = getResources().getString(R.string.PayMaintaincence);
            SpannableString str = new SpannableString(chars);
            str.setSpan(new ForegroundColorSpan(Color.WHITE), 0, chars.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            dialogBuilder.setTitle(String.valueOf(intYear) + " " + str); //getMonthShortName(intMonth)

//          final GlobalClass globalVariable = (GlobalClass) getApplicationContext();
//          globalVariable.getMonthShortName(intMonth);

            dialogBuilder.setMessage("Fill Maintenance Amount");
            dialogBuilder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    //do something with edtAmount.getText().toString();
                    //All of the validation happens inside the CustomListener.
                    //I had to move it to enable data validation.
                }
            });
            dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    //pass
                }
            });

            AlertDialog b = dialogBuilder.create();
            b.show();
            Button theButton = b.getButton(DialogInterface.BUTTON_POSITIVE);
            theButton.setOnClickListener(new CustomListener(b));

            // Download JSON data AsyncTask
            new DownloadJSON().execute();
        } catch (Exception e) {
            Error = e.getMessage();
        }
    }

//  CustomListener class used for validations
    class CustomListener implements View.OnClickListener {
        private final Dialog dialog;
        public CustomListener(Dialog dialog) {
            this.dialog = dialog;
        }
        @Override
        public void onClick(View v) {
            // put your code here
            blnIsValidation = validateInputs();

            if (!blnIsValidation) {
                //edtAmount.setError("Please enter maintainance amount!"); //
                //Toast.makeText(maintainance.this, "Invalid data", Toast.LENGTH_SHORT).show();
            } else {
//              call maintainance deposit Web servicce here to insert deposit amount into server
                insert(dialogView);
                //dialog.dismiss();
            }
        }
    }

    public boolean validateInputs()
    {
        boolean isValidate=true;
        if( edtAmount.getText().toString().length() == 0 ) {
            edtAmount.setError("Please provide Maintainance Amount!");
            isValidate=false;
            return isValidate;
        }

        String st =mySpinner.getSelectedItem().toString();
        int pos =mySpinner.getSelectedItemPosition();

        //Toast.makeText(this,
        //        "Selected position !!" + pos , Toast.LENGTH_LONG)
        //        .show();

        if(pos!=0)
        {
            String flat_type = mySpinner.getSelectedItem().toString();
            Toast.makeText(this,
                    "Thankyou for the maintainance payment of selected flat !!" + flat_type  , Toast.LENGTH_LONG)
                    .show();
            isValidate=true;
            return isValidate;
        }
        else{
            Toast.makeText(this,
                    "Please Select the flat !!", Toast.LENGTH_LONG)
                    .show();
            isValidate=false;
            return isValidate;
        }
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

    // Download JSON data AsyncTask
    private class DownloadJSON extends AsyncTask<Void, Void, Void> {
        ProgressDialog progressDialog;
        final GlobalClassMyApplication globalVariable = (GlobalClassMyApplication) getApplicationContext();

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
        protected Void doInBackground(Void... params) {
//          Locate the memberJSON Class
            member = new ArrayList<memberJSON>();
//          Create an array to populate the spinner
            Arraylst_Spinner_Member = new ArrayList<String>();

            try {
//                  JSON file URL address
//                  jsonobject = memberJSON
//                        .getJSONfromURL("http://myandroidng.com/member_detail.php");

                jsonobject = memberJSON
                        .getJSONfromURL(globalVariable.URL_MEMBERDETAIL);

//              Locate the NodeList name
                jsonarray = jsonobject.getJSONArray("register_member");

//              Set Initial Value in Spinner
                memberJSON Initialmemberpop = new memberJSON();

                Initialmemberpop.setownerid(0);
                Initialmemberpop.setfltid(0);
                Initialmemberpop.setownername("");
                Initialmemberpop.setrentername("");
                Initialmemberpop.setflatnumber("");
                Initialmemberpop.setflattype("");
                Initialmemberpop.setlastPaidMonth("");
                Initialmemberpop.setlastPaidYear("");
                Initialmemberpop.setlastPaidAmount("");
                Initialmemberpop.setlastPaidEntrydt("");
                Initialmemberpop.setlastPaidby("");

                member.add(Initialmemberpop);
                Arraylst_Spinner_Member.add("Choose Flat....");


                for (int i = 0; i < jsonarray.length(); i++) {
                    jsonobject = jsonarray.getJSONObject(i);

                    memberJSON memberpop = new memberJSON();

                    memberpop.setownerid(jsonobject.optInt("Owner_id"));
                    memberpop.setfltid(jsonobject.optInt("flt_id"));
                    memberpop.setownername(jsonobject.optString("Owner_name"));
                    memberpop.setrentername(jsonobject.isNull("Renter_name") ? "" : jsonobject.getString("Renter_name")); // Renters might be null sometimes
                    memberpop.setflatnumber(jsonobject.optString("flt_no"));
                    memberpop.setflattype(jsonobject.optString("flt_type"));


                    memberpop.setlastPaidMonth(jsonobject.isNull("LastPaidMonth") ? "" : jsonobject.optString("LastPaidMonth"));
                    memberpop.setlastPaidYear(jsonobject.isNull("LastPaidYear") ? "" : jsonobject.optString("LastPaidYear"));
                    memberpop.setlastPaidAmount(jsonobject.isNull("LastPaidAmount") ? "" : jsonobject.optString("LastPaidAmount"));
                    memberpop.setlastPaidEntrydt(jsonobject.isNull("LastPaidEntrydt") ? "" : jsonobject.optString("LastPaidEntrydt"));
                    memberpop.setlastPaidby(jsonobject.isNull("LastPaidby") ? "" : jsonobject.optString("LastPaidby"));

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
        protected void onPostExecute(Void args) {
            try
            {
                //progressDialog.dismiss();

                if (!(Arraylst_Spinner_Member.isEmpty())) {

                //Setting Payment Mode spinner adapter
                ArrayAdapter<String> spinnerpaymodeArrayAdapter = new ArrayAdapter<String>(maintainance.this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.paymentmode));
                my_paymodespinner.setAdapter(spinnerpaymodeArrayAdapter);

                // Setting Flat Spinner adapter
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

                            // clear hash map
                            hashmapmaintaincneInput.clear();

                            hashmapmaintaincneInput.put("FLT_ID", String.valueOf(member.get(position).getfltid()));
                            hashmapmaintaincneInput.put("OWNER_ID", String.valueOf(member.get(position).getownerid()) );
                            hashmapmaintaincneInput.put("OWNERNAME", member.get(position).getownername().toString());
                            hashmapmaintaincneInput.put("RENTERNAME", member.get(position).getrentername().toString());
                            hashmapmaintaincneInput.put("FLATNUMBER", member.get(position).getflatnumber().toString());
                            hashmapmaintaincneInput.put("FLATTYPE", member.get(position).getflattype().toString());

                            flatDescription = txtfltdiscription.getText().toString();
                            String newString;

                            renterName = member.get(position).getrentername();
                            if ( !renterName.contains("Not Available") )
                            {
                                renterName = "Renter -  " + renterName;
                                //newString ="The Last Maintainance deposit detail from " + member.get(position).getownername().toString() + "/" + renterName+ "."  + String.valueOf(member.get(position).getownerid()) + " - " + String.valueOf(member.get(position).getfltid()) ;
                                newString ="The Last Maintainance deposit detail from " + member.get(position).getownername().toString() + "/" + renterName+ ".";
                            }
                            else
                            {
                                //renterName = "Self";
                                //newString ="The Last Maintainance deposit detail from " + member.get(position).getownername().toString() + "."  + String.valueOf(member.get(position).getownerid()) + " - " + String.valueOf(member.get(position).getfltid()) ;
                                newString ="The Last Maintainance deposit detail from " + member.get(position).getownername().toString() + ".";
                            }


                            txtfltdiscription.setText(newString);
                            txtfltdiscription.setVisibility(View.VISIBLE);
                            txtlastPaidEntrydt.setVisibility(View.VISIBLE);
                            txtlastPaidMonth.setVisibility(View.VISIBLE);
                            txtlastPaidYear.setVisibility(View.VISIBLE);
                            txtlastPaidAmount.setVisibility(View.VISIBLE);
                            txtlastPaidby.setVisibility(View.VISIBLE);

                            String dateStr =member.get(position).getlastPaidEntrydt().toString();
                            if (dateStr!="")
                            {
                                bubblecontentWithBackgrounddate.setVisibility(View.VISIBLE);
                                bubblecontentWithBackgroundcash.setVisibility(View.VISIBLE);
                            }
                            else
                            {
                                bubblecontentWithBackgrounddate.setVisibility(View.INVISIBLE);
                                bubblecontentWithBackgroundcash.setVisibility(View.INVISIBLE);
                            }

                            SimpleDateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            SimpleDateFormat targetFormat = new SimpleDateFormat("EEEE, MMMM dd, yyyy hh:mm a" );
                            Date date;
                            String strTargetDate="";
                            try {
                                date = originalFormat.parse(dateStr);
//                              System.out.println("Old Format :   " + originalFormat.format(date));
//                              System.out.println("New Format :   " + targetFormat.format(date));
                                strTargetDate = targetFormat.format(date).toString();
                            } catch (ParseException ex) {
                                Toast.makeText(maintainance.this,
                                        "Last maintainance deposit detail not found!", Toast.LENGTH_LONG)
                                        .show();
                            }

                            txtlastPaidEntrydt.setText("Date : "
                                    + strTargetDate);
                            txtlastPaidMonth.setText("Month : "
                                    + member.get(position).getlastPaidMonth());
                            txtlastPaidYear.setText("Year : "
                                    + member.get(position).getlastPaidYear());
                            txtlastPaidAmount.setText("Amount : "
                                    + member.get(position).getlastPaidAmount());
                            txtlastPaidby.setText("paid by : "
                                    + member.get(position).getlastPaidby());
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


    public void insert(View v) {

        final GlobalClassMyApplication globalVariable = (GlobalClassMyApplication) getApplicationContext();

        MPD.show();
        //item_name = item_et.getText().toString();

        StringRequest postRequest = new StringRequest(Request.Method.POST, globalVariable.URL_UPDATEMAINTAINANCE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        MPD.dismiss();
                        //item_et.setText("");
                        Toast.makeText(getApplicationContext(),
                                "Data Inserted Successfully",
                                Toast.LENGTH_SHORT).show();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                MPD.dismiss();
                Toast.makeText(getApplicationContext(),
                        "failed to insert", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                //params.put("item_name", item_name);

                params.put("mth","12");
                params.put("yr","2015");
                params.put("O_id",hashmapmaintaincneInput.get("OWNER_ID"));
                params.put("f_id", hashmapmaintaincneInput.get("FLT_ID"));
                params.put("amt", edtAmount.getText().toString());
                params.put("pdby",my_paymodespinner.getSelectedItem().toString());

                return params;
            }
        };

        // Adding request to request queue
        GlobalClassMyApplication.getInstance().addToReqQueue(postRequest);
    }

    public void read(View v) {
        //Intent read_intent = new Intent(MainActivity.this, ReadData.class);
        //startActivity(read_intent);
    }
}