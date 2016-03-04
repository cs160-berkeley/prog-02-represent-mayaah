package com.example.mayaah.mewatchemulator;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.wearable.view.FragmentGridPagerAdapter;

/**
 * Created by mayaah on 3/3/16.
 */
public class MyGridPagerAdapter extends FragmentGridPagerAdapter {

    String[][] mData;

    final String[][] name={
            {"Senator Dianne Feinstein", "Senator Barbara Boxer", "Representative Doris Matsui"}
    };

    final String[][] party={
            {"Democrat", "Democrat", "Democrat"}
    };

    public MyGridPagerAdapter(FragmentManager fm, String[][] data) {
        super(fm);
        mData = data;
    }

    @Override
    public Fragment getFragment(int row, int column) {
        //
        return (MyCard.create(name[row][column], party[row][column]));
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