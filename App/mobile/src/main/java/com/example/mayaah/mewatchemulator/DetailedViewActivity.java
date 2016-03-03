package com.example.mayaah.mewatchemulator;

/**
 * Created by mayaah on 2/29/16.
 */

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;


public class DetailedViewActivity extends Activity{

    String[] itemname ={
            "Senator Dianne Feinstein",
            "Senator Barbara Boxer",
            "Representaive Doris Matsui"
    };

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

    String[] itembills={
            "California Desert Conservation, Off-Road Recreation, and Renewable Energy Act, Interstate Threats Clarificatino Act of 2016, Visa Waiver Program Security Enhancement Act",
            "Female Veteran Suicide Prevention Act, End of Suffering Act of 2015, SAFE DRONE Act of 2015",
            "Extend Excellence in Mental Health Act of 2016, Spectrum Challenge Prize ACt of 2015, Cruise Passenger Protection Act"


    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailed_view);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            int pos = extras.getInt("POSITION");
        }
        int pos = extras.getInt("POSITION");

        TextView nameTxt = (TextView) findViewById(R.id.name);
        ImageView imageView = (ImageView) findViewById(R.id.icon);
        TextView termTxt = (TextView) findViewById(R.id.term);
        TextView commTxt = (TextView) findViewById(R.id.comm);
        TextView billsTxt = (TextView) findViewById(R.id.bills);

        nameTxt.setText(itemname[pos]);
        imageView.setImageResource(imgid[pos]);
        termTxt.setText("Term ends: " + itemterm[pos]);
        commTxt.setText("Committees " + itemcomm[pos]);
        billsTxt.setText("Recent Bills" + itembills[pos]);
    }
}
