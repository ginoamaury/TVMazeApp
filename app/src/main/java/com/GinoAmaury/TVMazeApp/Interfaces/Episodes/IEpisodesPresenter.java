package com.GinoAmaury.TVMazeApp.Interfaces.Episodes;

import com.GinoAmaury.TVMazeApp.Model.Object.Episode;

import java.util.ArrayList;

public interface IEpisodesPresenter {
    void showResult(ArrayList<Episode> episodes);
    void getEpisodes (int show);
}
