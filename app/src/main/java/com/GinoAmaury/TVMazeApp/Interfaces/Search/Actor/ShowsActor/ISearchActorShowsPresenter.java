package com.GinoAmaury.TVMazeApp.Interfaces.Search.Actor.ShowsActor;

import com.GinoAmaury.TVMazeApp.Model.Object.Show;

import java.util.ArrayList;

public interface ISearchActorShowsPresenter {
    void getShows(int id);
    void showResult(ArrayList<Show> shows);
}
