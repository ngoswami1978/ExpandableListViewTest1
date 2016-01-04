package com.neerajweb.expandablelistviewtest;

import android.app.Application;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Admin on 29/12/2015.
 */
public class GlobalClass extends Application {

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


}
