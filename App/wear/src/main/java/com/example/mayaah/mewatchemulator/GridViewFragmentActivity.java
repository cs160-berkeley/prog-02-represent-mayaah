package com.example.mayaah.mewatchemulator;
import android.app.Fragment;
import android.app.FragmentManager;
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

        final String[][] name={
                {"Senator Dianne Feinstein", "Senator Barbara Boxer", "Representative Doris Matsui"}
        };

        final String[][] party={
                {"Democrat", "Democrat", "Democrat"}
        };



        mPageIndicator = (DotsPageIndicator) findViewById(R.id.page_indicator);
        mViewPager = (GridViewPager) findViewById(R.id.pager);

        mViewPager.setAdapter(new MyGridPagerAdapter(getFragmentManager(), name));
        mPageIndicator.setPager(mViewPager);
    }

}
