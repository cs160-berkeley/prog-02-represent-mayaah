package com.example.mayaah.mewatchemulator;

import android.app.Activity;
import android.graphics.Typeface;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.content.Context;
import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.wearable.Wearable;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    private Button zipCodeButton;
    private Button currLocationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/Lato-Light.ttf");

        TextView welcome = (TextView) findViewById(R.id.textView);
        TextView zc = (TextView) findViewById(R.id.editText);
        TextView clb = (TextView) findViewById(R.id.curr_btn);
        TextView or = (TextView) findViewById(R.id.textView2);
        welcome.setTypeface(custom_font);
        zc.setTypeface(custom_font);
        clb.setTypeface(custom_font);
        or.setTypeface(custom_font);

        zipCodeButton = (Button) findViewById(R.id.zip_btn);
        currLocationButton = (Button) findViewById(R.id.curr_btn);
        final EditText zipCodeEditText = (EditText) findViewById(R.id.editText);

        zipCodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(getBaseContext(), CongressionalViewActivity.class);
                String zipCodeText = zipCodeEditText.getText().toString();
                intent.putExtra("Location", zipCodeText);
                startActivity(intent);

                Intent watchIntent = new Intent(getBaseContext(), PhoneToWatchService.class);
                startService(watchIntent);

            }
        });

        currLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(getBaseContext(), CongressionalViewActivity.class);
//                Toast.makeText(MainActivity.this, String.valueOf(lltoZip), Toast.LENGTH_LONG).show();
                intent.putExtra("Location", lltoZip);
                startActivity(intent);

                Intent watchIntent = new Intent(getBaseContext(), PhoneToWatchService.class);
                startService(watchIntent);

            }
        });

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addApi(Wearable.API)  // used for data layer API
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private GoogleApiClient mGoogleApiClient;
    protected Location mLastLocation;
    public String lltoZip;


    @Override
    protected void onResume() {
        super.onResume();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mGoogleApiClient.disconnect();
    }

    @Override
    public void onConnected(Bundle bundle) {
        try {
            mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        if (mLastLocation != null) {
            double latitude = (mLastLocation.getLatitude());
            double longitude = (mLastLocation.getLongitude());
//            Toast.makeText(this, String.valueOf(latitude), Toast.LENGTH_LONG).show();
//            Toast.makeText(this, String.valueOf(longitude), Toast.LENGTH_LONG).show();
            Geocoder geocoder;
            List<Address> addresses;
            geocoder = new Geocoder(this, Locale.getDefault());
            try {
                addresses = geocoder.getFromLocation(latitude, longitude, 1);
                lltoZip = addresses.get(0).getPostalCode();

            } catch (IOException e) {
                e.printStackTrace();
            }


        } else {
            Toast.makeText(this, "No Location Detected", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {}

    @Override
    public void onConnectionFailed(ConnectionResult connResult) {}



}
