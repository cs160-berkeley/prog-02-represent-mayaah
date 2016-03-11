package com.example.mayaah.mewatchemulator;

/**
 * Created by mayaah on 2/28/16.
 */

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.widget.Button;
import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.appdatasearch.GetRecentContextCall;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


import com.squareup.okhttp.Callback;


public class CongressionalViewActivity extends Activity{


    private String mLocation;

    ListView list;
    String[] itemname;

    Integer[] imgid={
            R.drawable.feinstein,
            R.drawable.boxer,
            R.drawable.matsui,

    };

    String[] itemparty;

    String[] itememail;

    String[] itemwebsite;

    String[] itemtweet ={
            "#BlackHistoryMonth: My friend Willie Brown was the first African-American mayor of San Francisco.",
            "Putting the country first means Obama nominating a Justice and the Senate doing its consitutional duty by voting on the nominee.",
            "Looking forward to it!"
    };

    String[] itembioguide;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        Toast.makeText(this,"why", Toast.LENGTH_LONG).show();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.congressional_view);


        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        mLocation = extras.getString("Location");

        Runnable displayInfo = new Runnable() {
            @Override
            public void run() {
                CustomListAdapter adapter=new CustomListAdapter(CongressionalViewActivity.this, itemname, imgid, itemparty, itememail, itemwebsite, itemtweet);
                list=(ListView)findViewById(R.id.list);
                list.setAdapter(adapter);

                list.setOnItemClickListener(new OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {
                        // TODO Auto-generated method stub
                        String Slecteditem = itemname[+position];
//                Toast.makeText(getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();
                        Bundle b=new Bundle();
                        b.putStringArray("itemname", itemname);
                        b.putStringArray("itembioguide", itembioguide);
                        b.putInt("POSITION", position);
                        Intent intent = new Intent(getBaseContext(), DetailedViewActivity.class);
                        intent.putExtras(b);
//                        intent.putExtra("POSITION", position);
                        startActivity(intent);

                    }
                });
            }
        };

        getLegislators(mLocation, displayInfo);


    }

    public void getLegislators(String zipcode, final Runnable runnable) {
        String apiKey = "9dd30236d3784021854ae939c949c418";
        String legislatorsURL = "https://congress.api.sunlightfoundation.com/legislators/locate?apikey=" + apiKey + "&zip=" + zipcode;

        if (isNetworkAvailable()){
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(legislatorsURL)
                    .build();
            Call call = client.newCall(request);
            call.enqueue(new Callback() {

                @Override
                public void onFailure(Request request, IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(Response response) throws IOException {
                    try {
                        String jsonData = response.body().string();
                        Log.v("jsondata", jsonData);

                        if (response.isSuccessful()) {
                            getInfo(jsonData);
                            runOnUiThread(runnable);
                        } else {
                            Toast.makeText(CongressionalViewActivity.this, "idk", Toast.LENGTH_SHORT).show();
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }  else {
            Toast.makeText(this, "network unavailable", Toast.LENGTH_LONG).show();
        }
    }

    private void getInfo(String jsonData) throws JSONException {
        JSONObject legislatorsData = new JSONObject(jsonData);
        String legislatorsInfo = legislatorsData.getString("results");
        Log.i("legistlaors Info", legislatorsInfo);
        JSONArray jsonArray = new JSONArray(legislatorsInfo);
        String[] nameList = new String[jsonArray.length()];
        String[] partyList = new String[jsonArray.length()];
        String[] emailList = new String[jsonArray.length()];
        String[] websiteList = new String[jsonArray.length()];
        String[] bioguideList = new String[jsonArray.length()];

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonPart = jsonArray.getJSONObject(i);
            String firstName = jsonPart.getString("first_name");
            String lastName = jsonPart.getString("last_name");
            String title = jsonPart.getString("title");
            nameList[i] = (title + " " + firstName + " " + lastName);
            String party = jsonPart.getString("party");
            partyList[i] = party;
            String email = jsonPart.getString("oc_email");
            emailList[i] = email;
            String website = jsonPart.getString("website");
            websiteList[i] = website;
            String bioguideid = jsonPart.getString("bioguide_id");
            bioguideList[i] = bioguideid;
        }
        itemname = nameList;
        itemparty = partyList;
        itememail = emailList;
        itemwebsite = websiteList;
        itembioguide = bioguideList;
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected()) {
            isAvailable = true;
        }
        return isAvailable;
    }
}
