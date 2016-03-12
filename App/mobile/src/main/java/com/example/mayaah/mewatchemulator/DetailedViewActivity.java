package com.example.mayaah.mewatchemulator;

/**
 * Created by mayaah on 2/29/16.
 */

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class DetailedViewActivity extends Activity{

    String[] itemname;

    String[] itembioguide;

    Integer[] imgid={
            R.drawable.feinstein,
            R.drawable.boxer,
            R.drawable.matsui,

    };

    String[] itemterm={
            "January 3, 2019",
            "January 3, 2017",
            "January 3, 2017"

    };

    String[] itemcomm={
            "Appropriations Committee, Select Committee on Intelligence, Rules and Administration Committee",
            "Select Commitee on Ethics, Environment and Public Works Committee, Foreign Relations Commitee",
            "Energy and Commerce Committee"


    };

    String[] itembills;

    int pos;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailed_view);
        Toast.makeText(this, "idk", Toast.LENGTH_SHORT).show();
        Bundle extras = getIntent().getExtras();

        itemname = extras.getStringArray("itemname");
        itembioguide = extras.getStringArray("itembioguide");


        if (extras != null) {
            int pos = extras.getInt("POSITION");
        }
        pos = extras.getInt("POSITION");

        String apiKey = "9dd30236d3784021854ae939c949c418";
        String legislatorsURL = "https://congress.api.sunlightfoundation.com/bills?sponsor_id=" + itembioguide[pos] + "&apikey=" + apiKey;
        String commURL = "https://congress.api.sunlightfoundation.com/committees?member_ids=" + itembioguide[pos] + "&apikey=" + apiKey;
        String termURL = "https://congress.api.sunlightfoundation.com/legislators?bioguide_id=" + itembioguide[pos] + "&apikey=" + apiKey;


        DownloadTask task = new DownloadTask();
        task.execute(legislatorsURL, commURL, termURL);


    }

    public class DownloadTask extends AsyncTask<String, Void, String[]> {
        @Override
        protected String[] doInBackground(String... urls) {





            // Getting JSON from URL
            String[] results = new String[3];

            for (int i = 0; i < 3; i++) {
                String result = "";
                URL url;
                HttpURLConnection urlConnection = null;
                try {
                    System.out.println("plsplsletmeletmeletmegetwhatiwnat");
                    url = new URL(urls[i]);

                    urlConnection = (HttpURLConnection) url.openConnection();

                    InputStream in = urlConnection.getInputStream();

                    InputStreamReader reader = new InputStreamReader(in);

                    int data = reader.read();

                    while (data != -1) {

                        char current = (char) data;

                        result += current;

                        data = reader.read();

                    }

                    results[i] = result;

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            return results;
        }

        @Override
        protected void onPostExecute(String[] results) {
            super.onPostExecute(results);
            System.out.println("TRIGYRINGYIRN");
            String billListString = new String();
            String commListString = new String();
            String termInfo = new String();

            try {
                System.out.println("intryintryintry");
                System.out.println(results[0]);
                System.out.println(results[1]);
                JSONObject legislatorsData = new JSONObject(results[0]);
                JSONObject commData = new JSONObject(results[1]);
                JSONObject termData = new JSONObject(results[2]);
                String legislatorsInfo = legislatorsData.getString("results");
                String commInfo = commData.getString("results");
                String termz = termData.getString("results");
                JSONArray termArray = new JSONArray(termz);
                JSONObject termObject = termArray.getJSONObject(0);
                System.out.println(termObject);

                termInfo = termObject.getString("term_end");
                System.out.println(termInfo);
                JSONArray jsonArray = new JSONArray(legislatorsInfo);
                JSONArray commArray = new JSONArray(commInfo);
//                JSONArray termArray = new JSONArray(termInfo);
//                System.out.println(termArray);
//                termEnd = termArray.get("term_end");


                for (int i = 0; i < 5; i++) {
                    JSONObject jsonPart = jsonArray.getJSONObject(i);
                    JSONObject commPart = commArray.getJSONObject(i);
                    String shortTitle = jsonPart.getString("short_title");
                    String commName = commPart.getString("name");

                    if (!shortTitle.equals("null")) {
                        if (i < 4) {
                            billListString += " " + shortTitle + " - ";
                            commListString += " " + commName + " - ";
                        } else {
                            billListString += " " + shortTitle;
                            commListString += " " + commName;
                        }
                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            System.out.println("WHaaaaaat");
            TextView nameTxt = (TextView) findViewById(R.id.name);
            ImageView imageView = (ImageView) findViewById(R.id.icon);
            TextView termTxt = (TextView) findViewById(R.id.term);
            TextView commTxt = (TextView) findViewById(R.id.comm);
            TextView billsTxt = (TextView) findViewById(R.id.bills);

            nameTxt.setText(itemname[pos]);
            imageView.setImageResource(imgid[pos]);
            termTxt.setText("Term ends: " + termInfo);
            commTxt.setText("Committees: " + commListString);
            System.out.println(itembills);
            billsTxt.setText("Recent Bills: " + billListString);
        }
    }







}
