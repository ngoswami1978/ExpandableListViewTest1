package com.neerajweb.expandablelistviewtest.Maintainance;

/**
 * Created by Admin on 02/01/2016.
 */
import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class GlobalClassMyApplication extends Application {

    public static final String TAG = GlobalClassMyApplication.class.getSimpleName();
    private RequestQueue mRequestQueue;
    private static GlobalClassMyApplication mInstance;
    private String name;
    private String email;
    public final String URL_MEMBERDETAIL="http://myandroidng.com/member_detail.php";
    public final String URL_UPDATEMAINTAINANCE="http://myandroidng.com/Apartment/WS/ws_crud_maintainance.php";


    public String getName() {        return name;    }

    public void setName(String aName) {        name = aName;    }

    public String getEmail() {        return email;    }

    public void setEmail(String aEmail) {        email = aEmail;    }

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

    // Calling Application class (see application tag in AndroidManifest.xml)
    //--final GlobalClass globalVariable = (GlobalClass) getApplicationContext();

    //Set name and email in global/application context
    //--globalVariable.setName("Android Example context variable");
    //--globalVariable.setEmail("xxxxxx@aaaa.com");


    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static synchronized GlobalClassMyApplication getInstance() {
        return mInstance;
    }

    public RequestQueue getReqQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

    public <T> void addToReqQueue(Request<T> req, String tag) {
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getReqQueue().add(req);
    }

    public <T> void addToReqQueue(Request<T> req) {
        req.setTag(TAG);
        getReqQueue().add(req);
    }

    public void cancelPendingReq(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }
}
