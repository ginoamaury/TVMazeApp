package com.GinoAmaury.TVMazeApp.View.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.GinoAmaury.TVMazeApp.Interfaces.Favorite.IFavoriteView;
import com.GinoAmaury.TVMazeApp.Model.Object.Show;
import com.GinoAmaury.TVMazeApp.Presenter.FavoritePresenter;
import com.GinoAmaury.TVMazeApp.R;
import com.GinoAmaury.TVMazeApp.Util.Utility;
import com.GinoAmaury.TVMazeApp.View.Adapters.IOnShowClick;
import com.GinoAmaury.TVMazeApp.View.Adapters.ShowsAdapter;
import com.ethanhua.skeleton.Skeleton;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.GinoAmaury.TVMazeApp.Util.Utility.showSnackbar;

public class FavoriteActivity extends AppCompatActivity implements IFavoriteView, IOnShowClick {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerFav)
    RecyclerView recyclerViewShows;

    private ShowsAdapter showsAdapter;
    private FavoritePresenter favoritePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        ButterKnife.bind(this);
        addToolbar();

    }

    @Override
    protected void onResume() {
        super.onResume();
        getFavorites();
    }

    public void getFavorites (){
        showPreviewLoading();
        favoritePresenter = new FavoritePresenter(this);
        favoritePresenter.getFavorites(this);
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

    private void showPreviewLoading (){
        recyclerViewShows.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        Skeleton.bind(recyclerViewShows)
                .adapter(showsAdapter)
                .shimmer(true)      // whether show shimmer animation.                      default is true
                .count(12)          // the recycler view item count.                        default is 10
                .color(R.color.White)       // the shimmer color.                                   default is #a2878787
                .angle(20)          // the shimmer angle.                                   default is 20;
                .duration(900)     // the shimmer animation duration.                      default is 1000;
                .frozen(false)      // whether frozen recyclerView during skeleton showing  default is true;
                .load(R.layout.show_list_item_principal_preview)
                .show();
    }

    private void onClickNavigation (){
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
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
            showsAdapter = new ShowsAdapter(shows,this,0,getApplicationContext());
            recyclerViewShows.setItemAnimator(new DefaultItemAnimator());
            recyclerViewShows.setAdapter(showsAdapter);
        }else{
            //showSnackbar(getCurrentFocus(),getApplicationContext(),R.string.errShows);
        }
    }

    @Override
    public void onShowClick(int pos, String typeClick) {
        switch (typeClick){
            case Utility.CLICKCARD:
                Utility.goToNextActivityCleanStackShow(this, ShowActivity.class,false,null,showsAdapter.getShow(pos));
                break;
            case Utility.CLICKADDFAV:

                break;
        }
    }
}