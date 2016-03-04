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
                { "Row 1, Col 0", "Row 1, Col 1", "Row 1, Col 2" },
                { "Row 2, Col 0", "Row 2, Col 1", "Row 2, Col 2" }
        };

        mPageIndicator = (DotsPageIndicator) findViewById(R.id.page_indicator);
        mViewPager = (GridViewPager) findViewById(R.id.pager);

        mViewPager.setAdapter(new GridPagerAdapter(getFragmentManager(), data));
        mPageIndicator.setPager(mViewPager);
    }

    private static final class GridPagerAdapter extends FragmentGridPagerAdapter {

        String[][] mData;

        private GridPagerAdapter(FragmentManager fm, String[][] data) {
            super(fm);
            mData = data;
        }

        @Override
        public Fragment getFragment(int row, int column) {
            return (CardFragment.create("CardFragment", mData[row][column]));
        }

        @Override
        public int getRowCount() {
            return mData.length;
        }

        @Override
        public int getColumnCount(int row) {
            return mData[row].length;
        }
    }
}
