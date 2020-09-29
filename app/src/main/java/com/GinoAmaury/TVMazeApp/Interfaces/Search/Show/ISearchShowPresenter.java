package com.GinoAmaury.TVMazeApp.Interfaces.Search.Show;

import com.GinoAmaury.TVMazeApp.Model.Object.Show;

import java.util.ArrayList;

public interface ISearchShowPresenter {
    void getShows (String name);
    void showResult (ArrayList<Show> shows);
}
