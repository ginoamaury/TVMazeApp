package com.GinoAmaury.TVMazeApp.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.GinoAmaury.TVMazeApp.Interfaces.Favorite.IFavoriteView;
import com.GinoAmaury.TVMazeApp.Model.Object.Show;
import com.GinoAmaury.TVMazeApp.Presenter.FavoritePresenter;
import com.GinoAmaury.TVMazeApp.Presenter.SettingsPresenter;
import com.GinoAmaury.TVMazeApp.R;
import com.GinoAmaury.TVMazeApp.Util.Utility;
import com.GinoAmaury.TVMazeApp.View.Activities.FavoriteActivity;
import com.GinoAmaury.TVMazeApp.View.Adapters.ViewPagerAdapterDashboard;
import com.GinoAmaury.TVMazeApp.View.Modals.DialogSettingsFragment;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.GinoAmaury.TVMazeApp.Util.Utility.showSnackbar;

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener, IFavoriteView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.appBar)
    AppBarLayout appBarLayout;
    @BindView(R.id.tabs)
    TabLayout tabLayout;
    @BindView(R.id.pager)
    ViewPager viewPager;

    private View view;
    private FavoritePresenter favoritePresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        showToolbarViewPager();
        view = findViewById(R.id.contentDash);
    }

    private void showToolbarViewPager (){
        String [] tituloTabs = {getResources().getString(R.string.viewPagerShows),getResources().getString(R.string.viewPagerSearch)};
        ViewPagerAdapterDashboard pagerAdapter = new ViewPagerAdapterDashboard(getSupportFragmentManager(),tituloTabs);
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_gear:
                showModalFragmentGear();
                break;
            case R.id.action_fav:
                checkFavs();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showModalFragmentGear (){
        DialogSettingsFragment dialogSettingsFragment = new DialogSettingsFragment();
        dialogSettingsFragment.show(this.getSupportFragmentManager(),"");
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){

            default:
                break;
        }
    }

    private void checkFavs(){
        favoritePresenter =  new FavoritePresenter(this);
        favoritePresenter.getFavorites(this);
    }


    @Override
    public void showResult(boolean result) {

    }

    @Override
    public void showResultDelete(boolean result) {

    }

    @Override
    public void showIfExist(boolean result) {

    }

    @Override
    public void showResultFavorites(ArrayList<Show> shows) {
            if(shows != null){
                Utility.goToNextActivityCleanStack(this, FavoriteActivity.class,false,null);
            }else{
                showSnackbar(view,this,R.string.errNoFavorites);
            }
    }
}