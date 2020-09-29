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

import com.GinoAmaury.TVMazeApp.Interfaces.Search.Actor.ISearchActorView;
import com.GinoAmaury.TVMazeApp.Model.Object.Episode;
import com.GinoAmaury.TVMazeApp.Model.Object.Person;
import com.GinoAmaury.TVMazeApp.Presenter.SearchActorPresenter;
import com.GinoAmaury.TVMazeApp.Presenter.SearchShowPresenter;
import com.GinoAmaury.TVMazeApp.R;
import com.GinoAmaury.TVMazeApp.Util.Utility;
import com.GinoAmaury.TVMazeApp.View.Adapters.ActorsAdapter;
import com.GinoAmaury.TVMazeApp.View.Adapters.IOnActorClick;
import com.GinoAmaury.TVMazeApp.View.Adapters.ShowsAdapter;
import com.GinoAmaury.TVMazeApp.View.Modals.DialogActorFragment;
import com.GinoAmaury.TVMazeApp.View.Modals.DialogEpisodeFragment;
import com.ethanhua.skeleton.Skeleton;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.GinoAmaury.TVMazeApp.Util.Utility.showSnackbar;


public class SearchPeopleFragment extends Fragment implements ISearchActorView, EditText.OnEditorActionListener, IOnActorClick {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    @BindView(R.id.recyclerPeople)
    RecyclerView recyclerViewShows;

    @BindView(R.id.inputSearch)
    EditText inputSearch;

    SearchActorPresenter presenter;
    ActorsAdapter actorsAdapter;

    public SearchPeopleFragment() {
        // Required empty public constructor
    }

    public static SearchPeopleFragment newInstance(String param1, String param2) {
        SearchPeopleFragment fragment = new SearchPeopleFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_people, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        inputSearch.setOnEditorActionListener(this);
    }

    private void showPreviewLoading (){
        recyclerViewShows.setLayoutManager(new GridLayoutManager(getContext(),2));
        Skeleton.bind(recyclerViewShows)
                .adapter(actorsAdapter)
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
    public void showResult(ArrayList<Person> actors) {
        if(actors != null){
            actorsAdapter = new ActorsAdapter(actors,this);
            recyclerViewShows.setItemAnimator(new DefaultItemAnimator());
            recyclerViewShows.setAdapter(actorsAdapter);
        }else{
            showSnackbar(getView(),getContext(),R.string.errActor);
        }
    }

    private void getActors(String name){
        presenter = new SearchActorPresenter(this);
        presenter.getActors(name);
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if ( actionId == EditorInfo.IME_ACTION_DONE) {
            String input = inputSearch.getText().toString();
            showPreviewLoading();
            getActors(input);
            Utility.hideKeyboard(getActivity());
            return true;
        }
        return false;
    }

    private void showModalFragmentActor (Person actor){
        DialogActorFragment dialogActorFragment =  DialogActorFragment.newInstance(actor);
        dialogActorFragment.show(getActivity().getSupportFragmentManager(),"");
    }

    @Override
    public void onActorClick(int pos) {
        showModalFragmentActor(actorsAdapter.getActor(pos));
    }
}