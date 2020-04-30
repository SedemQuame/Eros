package com.visionarytech.eros.Adapters;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPageAdapter extends FragmentPagerAdapter {

    private final List<Fragment> listOfFragments = new ArrayList<>();
    private final List<String> listOfTitles = new ArrayList<>();


    public ViewPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return listOfFragments.get(position);
    }

    @Override
    public int getCount() {
        return listOfTitles.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return listOfTitles.get(position);
    }


    public void AddFragment (Fragment fragment, String title){
        listOfFragments.add(fragment);
        listOfTitles.add(title);
    }
}
