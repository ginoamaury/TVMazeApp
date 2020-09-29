package com.GinoAmaury.TVMazeApp.Presenter;

import com.GinoAmaury.TVMazeApp.Interactors.EpisodeInteractor;
import com.GinoAmaury.TVMazeApp.Interfaces.Episodes.IEpisodesPresenter;
import com.GinoAmaury.TVMazeApp.Interfaces.Episodes.IEpisodesView;
import com.GinoAmaury.TVMazeApp.Model.Object.Episode;

import java.util.ArrayList;

public class EpisodePresenter implements IEpisodesPresenter {

    IEpisodesView episodesView;
    EpisodeInteractor episodeInteractor;

    public EpisodePresenter(IEpisodesView episodesView) {
        this.episodesView = episodesView;
        episodeInteractor = new EpisodeInteractor(this);
    }

    @Override
    public void showResult(ArrayList<Episode> episodes) { episodesView.showResult(episodes); }

    @Override
    public void getEpisodes(int show) { episodeInteractor.getEpisodes(show); }
}
