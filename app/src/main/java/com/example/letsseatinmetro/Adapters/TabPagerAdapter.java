package com.example.letsseatinmetro.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.letsseatinmetro.Fragment.ExtreamFragment;
import com.example.letsseatinmetro.Fragment.NineExtreamFragment;
import com.example.letsseatinmetro.Fragment.NormalFragment;
import com.example.letsseatinmetro.Fragment.NineNormalFragment;

/**
 * Created by 이승헌 on 2018-03-18.
 */

public class TabPagerAdapter extends FragmentStatePagerAdapter {

    // Count number of tabs
    private int tabCount;
    private int lineNum;

    public TabPagerAdapter(FragmentManager fm, int tabCount, int lineNum) {
        super(fm);
        this.tabCount = tabCount;
        this.lineNum = lineNum;
    }

    @Override
    public Fragment getItem(int position) {

        // Returning the current tabs
        switch (lineNum){
            case 1:
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
            case 9:
                switch (position) {
                    case 0:
                        NineNormalFragment nineNormalFragment = new NineNormalFragment();
                        return nineNormalFragment;
                    case 1:
                        NineExtreamFragment nineExtreamFragment = new NineExtreamFragment();
                        return nineExtreamFragment;
                    default:
                        return null;
                }
                default:
                    return null;
        }


    }

    @Override
    public int getCount() {
        return tabCount;
    }
}