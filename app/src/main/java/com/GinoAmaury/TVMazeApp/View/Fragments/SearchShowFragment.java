package com.GinoAmaury.TVMazeApp.View.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.GinoAmaury.TVMazeApp.Interfaces.Search.Show.ISearchShowView;
import com.GinoAmaury.TVMazeApp.Model.Object.Show;
import com.GinoAmaury.TVMazeApp.Presenter.SearchShowPresenter;
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

public class SearchShowFragment extends Fragment implements ISearchShowView, EditText.OnEditorActionListener, IOnShowClick {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    @BindView(R.id.recyclerShows)
    RecyclerView recyclerViewShows;

    @BindView(R.id.inputSearch)
    EditText inputSearch;

    SearchShowPresenter presenter;
    private ShowsAdapter showsAdapter;

    public SearchShowFragment() {
        // Required empty public constructor
    }

    public static SearchShowFragment newInstance(String param1, String param2) {
        SearchShowFragment fragment = new SearchShowFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private void getShows(String name){
        presenter = new SearchShowPresenter(this);
        presenter.getShows(name);
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_show, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        inputSearch.setOnEditorActionListener(this);
    }

    @Override
    public void showResult(ArrayList<Show> shows) {
        if(shows != null){
            Log.d("SHOW", shows.get(0).getName() +"-");
            showsAdapter = new ShowsAdapter(shows,this,0);
            recyclerViewShows.setItemAnimator(new DefaultItemAnimator());
            recyclerViewShows.setAdapter(showsAdapter);
        }else{
            showSnackbar(getView(),getContext(),R.string.errShows);
        }
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if ( actionId == EditorInfo.IME_ACTION_DONE) {
            String input = inputSearch.getText().toString();
            showPreviewLoading();
            getShows(input);
            return true;
        }
        return false;
    }

    @Override
    public void onShowClick(int pos, String typeClick) {
        Utility.goToNextActivityCleanStackShow(getActivity(), ShowActivity.class,false,null,showsAdapter.getShow(pos));
    }
}
