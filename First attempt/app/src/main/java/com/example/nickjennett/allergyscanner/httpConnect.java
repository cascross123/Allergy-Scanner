package com.example.nickjennett.allergyscanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import android.util.Log;

/**
 * Created by nickjennett on 08/11/2016.
 */


public class httpConnect {

    final String TAG = "JsonParser.java";

    static String json = "";

    // your android activity will call this method and pass in the url of the REST service
    public String getJSONFromUrl(String url) {

                try {
                // this code block represents/configures a connection to your REST service
                    // it also represents an HTTP 'GET' request to get data from the REST service, not POST!
                         URL u = new URL(url);
                        HttpURLConnection restConnection = (HttpURLConnection) u.openConnection();
                        restConnection.setRequestProperty("gtin", "5054402919854");
                        restConnection.setRequestMethod("GET");
                        restConnection.setRequestProperty("Content-length", "0");
                        //httpRequest.addHeader( "X-Testing-Auth-Secret" , "kI7wGju76kjhJHGklk76" );

                        restConnection.setRequestProperty ("Ocp-Apim-Subscription-Key", "3e9b724417264a91afbe3f4247b6d8d1");

                        restConnection.setUseCaches(false);
                        restConnection.setAllowUserInteraction(false);
                        restConnection.setConnectTimeout(10000);
                        restConnection.setReadTimeout(10000);
                        restConnection.connect();
                        int status = restConnection.getResponseCode();

                        // switch statement to catch HTTP 200 and 201 errors
                        switch (status) {
                                case 200:
                                case 201:
                                    // live connection to your REST service is established here using getInputStream() method
                                        BufferedReader br = new BufferedReader(new InputStreamReader(restConnection.getInputStream()));

                                // create a new string builder to store json data returned from the REST service
                                    StringBuilder sb = new StringBuilder();
                                    String line;

                                // loop through returned data line by line and append to stringbuilder 'sb' variable
                                        while ((line = br.readLine()) != null) {
                                        sb.append(line+"\n");
                                        }
                                        br.close();

                                    // remember, you are storing the json as a stringy
                                    try {
                                        json = sb.toString();
                                    } catch (Exception e) {
                                        Log.e(TAG, "Error parsing data " + e.toString());
                                    }
                                    // return JSON String containing data to Tweet activity (or whatever your activity is called!)
                                    Log.d( "json:..", json);
                                    return json;

                            }
                    // HTTP 200 and 201 error handling from switch statement
                    } catch (MalformedURLException ex) {
                        Log.e(TAG, "Malformed URL ");
                    } catch (IOException ex) {
                    Log.e(TAG, "IO Exception error here ");
                }
            return null;
        }

}
