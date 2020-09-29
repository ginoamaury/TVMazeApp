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
import android.widget.TextView;

import com.GinoAmaury.TVMazeApp.Interfaces.Episodes.IEpisodesView;
import com.GinoAmaury.TVMazeApp.Model.Object.Episode;
import com.GinoAmaury.TVMazeApp.Model.Object.Show;
import com.GinoAmaury.TVMazeApp.Presenter.EpisodePresenter;
import com.GinoAmaury.TVMazeApp.R;
import com.GinoAmaury.TVMazeApp.View.Adapters.EpisodesAdapter;
import com.GinoAmaury.TVMazeApp.View.Adapters.IOnEpisodeClick;
import com.GinoAmaury.TVMazeApp.View.Modals.DialogEpisodeFragment;
import com.ethanhua.skeleton.Skeleton;
import com.google.android.material.chip.Chip;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ShowEpisodesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShowEpisodesFragment extends Fragment implements IEpisodesView, IOnEpisodeClick {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String SHOWARG = "show";
    private Show show;

    @BindView(R.id.titleShow)
    TextView titleShow;
    @BindView(R.id.chip)
    Chip chip;

    @BindView(R.id.recyclerEpisodes)
    RecyclerView recyclerEpisodes;
    @BindView(R.id.swipeToRefresh)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private EpisodesAdapter episodesAdapter;
    private EpisodePresenter episodePresenter;

    public ShowEpisodesFragment() {}


    public static ShowEpisodesFragment newInstance(Show show) {
        ShowEpisodesFragment fragment = new ShowEpisodesFragment();
        Bundle args = new Bundle();
        args.putSerializable(SHOWARG, show);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            show = (Show) getArguments().getSerializable(SHOWARG);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show_episodes, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(show != null){
            titleShow.setText(show.getName());
            chip.setText(show.getGenres().toString());
            showPreviewLoading();
            getEpisodes(show.getId());
        }
    }

    private void showModalFragmentEpisode (Episode episode){
        DialogEpisodeFragment dialogSettingsFragment =  DialogEpisodeFragment.newInstance(episode);
        dialogSettingsFragment.show(getActivity().getSupportFragmentManager(),"");
    }

    private void getEpisodes(int show){
        episodePresenter = new EpisodePresenter(this);
        episodePresenter.getEpisodes(show);
    }

    private void showPreviewLoading (){
        recyclerEpisodes.setLayoutManager(new GridLayoutManager(getContext(),2));
        Skeleton.bind(recyclerEpisodes)
                .adapter(episodesAdapter)
                .shimmer(true)      // whether show shimmer animation.                      default is true
                .count(12)          // the recycler view item count.                        default is 10
                .color(R.color.White)       // the shimmer color.                                   default is #a2878787
                .angle(20)          // the shimmer angle.                                   default is 20;
                .duration(900)     // the shimmer animation duration.                      default is 1000;
                .frozen(false)      // whether frozen recyclerView during skeleton showing  default is true;
                .load(R.layout.show_episode_item_preview)
                .show();
    }

    @Override
    public void showResult(ArrayList<Episode> episodes) {
            if(episodes != null){
                episodesAdapter = new EpisodesAdapter(episodes,this);
                recyclerEpisodes.setItemAnimator(new DefaultItemAnimator());
                recyclerEpisodes.setAdapter(episodesAdapter);
            }
    }

    @Override
    public void onEpisodeClick(int pos) {
        showModalFragmentEpisode(episodesAdapter.getEpisode(pos));
    }
}