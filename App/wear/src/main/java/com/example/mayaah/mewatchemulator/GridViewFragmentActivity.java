package com.example.mayaah.mewatchemulator;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.wearable.view.CardFragment;
import android.support.wearable.view.DotsPageIndicator;
import android.support.wearable.view.FragmentGridPagerAdapter;
import android.support.wearable.view.GridPagerAdapter;
import android.support.wearable.view.GridViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * Created by mayaah on 3/2/16.
 */
public class GridViewFragmentActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wcongressional_view);

        final DotsPageIndicator mPageIndicator;
        final GridViewPager mViewPager;

        final String[][] data = {
                { "Row 0, Col 0", "Row 0, Col 1", "Row 0, Col 2" },
        };

        String[][] name = {
                {"Barbara Boxer", "Dianne Feinstein", "Doris Matsui"}
        };

        String[][] party={
                {"Democrat", "Democrat", "Democrat"}
        };


//        Intent intent = getIntent();
//        Bundle extras = intent.getExtras();
//        String[] nameArray = extras.getStringArray("itemname");
//        String[] partyArray = extras.getStringArray("itemparty");
//        name = new String[0][nameArray.length];
//        party = new String[0][partyArray.length];
//        for (int r = 0; r < nameArray.length; r++)
//        {
//            for (int c = 0; c < nameArray.length; c++)
//            {
//                name[r][c] = nameArray[c];
//                party[r][c] = partyArray[c];
//            }
//        }



        mPageIndicator = (DotsPageIndicator) findViewById(R.id.page_indicator);
        mViewPager = (GridViewPager) findViewById(R.id.pager);

        mViewPager.setAdapter(new MyGridPagerAdapter(getFragmentManager(), name));
        mPageIndicator.setPager(mViewPager);
    }

}
