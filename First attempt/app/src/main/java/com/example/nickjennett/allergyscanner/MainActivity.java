package com.example.nickjennett.allergyscanner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.os.AsyncTask;
import java.util.ArrayList;
import android.widget.*;
import java.util.Date;


public class MainActivity extends AppCompatActivity {
    ArrayList<String> items = new ArrayList<String>();
    String jsonTest;
    private ListView mDrawerList;
    private ArrayAdapter<String> mAdapter;


    private void addDrawerItems() {
        String[] osArray = {"Android", "iOS", "Windows", "OS X", "Linux"};
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, osArray);
        mDrawerList.setAdapter(mAdapter);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDrawerList = (ListView)findViewById(R.id.navList);
        new AsyncTaskParseJson().execute();
    }

    // added asynctask class methods below -  you can make this class as a separate class file
    public class AsyncTaskParseJson extends AsyncTask<String, String, String> {

                        // set the url of the web service to call
                String yourServiceUrl = "https://dev.tescolabs.com/product/?gtin=5054402919854";


                @Override
        // this method is used for......................
            protected void onPreExecute() {}

        @Override
        // this method is used for...................
                 protected String doInBackground(String... arg0) {

                try {
                            Log.d( "class.function", "blah");
                            // create new instance of the httpConnect class
                                httpConnect jParser = new httpConnect();
                                // get json string from service url
                            String json = jParser.getJSONFromUrl(yourServiceUrl);


                                // save returned json to your test string
                            jsonTest = json.toString();
                            //String aJsonString = json.getString("products");



                        }  catch (Exception e) {
                                e.printStackTrace();
                            }
                        return null;
                    }
                @Override
                // below method will run when service HTTP request is complete, will then bind tweet text in arrayList to ListView
                        protected void onPostExecute(String strFromDoInBg) {

                    }
                }

    }

