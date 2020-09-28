package com.GinoAmaury.TVMazeApp.Interfaces.Search;

import com.GinoAmaury.TVMazeApp.Model.Object.Search;

import java.util.ArrayList;

public interface ISearchShowPresenter {
    void getShows (String name);
    void showResult (ArrayList<Search> shows);
}
