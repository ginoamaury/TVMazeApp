package com.GinoAmaury.TVMazeApp.View.Adapters;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.GinoAmaury.TVMazeApp.R;
import com.GinoAmaury.TVMazeApp.View.Fragments.SearchFragment;
import com.GinoAmaury.TVMazeApp.View.Fragments.ShowsFragment;

public class ViewPagerAdapterDashboard  extends FragmentStatePagerAdapter {
    private  String [] tituloTabs;
    public ViewPagerAdapterDashboard (FragmentManager fm, String [] tituloTabs) {
        super(fm);
        this.tituloTabs = tituloTabs;
    }



    @Override
    public Fragment getItem(int i) {

        switch (i){
            case 0: return new ShowsFragment();
            case 1: return new SearchFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    public CharSequence getPageTitle ( int position){
        return tituloTabs[position];
    }
}
