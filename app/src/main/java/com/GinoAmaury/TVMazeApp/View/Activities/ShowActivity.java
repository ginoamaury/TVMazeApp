package com.GinoAmaury.TVMazeApp.View.Activities;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.GinoAmaury.TVMazeApp.Interfaces.Favorite.IFavoriteView;
import com.GinoAmaury.TVMazeApp.Model.Object.Show;
import com.GinoAmaury.TVMazeApp.Presenter.FavoritePresenter;
import com.GinoAmaury.TVMazeApp.Util.Utility;
import com.GinoAmaury.TVMazeApp.View.Adapters.ViewPAgerAdapterShow;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.GinoAmaury.TVMazeApp.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShowActivity extends AppCompatActivity implements IFavoriteView {

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
    private Show show;

    private boolean favIsActive;
    private View focus;

    FavoritePresenter favoritePresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        ButterKnife.bind(this);
        favoritePresenter = new FavoritePresenter(this);
        show = (Show) getIntent().getSerializableExtra("SHOW");
        addToolbar();
        showToolbarViewPager();
        addFavClick(favFab);
        setImage();
    }

    @Override
    protected void onResume() {
        super.onResume();
        favoritePresenter.findFav(show,getApplicationContext());
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
               focus = v;
                if(favIsActive){
                     favoritePresenter.deleteFav(show,getApplicationContext());
                }else {
                    favoritePresenter.addFav(show,getApplicationContext());
                }

            }
        });
    }

    private void changeFabIcon (FloatingActionButton favIcon,boolean isActive){
        if(isActive){
            favIcon.setImageDrawable(getDrawable(R.drawable.ic_heart_full));
        }else{
            favIcon.setImageDrawable(getDrawable(R.drawable.ic_heart));
        }
    }

    private void showToolbarViewPager (){
        String [] tituloTabs = {getResources().getString(R.string.viewPagerInfo),getResources().getString(R.string.viewPagerEpisodes)};
        ViewPAgerAdapterShow pagerAdapter = new ViewPAgerAdapterShow(getSupportFragmentManager(),tituloTabs,show);
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void showResult(boolean result) {
        if(result){
            favIsActive = true;
            changeFabIcon(favFab,true);
            Utility.showSnackbar(focus,getApplicationContext(),R.string.favNotification);
        }else{
            Utility.showSnackbar(focus,getApplicationContext(),R.string.favErr);
        }
    }

    @Override
    public void showResultDelete(boolean result) {
        if(result){
            favIsActive = false;
            changeFabIcon(favFab,false);
            Utility.showSnackbar(focus,getApplicationContext(),R.string.favNotificationDelete);
        }else{
            Utility.showSnackbar(focus,getApplicationContext(),R.string.favErr);
        }
    }

    @Override
    public void showIfExist(boolean result) {
        if (result){
            favIsActive = true;
            changeFabIcon(favFab,true);
        }else{
            favIsActive = false;
            changeFabIcon(favFab,false);
        }
    }

    @Override
    public void showResultFavorites(ArrayList<Show> shows) {

    }
}