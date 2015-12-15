package com.neerajweb.expandablelistviewtest.JSONfunctions;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.json.JSONException;
import org.json.JSONObject;
import java.net.HttpURLConnection;
import java.net.URL;
import android.util.Log;

/**
 * Created by Admin on 10/12/2015.
 */
public class memberJSON {
    private String ownername ="";

    private String rentername="";

    private String flatnumber="";

    private String flag="";

    public static JSONObject getJSONfromURL(String url1) {
        InputStream is = null;
        String result = "";
        JSONObject jArray = null;
        int response=0;

        // Download JSON data from URL
        try {
            URL url = new URL(url1);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            response = conn.getResponseCode();
            is = new BufferedInputStream(conn.getInputStream());

        } catch (Exception e) {
            Log.e("log_tag", "Error in http connection " + e.toString());
            Log.e("log_tag", "Error in http connection " + e.toString());
            Log.e("log_tag", "Response  " + response);
        }

        // Convert response to string
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            result = sb.toString();
        } catch (Exception e) {
            Log.e("log_tag", "Error converting result " + e.toString());
        }

        try {

            jArray = new JSONObject(result);
        } catch (JSONException e) {
            Log.e("log_tag", "Error parsing data " + e.toString());
        }

        return jArray;
    }

    public void setownername(String _ownername)
    {
        this.ownername = _ownername;
    }

    public String getownername()
    {
        return this.ownername;
    }

    public void setrentername(String _rentername)
    {
        this.rentername = _rentername;
    }

    public String getrentername()
    {
        return this.rentername;
    }

    public void setflatnumber(String _flatnumber)
    {
        this.flatnumber = _flatnumber;
    }

    public String getflatnumber()
    {
        return this.flatnumber;
    }

    public void setFlag(String _Flag)
    {
        this.flag = _Flag;
    }

    public String getFlag()
    {
        return this.flag;
    }
}
