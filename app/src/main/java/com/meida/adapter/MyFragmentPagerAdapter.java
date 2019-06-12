package com.meida.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;

import java.util.ArrayList;

/*
* FragmentStatePagerAdapter 和 FragmentPagerAdapter
* */
public class MyFragmentPagerAdapter extends FragmentStatePagerAdapter {
    ArrayList<Fragment> fragments;
    public MyFragmentPagerAdapter(FragmentManager fm, ArrayList<Fragment> list) {
        super(fm);
        this.fragments = list;
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public int getItemPosition(Object object) {
        // TODO Auto-generated method stub
        return PagerAdapter.POSITION_NONE;
    }

    @Override
    public Fragment getItem(int arg0) {
        return fragments.get(arg0);
    }
}
