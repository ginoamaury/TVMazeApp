package com.GinoAmaury.TVMazeApp.View.Activities;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.GinoAmaury.TVMazeApp.Model.Object.Search;
import com.GinoAmaury.TVMazeApp.Util.Utility;
import com.GinoAmaury.TVMazeApp.View.Adapters.ViewPAgerAdapterShow;
import com.GinoAmaury.TVMazeApp.View.Adapters.ViewPagerAdapterDashboard;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.GinoAmaury.TVMazeApp.R;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShowActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.app_bar)
    AppBarLayout appBarLayout;
    @BindView(R.id.tabs)
    TabLayout tabLayout;
    @BindView(R.id.pager)
    ViewPager viewPager;
    @BindView(R.id.fab)
    FloatingActionButton favFab;
    @BindView(R.id.show_image)
    ImageView showImage;
    private Search show;

    private boolean favIsActive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        ButterKnife.bind(this);
        show = (Search) getIntent().getSerializableExtra("SHOW");
        addToolbar();
        showToolbarViewPager();
        addFavClick(favFab);
        setImage();
    }

    private void setImage (){

        if(show != null){
            if(show.getImage()!= null){
                if(show.getImage().getMedium()!=null && show.getImage().getOriginal()!=null ){
                    String urlImage = show.getImage().getOriginal();
                    Utility.showImage(getApplicationContext(),showImage,urlImage);
                }else if(show.getImage().getMedium()==null && show.getImage().getOriginal()!=null ){
                    String urlImage = show.getImage().getOriginal();
                    Utility.showImage(getApplicationContext(),showImage,urlImage);
                }else if(show.getImage().getMedium()!=null && show.getImage().getOriginal()==null ){
                    String urlImage = show.getImage().getMedium();
                    Utility.showImage(getApplicationContext(),showImage,urlImage);
                }
            }else{
                Utility.showImage(getApplicationContext(),showImage,"noimage");
            }
        }
    }

    private void addToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        int colorNavigationIcon = getResources().getColor(R.color.White);
        Drawable iconNavigation = getResources().getDrawable(R.drawable.ic_left_arrow);
        iconNavigation.setColorFilter(colorNavigationIcon, PorterDuff.Mode.SRC_ATOP);
        toolbar.setNavigationIcon(iconNavigation);
        onClickNavigation();
    }

    private void onClickNavigation (){
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void addFavClick (FloatingActionButton fab){
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFabIcon(favFab,favIsActive,v);
            }
        });
    }

    private void changeFabIcon (FloatingActionButton favIcon,boolean isActive,View v){
        if(isActive){
            favIcon.setImageDrawable(getDrawable(R.drawable.ic_heart));
            Utility.showSnackbar(v,getBaseContext(),R.string.favNotificationDelete);
            favIsActive = false;
        }else{
            favIsActive = true;
            favIcon.setImageDrawable(getDrawable(R.drawable.ic_heart_full));
            Utility.showSnackbar(v,getBaseContext(),R.string.favNotification);
        }
    }

    private void showToolbarViewPager (){
        String [] tituloTabs = {getResources().getString(R.string.viewPagerInfo),getResources().getString(R.string.viewPagerEpisodes)};
        ViewPAgerAdapterShow pagerAdapter = new ViewPAgerAdapterShow(getSupportFragmentManager(),tituloTabs,show);
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}