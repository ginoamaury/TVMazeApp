package com.GinoAmaury.TVMazeApp.View.Adapters;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.GinoAmaury.TVMazeApp.Model.Object.Show;
import com.GinoAmaury.TVMazeApp.View.Fragments.ShowEpisodesFragment;
import com.GinoAmaury.TVMazeApp.View.Fragments.ShowInfoFragment;

public class ViewPAgerAdapterShow  extends FragmentStatePagerAdapter {
    private  String [] tituloTabs;
    private Show show;
    private static final String SHOWARG = "show";

    public ViewPAgerAdapterShow (FragmentManager fm, String [] tituloTabs, Show show) {
        super(fm);
        this.tituloTabs = tituloTabs;
        this.show = show;
    }


    @NonNull
    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                ShowInfoFragment fragment = new ShowInfoFragment();
                Bundle args = new Bundle();
                args.putSerializable(SHOWARG, show);
                fragment.setArguments(args);
                return  fragment;
            case 1:
                ShowEpisodesFragment fragment2 = new ShowEpisodesFragment();
                Bundle args2 = new Bundle();
                args2.putSerializable(SHOWARG, show);
                fragment2.setArguments(args2);
                return  fragment2;
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
