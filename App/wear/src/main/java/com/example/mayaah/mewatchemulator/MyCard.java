package com.example.mayaah.mewatchemulator;

import android.os.Bundle;
import android.support.wearable.view.CardFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;

/**
 * Created by mayaah on 3/3/16.
 */
public class MyCard extends CardFragment {

    TextView nameText, partyText;



    @Override
    public View onCreateContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.card_view, container, false);

        Log.d("T", "onCreateContentView");

        nameText = (TextView) root.findViewById(R.id.title);
        partyText = (TextView) root.findViewById(R.id.description);

        root.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(final View view) {
                Context context = getActivity().getApplicationContext();
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, "COMEON", duration);
                toast.show();
                Log.d("T", "onClick");
            }
        });
        return root;
    }

}