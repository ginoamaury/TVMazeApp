package com.GinoAmaury.TVMazeApp.View.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.GinoAmaury.TVMazeApp.Interfaces.Shows.IShowView;
import com.GinoAmaury.TVMazeApp.Model.Object.Search;
import com.GinoAmaury.TVMazeApp.Presenter.ShowPresenter;
import com.GinoAmaury.TVMazeApp.R;
import com.GinoAmaury.TVMazeApp.Util.Utility;
import com.GinoAmaury.TVMazeApp.View.Activities.ShowActivity;
import com.GinoAmaury.TVMazeApp.View.Adapters.IOnShowClick;
import com.GinoAmaury.TVMazeApp.View.Adapters.ShowsAdapter;
import com.ethanhua.skeleton.Skeleton;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.GinoAmaury.TVMazeApp.Util.Utility.showSnackbar;
import static com.GinoAmaury.TVMazeApp.Util.Utility.showSnackbarTopMsg;

public class ShowsFragment extends Fragment implements IShowView, IOnShowClick, View.OnClickListener {

    @BindView(R.id.recyclerShows)
    RecyclerView recyclerViewShows;
    @BindView(R.id.swipeToRefresh)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.layoutLeft)
    LinearLayout layoutLeft;
    @BindView(R.id.layoutRigth)
    LinearLayout layoutRigth;
    @BindView(R.id.numberPage)
    TextView numberPage;

    private ShowsAdapter showsAdapter;
    private ShowPresenter showPresenter;
    private int currentPage;
    private int max;

    public ShowsFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_shows, container, false);
        ButterKnife.bind(this,view);
        currentPage = 0;
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int page = currentPage;
        numberPage.setText(String.valueOf(page+1));
        showPreviewLoading();
        getShows();
        refreshView();
        initOnclickLayouts();
    }

    private void initOnclickLayouts() {
        layoutLeft.setOnClickListener(this);
        layoutRigth.setOnClickListener(this);
    }

    private void getShows(){
        showPresenter = new ShowPresenter(this);
        showPresenter.getShows(currentPage);
    }

    private void refreshView (){
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                showPreviewLoading();
                getShows();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }



    private void showPreviewLoading (){
        recyclerViewShows.setLayoutManager(new GridLayoutManager(getContext(),2));
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

    @Override
    public void showResult(ArrayList<Search> shows) {
        if(shows != null){
            showsAdapter = new ShowsAdapter(shows,this,0);
            recyclerViewShows.setItemAnimator(new DefaultItemAnimator());
            recyclerViewShows.setAdapter(showsAdapter);
        }else{
            showSnackbar(getView(),getContext(),R.string.errShows);
        }

    }

    @Override
    public void onShowClick(int pos, String typeClick) {
        Utility.goToNextActivityCleanStackShow(getActivity(), ShowActivity.class,false,null,showsAdapter.getShow(pos));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.layoutRigth:
                if(currentPage>=0){
                    currentPage++;
                    int page = currentPage;
                    numberPage.setText(String.valueOf(page+1));
                    showPreviewLoading();
                    getShows();
                }
                break;
            case R.id.layoutLeft:
                if(currentPage>0){
                    currentPage--;
                    int page = currentPage;
                    numberPage.setText(String.valueOf(page+1));
                    showPreviewLoading();
                    getShows();
                }else{
                    showSnackbar(getView(),getContext(),R.string.errPrevious);
                }
                break;
        }
    }
}