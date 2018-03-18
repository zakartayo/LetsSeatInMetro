package com.example.letsseatinmetro.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.letsseatinmetro.Fragment.ExtreamFragment;
import com.example.letsseatinmetro.Fragment.NormalFragment;

/**
 * Created by 이승헌 on 2018-03-18.
 */

public class TabPagerAdapter extends FragmentStatePagerAdapter {

    // Count number of tabs
    private int tabCount;

    public TabPagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {

        // Returning the current tabs
        switch (position) {
            case 0:
                NormalFragment normalFragment = new NormalFragment();
                return normalFragment;
            case 1:
                ExtreamFragment extreamFragment = new ExtreamFragment();
                return extreamFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}