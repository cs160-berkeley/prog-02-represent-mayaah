package com.example.mayaah.mewatchemulator;

/**
 * Created by mayaah on 2/28/16.
 */

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
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


public class CongressionalViewActivity extends Activity{

    ListView list;
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

    String[] itemparty ={
            "Democrat",
            "Democrat",
            "Democrat"
    };

    String[] itememail ={
            "senator@feinstein.senate.gov",
            "senator@boxer.senate.gov",
            "dorislovesamerica@gmail.com"
    };

    String[] itemwebsite ={
            "http://www.feinstein.senate.gov",
            "http://boxer.senate.gov",
            "https://matsui.house.gov"
    };

    String[] itemtweet ={
            "#BlackHistoryMonth: My friend Willie Brown was the first African-American mayor of San Francisco.",
            "Putting the country first means Obama nominating a Justice and the Senate doing its consitutional duty by voting on the nominee.",
            "Looking forward to it!"
    };



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.congressional_view);

        CustomListAdapter adapter=new CustomListAdapter(this, itemname, imgid, itemparty, itememail, itemwebsite, itemtweet);
        list=(ListView)findViewById(R.id.list);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                String Slecteditem = itemname[+position];
//                Toast.makeText(getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getBaseContext(), DetailedViewActivity.class);
                    startActivity(intent);

            }
        });
    }
}
